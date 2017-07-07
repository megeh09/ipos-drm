/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Sales;
import com.ipos.entity.Stock;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.SalesJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.jpa.controller.SupplierJpaController;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.view.sales.AcceptPaymentDialog;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author megeh
 */
public class SalesImplementation {

    protected EntityManagerFactory emf;
    protected SalesJpaController salesJpaController;
    protected StockJpaController stockJpaController;
    protected SupplierJpaController supplierJpaController;
    protected ItemJpaController itemJpaController;
    protected UserJpaController userJpaController;

    public SalesImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        salesJpaController = new SalesJpaController(emf);
        stockJpaController = new StockJpaController(emf);
        supplierJpaController = new SupplierJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getTableModel(String bodega) {
        Object[] columnName = {
            "Item",
            "Quantity",
            "Unit Price",
            "Total",
            "Sales On",
            "Sales By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Sales> sales = salesJpaController.findSalesEntities();

        try {
            for (Sales sale : sales) {
                int i = 0;
                Object[] newRow = new Object[6];
                Stock stock = stockJpaController.findStock(sale.getStock().getId());

                newRow[i++] = itemJpaController.findItem(stock.getItem().getId(), bodega);
                newRow[i++] = sale.getQuantity();
                newRow[i++] = sale.getUnitPrice();
                newRow[i++] = sale.getTotalAmount();
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(sale.getDate());
                newRow[i++] = userJpaController.findUser(sale.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(SalesImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public void accept() {
        AcceptPaymentDialog dialog = new AcceptPaymentDialog(null, true, this.emf);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
