/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.util;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author megeh
 */
public class JTableSearchUtil {

    public static void performSearch(JTable table, String text) {
        // Non database search.
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(sorter);

        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + text + ".*"));
        }
    }
}
