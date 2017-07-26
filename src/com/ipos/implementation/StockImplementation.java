/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Stock;
import com.ipos.helper.util.DateUtil;
import com.ipos.helper.util.JComboBoxModelUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.PersonnelJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.view.stock.adjust.StockAdjustDialog;
import com.ipos.view.stock.in.StockInDialog;
import com.ipos.view.stock.out.StockWithdrawalDialog;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author megeh
 */
public class StockImplementation {

    protected EntityManagerFactory emf;
    protected StockJpaController stockJpaController;
    protected PersonnelJpaController personnelJpaController;
    protected ItemJpaController itemJpaController;
    protected UserJpaController userJpaController;

    public StockImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        stockJpaController = new StockJpaController(emf);
        personnelJpaController = new PersonnelJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public ComboBoxModel getComboBoxModel(String bodega) {
        return JComboBoxModelUtil.getStockModel("Select Stock", stockJpaController.findStockEntities(bodega));
    }

    public TableModel getTableModel(String bodega) {
        Object[] columnName = {
            "stock",
            "Code",
            "SC Number",
            "Item",
            "Description",
            "Color",
            "Quantity",
            "Personnel",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Stock> stocks = stockJpaController.findStocks(bodega);

        try {            
            for (Stock stock : stocks) {
                int i = 0;
                Object[] newRow = new Object[10];

                newRow[i++] = stock;
                newRow[i++] = stock.getCode();
                newRow[i++] = stock.getItem().getStockCardNumber();
                newRow[i++] = stock.getItem().getName();
                newRow[i++] = stock.getItem().getDescription();
                newRow[i++] = stock.getItem().getColor();
                newRow[i++] = stock.getQuantity();
                newRow[i++] = personnelJpaController.findPersonnel(stock.getFKpersonnelId());
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(stock.getDate());
                newRow[i++] = userJpaController.findUser(stock.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(StockImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public void add(String bodega) {
        StockInDialog dialog = new StockInDialog(null, true, this.emf, bodega);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Stock stock = (Stock) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            StockAdjustDialog dialog = new StockAdjustDialog(null, true, this.emf, stock);

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void withdrawal(JXTable table, String bodega) {
        StockWithdrawalDialog dialog = new StockWithdrawalDialog(null, true, this.emf, bodega, table);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
