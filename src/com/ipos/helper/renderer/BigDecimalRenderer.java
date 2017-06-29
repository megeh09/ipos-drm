/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author megeh
 */
public class BigDecimalRenderer extends DefaultTableCellRenderer {

    NumberFormat currencyFormat;

    public BigDecimalRenderer(NumberFormat cf) {
        currencyFormat = cf;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        BigDecimal _value = BigDecimal.ZERO;

        if (value instanceof Double) {
            _value = new BigDecimal((Double) value);
        } else if (value instanceof Integer) {
            _value = new BigDecimal((Integer) value);
        } else if (value instanceof BigDecimal) {
            _value = (BigDecimal) value;
        } else if (value instanceof String) {
            try {
                _value = new BigDecimal((String) value);
            } catch (Exception ex) {
            }
        }

        String cellVal = (value == null || _value.compareTo(BigDecimal.ZERO) == 0) ? "" : currencyFormat.format(_value);
        JLabel valLabel = new JLabel(cellVal, SwingConstants.RIGHT);

        if (isSelected) {
            valLabel.setBackground(table.getSelectionBackground());
            valLabel.setForeground(Color.WHITE);
            valLabel.setOpaque(true);
        } else {
            valLabel.setForeground(Color.BLACK);
            valLabel.setOpaque(true);
            valLabel.setBackground(Color.WHITE);
        }

        valLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        return valLabel;
    }
}
