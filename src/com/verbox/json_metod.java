/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.Serial_XDD.Serial_XDDGet;
import static com.verbox.Serial_XDD.Serial_XDDGetHash;
import static com.verbox.sqlite_metod.GetMd5;
import static com.verbox.sqlite_metod.cureList_GetCash;
import static com.verbox.sqlite_metod.cureList_GetLog;
import static com.verbox.sqlite_metod.cureList_GetPass;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author maxxl
 */
public class json_metod {

    private static String USER_AGENT = "Mozilla/5.0";
    


//запрос на получение пустого списка пользователей--------

    /**
     *
     * @return
     * @throws SQLException
     */
        public static JSONObject GetPairLogin() throws SQLException   {
        try {
        Serial_XDDGet();
        String tmp = Serial_XDDGetHash();
        
        JSONObject obparams = new JSONObject();
        obparams.put("patterns", "2015-03-05 13:00:53");
        JSONObject obj = new JSONObject();
        obj.put("cash_id", cureList_GetCash());
        obj.put("cash_password",   tmp);
        obj.put("cashier_id", "1");
        obj.put("cashier_password", "117475e8ed646af06790a513fd91f0f");
        obj.put("action_name", "get_info");
        obj.put("params", obparams);
        obj.put("lastmes", "2020-03-05 13:54:53");
        return obj;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex+"getPairLogin");
        }
        return null;
    }
        
    /**
     *
     * @return
     * @throws SQLException
     */
    public static JSONObject GetInfoJS() throws SQLException   {
        try {
        Serial_XDDGet();
        String tmp = Serial_XDDGetHash();
        
        JSONObject obparams = new JSONObject();
        obparams.put("patterns", "2015-03-05 13:00:53");
        JSONObject obj = new JSONObject();
        obj.put("cash_id", cureList_GetCash());
        obj.put("cash_password",   tmp);
        obj.put("cashier_id", "1");
        obj.put("cashier_password", "117475e8ed646af06790a513fd91f0fd");
        obj.put("action_name", "get_info");
        obj.put("params", obparams);
        obj.put("lastmes", "2020-03-05 13:54:53");
        return obj;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex+"getPairLogin");
        }
        return null;
    }
                
                
                
  //Отправка и получение JSON-------- 

    /**
     *
     * @param obj
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws InterruptedException
     * @throws SQLException
     */
     public static JSONObject SendPost(JSONObject obj) throws IOException, org.json.simple.parser.ParseException, InterruptedException, SQLException {
         if(!CheckInet())
         {
             JSONObject js = new JSONObject();
             showMessageDialog(null, "Отсутствует соединение с интернетом.");
         return js;
         }
        URL url = new URL("http://verbox.biz/application/apimanager");
        Base64 b = new Base64();
        String encoding = b.encodeAsString(new String(cureList_GetLog()+":"+cureList_GetPass()).getBytes());
        
                
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);

   
        String urlParameters = obj.toString();
        // Send post request
        connection.setDoOutput(true);
        
        
        

        
         
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        
        int responseCode = connection.getResponseCode();
         
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        System.out.println(response.toString());
 

 
     //JSONObject jsonObject = jsonArray.toJSONObject(names);
    JSONParser parser = new JSONParser();
    Object la = parser.parse(response.toString());
    JSONObject jsonObject = (JSONObject) la;

     
        return jsonObject;
         }
    
    

     //проверка интернета

    /**
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
     public static boolean CheckInet() throws IOException, InterruptedException
     {
        Process proc = Runtime.getRuntime().exec("ping -n 1 " + "8.8.8.8");
        boolean reachable = (proc.waitFor()==0);
        System.out.println(reachable ? "Host is reachable" : "Host is NOT reachable");
         
        return reachable;
        
     }
     
     
     
}
