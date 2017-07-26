/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Personnel;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.PersonnelJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import com.ipos.view.settings.personnel.AddPersonnelDialog;
import com.ipos.view.settings.personnel.UpdatePersonnelDialog;
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
public class PersonnelImplementation {

    protected EntityManagerFactory emf;
    protected PersonnelJpaController personnelJpaController;
    protected UserJpaController userJpaController;

    public PersonnelImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        personnelJpaController = new PersonnelJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "Fullname",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Personnel> personnels = personnelJpaController.findPersonnelEntities();

        for (Personnel personnel : personnels) {
            int i = 0;
            Object[] newRow = new Object[3];

            newRow[i++] = personnel;
            newRow[i++] = DateUtil.toMMMMddyyyyFormat(personnel.getDate());
            newRow[i++] = userJpaController.findUser(personnel.getFKcreatedByUserId()).getFullname();

            model.addRow(newRow);
        }

        return model;
    }

    public void add() {
        AddPersonnelDialog dialog = new AddPersonnelDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Personnel personnel = (Personnel) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            UpdatePersonnelDialog dialog = new UpdatePersonnelDialog(null, true, this.emf, personnel);

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
                Personnel personnel = (Personnel) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);

                Integer response = JOptionPane.showConfirmDialog(null, "You are about to remove " + personnel.getFullname() + " personnel. Do you want to continue?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    personnelJpaController.destroy(personnel.getId());

                    JOptionPane.showMessageDialog(null, "Personnel successfully deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PersonnelImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
