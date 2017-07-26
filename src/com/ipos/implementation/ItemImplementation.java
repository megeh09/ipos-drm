/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Item;
import com.ipos.entity.Stock;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.jpa.controller.UnitJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import com.ipos.view.settings.item.AddItemDialog;
import com.ipos.view.settings.item.UpdateItemDialog;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author megeh
 */
public class ItemImplementation {

    protected EntityManagerFactory emf;
    protected ItemJpaController itemJpaController;
    protected UnitJpaController unitJpaController;
    protected UserJpaController userJpaController;
    protected StockJpaController stockJpaController;

    public ItemImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        itemJpaController = new ItemJpaController(emf);
        unitJpaController = new UnitJpaController(emf);
        userJpaController = new UserJpaController(emf);
        stockJpaController = new StockJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "item",
            "Code",
            "SC Number",
            "Name",
            "Color",
            "Unit",
            "Description",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Item> items = itemJpaController.findItemEntities();

        for (Item item : items) {
            int i = 0;
            Object[] newRow = new Object[9];

            newRow[i++] = item;
            newRow[i++] = item.getCode();
            newRow[i++] = item.getStockCardNumber();
            newRow[i++] = item.getName();
            newRow[i++] = item.getColor();
            newRow[i++] = unitJpaController.findUnit(item.getFKunitId()).getCode();
            newRow[i++] = item.getDescription();
            newRow[i++] = DateUtil.toMMMMddyyyyFormat(item.getDate());
            newRow[i++] = userJpaController.findUser(item.getFKcreatedByUserId()).getFullname();

            model.addRow(newRow);
        }

        return model;
    }

    public void add() {
        AddItemDialog dialog = new AddItemDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Item item = (Item) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            UpdateItemDialog dialog = new UpdateItemDialog(null, true, this.emf, item);

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void remove(JXTable table, String bodega) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            try {
                Item item = (Item) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);

                // Check if item has stocks.
                List<Stock> stocks = stockJpaController.findStock(item, bodega);

                if (!stocks.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Delete not allowed.  This item has a corresponding stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    return;
                }

                Integer response = JOptionPane.showConfirmDialog(null, "You are about to remove " + item.getName() + " item. Do you want to continue?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    itemJpaController.destroy(item.getId());

                    JOptionPane.showMessageDialog(null, "Item successfully deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ItemImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
