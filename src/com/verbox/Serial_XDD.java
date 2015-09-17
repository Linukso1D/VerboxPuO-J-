/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxxl
 */
public class Serial_XDD {
     private static String mystr;
    public static String Serial_XDDGet()
    {
         mystr ="";
        
                    String cmd = "wmic diskdrive get serialnumber";
                     Process pr = null;
                     try {
                            String line=null;
                     pr = Runtime.getRuntime().exec(cmd);



                     BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                     
                     in.readLine();//pro4itaet pustuju stoku ,no ne vivedet ejo
                     while ((line = in.readLine()) != null) {
                     mystr+=line;
                     }
                     String NotSpace = mystr.replaceAll(" ", "");
                     mystr=NotSpace;
                       return NotSpace; 
                     } catch (Exception e) {
                     e.printStackTrace();
                     } 
                     System.out.println("MYSTR= " + mystr);
        return mystr;
                        
}
   public static String Serial_XDDGetHash() throws UnsupportedEncodingException{  
try {
    String hashtext="";
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            try {
                m.update(mystr.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] digest = m.digest();

            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println(hashtext);
            System.out.println("HASH= " + hashtext);
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "123456789101112151617181926";
   }
}
