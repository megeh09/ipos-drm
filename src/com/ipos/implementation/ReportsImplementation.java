/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.implementation;

import com.ipos.entity.Sales;
import com.ipos.entity.Stock;
import com.ipos.entity.StockWithdrawal;
import com.ipos.helper.util.DateUtil;
import com.ipos.jpa.controller.ItemJpaController;
import com.ipos.jpa.controller.PersonnelJpaController;
import com.ipos.jpa.controller.SalesJpaController;
import com.ipos.jpa.controller.StockJpaController;
import com.ipos.jpa.controller.StockWithdrawalJpaController;
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
    protected StockWithdrawalJpaController stockWithdrawalJpaController;
    protected PersonnelJpaController personnelJpaController;
    protected ItemJpaController itemJpaController;
    protected UserJpaController userJpaController;

    public ReportsImplementation(EntityManagerFactory emf) {
        this.emf = emf;

        salesJpaController = new SalesJpaController(emf);
        stockJpaController = new StockJpaController(emf);
        stockWithdrawalJpaController = new StockWithdrawalJpaController(emf);
        personnelJpaController = new PersonnelJpaController(emf);
        itemJpaController = new ItemJpaController(emf);
        userJpaController = new UserJpaController(emf);
    }

    public TableModel getSalesReportTableModel(Date from, Date to, Integer stockId, String bodega) {
        Object[] columnName = {
            "Item",
            "Quantity",
            "Unit Price",
            "Total",
            "Sales On",
            "Sales By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Sales> sales = (stockId == 0 ? salesJpaController.findSalesFromTo(from, to) : salesJpaController.findSalesFromToAndStock(from, to, stockId));

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
            Logger.getLogger(ReportsImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public TableModel getStocksReportTableModel(Date from, Date to, Integer stockId) {
        Object[] columnName = {
            "Code",
            "Item",
            "Personnel",
            "SC Number",
            "Quantity",
            "Date Ceated",
            "Created By"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<Stock> stocks = (stockId == 0 ? stockJpaController.findStocksFromTo(from, to) : stockJpaController.findStocksFromToAndStock(from, to, stockId));

        try {
            for (Stock stock : stocks) {
                int i = 0;
                Object[] newRow = new Object[7];

                newRow[i++] = stock.getCode();
                newRow[i++] = stock;
                newRow[i++] = personnelJpaController.findPersonnel(stock.getFKpersonnelId());
                newRow[i++] = stock.getStockCardNumber();
                newRow[i++] = stock.getQuantity();
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(stock.getDate());
                newRow[i++] = userJpaController.findUser(stock.getFKcreatedByUserId()).getFullname();

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportsImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public TableModel getStockOutReportTableModel(Date from, Date to, Integer stockId) {
        Object[] columnName = {
            "Item",
            "Personnel",
            "Purpose",
            "Quantity",
            "Date Withdrawn"
        };

        DefaultTableModel model = new DefaultTableModel(null, columnName);
        List<StockWithdrawal> stockWithdrawals = (stockId == 0 ? stockWithdrawalJpaController.findStockWithdrawalFromTo(from, to) : stockWithdrawalJpaController.findStockWithdrawalFromToAndStock(from, to, stockId));

        try {
            for (StockWithdrawal stockWithdrawal : stockWithdrawals) {
                int i = 0;
                Object[] newRow = new Object[5];

                newRow[i++] = stockWithdrawal.getStock();
                newRow[i++] = stockWithdrawal.getPersonnel().getFullname();
                newRow[i++] = stockWithdrawal.getPurpose();
                newRow[i++] = stockWithdrawal.getQuantity();
                newRow[i++] = DateUtil.toMMMMddyyyyFormat(stockWithdrawal.getDate());

                model.addRow(newRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportsImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
}
