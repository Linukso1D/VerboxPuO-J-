/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author maxxl
 */
public class DecodeUTF {

    /**
     *
     * @param a
     * @return 
     * @throws UnsupportedEncodingException
     */
    public static String DecodeUTF(String a) throws UnsupportedEncodingException
    {    
    byte[] utf8Bytes = a.getBytes("UTF8");
    String out = new String(utf8Bytes, "UTF8");
	 System.out.println(out);
    return out;
    }
}
