/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Supplier;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.SupplierJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import com.ipos.view.settings.supplier.AddSupplierDialog;
import com.ipos.view.settings.supplier.UpdateSupplierDialog;
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
public class SupplierImplementation {

    protected EntityManagerFactory emf;
    protected SupplierJpaController supplierJpaController;
    protected UserJpaController userJpaController;

    public SupplierImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        supplierJpaController = new SupplierJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "Code",
            "Name",
            "Description",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Supplier> suppliers = supplierJpaController.findSupplierEntities();

        for (Supplier supplier : suppliers) {
            int i = 0;
            Object[] newRow = new Object[5];

            newRow[i++] = supplier.getCode();
            newRow[i++] = supplier;
            newRow[i++] = supplier.getDescription();
            newRow[i++] = DateUtil.toMMMMddyyyyFormat(supplier.getDate());
            newRow[i++] = userJpaController.findUser(supplier.getFKcreatedByUserId()).getFullname();

            model.addRow(newRow);
        }

        return model;
    }

    public void add() {
        AddSupplierDialog dialog = new AddSupplierDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Supplier supplier = (Supplier) table.getValueAt(row, 1);
            UpdateSupplierDialog dialog = new UpdateSupplierDialog(null, true, this.emf, supplier);

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void remove(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            try {
                Supplier supplier = (Supplier) table.getValueAt(row, 1);

                Integer response = JOptionPane.showConfirmDialog(null, "You are about to remove " + supplier.getName() + " supplier. Do you want to continue?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    supplierJpaController.destroy(supplier.getId());

                    JOptionPane.showMessageDialog(null, "Supplier successfully deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SupplierImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}