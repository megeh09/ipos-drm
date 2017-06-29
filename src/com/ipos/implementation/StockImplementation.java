/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Stock;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.jpa.controller.SupplierJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.view.stock.adjust.StockAdjustDialog;
import com.ipos.view.stock.in.StockInDialog;
import com.ipos.view.stock.out.StockWithdrawalDialog;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
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
public class StockImplementation {

    protected EntityManagerFactory emf;
    protected StockJpaController stockJpaController;
    protected SupplierJpaController supplierJpaController;
    protected ItemJpaController itemJpaController;
    protected UserJpaController userJpaController;

    public StockImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        stockJpaController = new StockJpaController(emf);
        supplierJpaController = new SupplierJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel() {
        Object[] columnName = {
            "Code",
            "Item",
            "Supplier",
            "Quantity",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Stock> stocks = stockJpaController.findStockEntities();

        try {
            for (Stock stock : stocks) {
                int i = 0;
                Object[] newRow = new Object[6];

                newRow[i++] = stock;
                newRow[i++] = itemJpaController.findItem(stock.getFKitemId());
                newRow[i++] = supplierJpaController.findSupplier(stock.getFKsupplierId());
                newRow[i++] = stock.getQuantity();
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(stock.getDate());
                newRow[i++] = userJpaController.findUser(stock.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }

            // Sort column.
//            Vector data = model.getDataVector();
//            Collections.sort(data, new ColumnSorter(1));
//            model.fireTableStructureChanged();
        } catch (Exception ex) {
            Logger.getLogger(StockImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public void add() {
        StockInDialog dialog = new StockInDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void update(JXTable table) {
        Integer row = table.getSelectedRow();

        if (row > -1) {
            Stock stock = (Stock) table.getValueAt(row, 0);
            StockAdjustDialog dialog = new StockAdjustDialog(null, true, this.emf, stock);

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to be updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void withdrawal() {
        StockWithdrawalDialog dialog = new StockWithdrawalDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    class ColumnSorter implements Comparator {

        int colIndex;

        ColumnSorter(int colIndex) {
            this.colIndex = colIndex;
        }

        @Override
        public int compare(Object a, Object b) {
            Vector v1 = (Vector) a;
            Vector v2 = (Vector) b;
            Object o1 = v1.get(colIndex);
            Object o2 = v2.get(colIndex);

            if (o1 instanceof String && ((String) o1).length() == 0) {
                o1 = null;
            }
            if (o2 instanceof String && ((String) o2).length() == 0) {
                o2 = null;
            }

            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            } else if (o1 instanceof Comparable) {

                return ((Comparable) o1).compareTo(o2);
            } else {

                return o1.toString().compareTo(o2.toString());
            }
        }
    }
}
