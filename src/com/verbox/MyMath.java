/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author maxxl
 */
public class MyMath {
    
    
public static double round(double number, int scale) {
double newDouble = new BigDecimal(number).setScale(scale, RoundingMode.DOWN).doubleValue();
return newDouble;
}
}
