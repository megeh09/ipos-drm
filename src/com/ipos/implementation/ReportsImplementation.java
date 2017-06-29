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
import java.util.Date;
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
public class ReportsImplementation {

    protected EntityManagerFactory emf;
    protected SalesJpaController salesJpaController;
    protected StockJpaController stockJpaController;
    protected SupplierJpaController supplierJpaController;
    protected ItemJpaController itemJpaController;
    protected UserJpaController userJpaController;

    public ReportsImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        salesJpaController = new SalesJpaController(emf);
        stockJpaController = new StockJpaController(emf);
        supplierJpaController = new SupplierJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getSalesFromToTableModel(Date from, Date to) {
        Object[] columnName = {
            "Item",
            "Quantity",
            "Unit Price",
            "Total",
            "Sales On",
            "Sales By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Sales> sales = salesJpaController.findSalesFromTo(from, to);

        try {
            for (Sales sale : sales) {
                int i = 0;
                Object[] newRow = new Object[6];
                Stock stock = stockJpaController.findStock(sale.getFKstockId());

                newRow[i++] = itemJpaController.findItem(stock.getFKitemId());
                newRow[i++] = sale.getQuantity();
                newRow[i++] = sale.getUnitPrice();
                newRow[i++] = sale.getTotalAmount();
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(sale.getDate());
                newRow[i++] = userJpaController.findUser(sale.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportsImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public TableModel getStocksFromToTableModel(Date from, Date to) {
        Object[] columnName = {
            "Code",
            "Item",
            "Supplier",
            "SC Number",
            "Quantity",
            "Unit Price",
            "Expiry Date",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Stock> stocks = stockJpaController.findStocksFromTo(from, to);

        try {
            for (Stock stock : stocks) {
                int i = 0;
                Object[] newRow = new Object[9];

                newRow[i++] = stock;
                newRow[i++] = itemJpaController.findItem(stock.getFKitemId());
                newRow[i++] = supplierJpaController.findSupplier(stock.getFKsupplierId());
                newRow[i++] = stock.getStockCardNumber();
                newRow[i++] = stock.getQuantity();
                newRow[i++] = stock.getUnitPrice();
                newRow[i++] = (stock.getExpiryDate().compareTo(DateUtil.parseDate("1989-03-13").getTime()) > 0) ? DateUtil.toMMMMddyyyyFormat(stock.getExpiryDate()) : "";
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(stock.getDate());
                newRow[i++] = userJpaController.findUser(stock.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(StockImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
}
