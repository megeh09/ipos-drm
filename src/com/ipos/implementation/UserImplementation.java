/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.User;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.jpa.controller.exceptions.NonexistentEntityException;
import com.ipos.view.settings.user.AddUserDialog;
import com.ipos.view.settings.user.UpdateUserDialog;
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
public class UserImplementation {

    protected EntityManagerFactory emf;
    protected UserJpaController userJpaController;

    public UserImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "Fullname",
            "Username",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<User> users = userJpaController.findUserEntities();

        for (User user : users) {
            int i = 0;
            Object[] newRow = new Object[4];

            newRow[i++] = user;
            newRow[i++] = user.getUsername();
            newRow[i++] = DateUtil.toMMMMddyyyyFormat(user.getDate());
            newRow[i++] = userJpaController.findUser(user.getFKcreatedByUserId()).getFullname();

            model.addRow(newRow);
        }

        return model;
    }

    public void add() {
        AddUserDialog dialog = new AddUserDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            User user = (User) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            UpdateUserDialog dialog = new UpdateUserDialog(null, true, this.emf, user);

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
                User user = (User) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);

                Integer response = JOptionPane.showConfirmDialog(null, "You are about to remove " + user.getFullname() + " user. Do you want to continue?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    userJpaController.destroy(user.getId());

                    JOptionPane.showMessageDialog(null, "User successfully deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UserImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
