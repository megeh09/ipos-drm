/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Unit;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.UnitJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import com.ipos.view.settings.unit.AddUnitDialog;
import com.ipos.view.settings.unit.UpdateUnitDialog;
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
public class UnitImplementation {

    protected EntityManagerFactory emf;
    protected UnitJpaController unitJpaController;
    protected UserJpaController userJpaController;

    public UnitImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        unitJpaController = new UnitJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "Code",
            "Description",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Unit> units = unitJpaController.findUnitEntities();

        for (Unit unit : units) {
            int i = 0;
            Object[] newRow = new Object[4];

            newRow[i++] = unit;
            newRow[i++] = unit.getDescription();
            newRow[i++] = DateUtil.toMMMMddyyyyFormat(unit.getDate());
            newRow[i++] = userJpaController.findUser(unit.getFKcreatedByUserId()).getFullname();

            model.addRow(newRow);
        }

        return model;
    }

    public void add() {
        AddUnitDialog dialog = new AddUnitDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Unit unit = (Unit) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            UpdateUnitDialog dialog = new UpdateUnitDialog(null, true, this.emf, unit);

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
                Unit unit = (Unit) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);

                Integer response = JOptionPane.showConfirmDialog(null, "You are about to remove " + unit.getDescription() + "(" + unit.getCode() + ") unit. Do you want to continue?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    unitJpaController.destroy(unit.getId());

                    JOptionPane.showMessageDialog(null, "Unit successfully deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UnitImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
