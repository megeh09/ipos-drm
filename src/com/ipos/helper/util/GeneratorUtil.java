/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.helper.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megeh
 */
public class GeneratorUtil {

    public static String generateCode(Date now, String code, String prefix) {
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        Integer y1 = Integer.valueOf(formatYear.format(now));

        try {
            if (code == null) {
                // Start new work order number.
                // Format XX-YYYY-XXXX.
                code = prefix.concat("-").concat(String.valueOf(y1)).concat("-0001");
            } else {
                // Continue work order number sequence.
                // Format XX-YYYY-XXXX.
                Integer y2 = Integer.valueOf(code.substring(3, 7));
                Integer c1 = Integer.valueOf(code.substring(8)) + 1;
                String c2 = "000".concat(String.valueOf(c1));

                // Reset counter to 1 every year.
                if (y1 > y2) {
                    // Reset.
                    code = prefix.concat("-").concat(String.valueOf(y1)).concat("-0001");
                } else {
                    // Continue.
                    code = prefix.concat("-")
                            .concat(String.valueOf(y1))
                            .concat("-")
                            .concat(c2.substring(c2.length() - 4, c2.length()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneratorUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;
    }
}
