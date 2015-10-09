/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author maxxl
 */
public class MyMath {
    
    /**
     *
     * @param number
     * @param scale
     * @return
     */
    public static double round(double number, int scale) {
return Math.round(number * 100.0) / 100.0;
}

//ищет дату из листа в скобок



}
