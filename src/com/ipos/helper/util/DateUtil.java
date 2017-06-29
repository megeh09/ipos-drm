/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author megeh
 */
public class DateUtil {

    public static String toSQLFormat(Date date) {
        return String.valueOf(new java.sql.Date(date.getTime()));
    }

    public static String toMMMMddyyyyFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        
        return dateFormat.format(date);
    }

    public static Date firstDateOfMonth() {
        Calendar c = Calendar.getInstance();
        
        c.set(Calendar.DAY_OF_MONTH, 1);
        
        return c.getTime();
    }

    public static Date current() {
        Calendar c = Calendar.getInstance();
        
        return c.getTime();
    }

    public static Calendar parseDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = dateFormat.parse(strDate);
        Calendar c = Calendar.getInstance();
        
        c.setTime(convertedDate);

        return c;
    }
}
