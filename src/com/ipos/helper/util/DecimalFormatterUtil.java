/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author megeh
 */
public class DecimalFormatterUtil {

    private String pattern = "###,##0.00";

    /**
     * Instantiate formatter using default pattern, ###,##0.00.
     *
     */
    public DecimalFormatterUtil() {
    }

    /**
     * Instantiate formatter using given pattern.
     *
     * @param pattern String format pattern.
     */
    public DecimalFormatterUtil(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Rounds the given value to the nearest hundred.
     *
     * @param value Double value to be rounded of.
     * @param pow Integer value for the power of.
     * @return Rounded double value.
     */
    private double round(double value, int pow) {
        double p = (double) Math.pow(10, pow);
        value = value * p;
        double tmp = Math.round(value);

        return (double) tmp / p;
    }

    /**
     * Formats the given decimal value.
     *
     * @param value BigDecimal value.
     * @return Formatted String.
     */
    public String format(BigDecimal value) {
        return new DecimalFormat(pattern).format(round(value.doubleValue(), 2));
    }

    /**
     * Formats and removes characters from the given String decimal value.
     *
     * @param value String decimal value.
     * @return Formatted big decimal value.
     */
    public BigDecimal format(String value) {
        String regex = "(?<=[\\d])(,)(?=[\\d])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        BigDecimal decimal = BigDecimal.valueOf(Double.parseDouble(m.replaceAll("")));

        return BigDecimal.valueOf(Double.parseDouble(new DecimalFormat(pattern).format(round(decimal.doubleValue(), 2))));
    }
}
