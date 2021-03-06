/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.view.stock.adjust;

import com.ipos.entity.Item;
import com.ipos.entity.Personnel;
import com.ipos.entity.Stock;
import com.ipos.helper.util.DecimalFormatterUtil;
import com.ipos.helper.util.JComboBoxModelUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.PersonnelJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.start.IPOS;
import com.jidesoft.swing.ComboBoxSearchable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author megeh
 */
public class StockAdjustDialog extends javax.swing.JDialog {

    private StockJpaController controller;
    private PersonnelJpaController personnelJpaController;
    private ItemJpaController itemJpaController;
    private DecimalFormatterUtil dfNoComma;
    private DecimalFormatterUtil dfWithComma;
    private Stock stock;

    /**
     * Creates new form StockInDialog
     *
     * @param parent
     * @param modal
     * @param emf
     * @param s
     */
    public StockAdjustDialog(java.awt.Frame parent, boolean modal, EntityManagerFactory emf, Stock s) {
        super(parent, modal);
        initComponents();
        initElements(emf, s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();
        bottomSeparator = new javax.swing.JSeparator();
        codeLabel = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        itemComboBox = new javax.swing.JComboBox();
        itemLabel = new javax.swing.JLabel();
        quantityFormattedTextField = new javax.swing.JFormattedTextField();
        personnelLabel = new javax.swing.JLabel();
        personnelComboBox = new javax.swing.JComboBox();
        bottomPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stock Adjust");

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mainPanel.add(topPanel, java.awt.BorderLayout.NORTH);

        codeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codeLabel.setText("Code");

        codeTextField.setEditable(false);
        codeTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        quantityLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantityLabel.setText("Quantity");

        itemComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        itemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        itemComboBox.setEnabled(false);
        itemComboBox.setName("approvedBy"); // NOI18N
        itemComboBox.setPreferredSize(new java.awt.Dimension(205, 27));

        itemLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        itemLabel.setText("Item");

        quantityFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        quantityFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        quantityFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantityFormattedTextField.setName("assetValue"); // NOI18N
        quantityFormattedTextField.setPreferredSize(new java.awt.Dimension(6, 27));

        personnelLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        personnelLabel.setText("Personnel");

        personnelComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        personnelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        personnelComboBox.setName("approvedBy"); // NOI18N
        personnelComboBox.setPreferredSize(new java.awt.Dimension(205, 27));

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottomSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addComponent(itemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addComponent(codeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(centerPanelLayout.createSequentialGroup()
                                .addComponent(personnelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(personnelComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(centerPanelLayout.createSequentialGroup()
                        .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 156, Short.MAX_VALUE))))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personnelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personnelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(bottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setPreferredSize(new java.awt.Dimension(400, 50));

        addButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addButton.setText("UPDATE");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cancelButton.setText("CANCEL");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        mainPanel.add(bottomPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Stock entity = stock;

        try {
            // Check if it has unit and supplier selected.
            if (itemComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Error, please select item.", "Failed", JOptionPane.ERROR_MESSAGE);

                return;
            }

            if (personnelComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Error, please select supplier.", "Failed", JOptionPane.ERROR_MESSAGE);

                return;
            }

            entity.setCode(codeTextField.getText());
            entity.setStockCardNumber("");
            entity.setQuantity(stock.getQuantity().add(dfNoComma.format(quantityFormattedTextField.getText())));
            entity.setUnitPrice(BigDecimal.ZERO);
            entity.setFKpersonnelId(((Personnel) personnelComboBox.getSelectedItem()).getId());
            entity.setItem((Item) itemComboBox.getSelectedItem());
            entity.setFKcreatedByUserId(IPOS.currentUser.getId());

            controller.edit(entity);

            JOptionPane.showMessageDialog(null, "Stock successfully updated.", "Sucess", JOptionPane.INFORMATION_MESSAGE);

            hideThis();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, stock not updated.", "Failed", JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(StockAdjustDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        hideThis();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JSeparator bottomSeparator;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTextField;
    private javax.swing.JComboBox itemComboBox;
    private javax.swing.JLabel itemLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox personnelComboBox;
    private javax.swing.JLabel personnelLabel;
    public javax.swing.JFormattedTextField quantityFormattedTextField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    private void initElements(EntityManagerFactory emf, Stock s) {
        controller = new StockJpaController(emf);
        personnelJpaController = new PersonnelJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        dfNoComma = new DecimalFormatterUtil("#####0.00");
        dfWithComma = new DecimalFormatterUtil();
        stock = s;

        // Set combo box.
        personnelComboBox.setModel(JComboBoxModelUtil.getPersonnelModel("Select Personnel", personnelJpaController.findPersonnelEntities()));
        itemComboBox.setModel(JComboBoxModelUtil.getItemModel("Select Item", itemJpaController.findItemEntities()));
        
        // Set combo box searchable.
        ComboBoxSearchable s1 = new ComboBoxSearchable(personnelComboBox);
        ComboBoxSearchable s2 = new ComboBoxSearchable(itemComboBox);

        // Set entity to be updated.
        codeTextField.setText(stock.getCode());
        itemComboBox.setSelectedItem(itemJpaController.findItem(stock.getItem().getId()));
        personnelComboBox.setSelectedItem(personnelJpaController.findPersonnel(stock.getFKpersonnelId()));
        quantityFormattedTextField.setText("");
        
        // Request focus.
        quantityFormattedTextField.requestFocus();
    }

    private void hideThis() {
        this.setVisible(false);
    }
}
