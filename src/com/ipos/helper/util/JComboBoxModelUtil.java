/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.util;

import com.ipos.entity.Item;
import com.ipos.entity.Personnel;
import com.ipos.entity.Stock;
import com.ipos.entity.Supplier;
import com.ipos.entity.Unit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author megeh
 */
public class JComboBoxModelUtil {

    public static DefaultComboBoxModel getUnitModel(String label, List<Unit> units) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model = setComboLabel(model, label);

        for (Unit user : units) {
            model.addElement(user);
        }

        return model;
    }

    public static DefaultComboBoxModel getSupplierModel(String label, List<Supplier> suppliers) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model = setComboLabel(model, label);

        for (Supplier supplier : suppliers) {
            model.addElement(supplier);
        }

        return model;
    }

    public static DefaultComboBoxModel getItemModel(String label, List<Item> items) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model = setComboLabel(model, label);

        for (Item item : items) {
            model.addElement(item);
        }

        return model;
    }

    public static DefaultComboBoxModel getStockModel(String label, List<Stock> stocks) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model = setComboLabel(model, label);

        for (Stock stock : stocks) {
            model.addElement(stock);
        }

        return model;
    }

    public static DefaultComboBoxModel getPersonnelModel(String label, List<Personnel> items) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model = setComboLabel(model, label);

        for (Personnel personnel : items) {
            model.addElement(personnel);
        }

        return model;
    }

    static DefaultComboBoxModel setComboLabel(DefaultComboBoxModel model, String label) {
        if (hasLabel(label)) {
            model.addElement(label);
        }

        return model;
    }

    static boolean hasLabel(String label) {
        return label.length() > 0;
    }
}
