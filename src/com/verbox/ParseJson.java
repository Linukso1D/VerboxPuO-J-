/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.DecodeUTF.DecodeUTF;
import static com.verbox.ErrorList.DesctiptError;
import static com.verbox.sqlite_metod.Insert;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author maxxl
 */
public class ParseJson {

    private String ErrorCode;
    private String ErrorMessage;

    //курсы валют
    ArrayList Currencies;
    String TimetoFinish;
    
    // для левой логинизации касиры
    ArrayList CashierNameList;
    
   

    public ArrayList getCashierNameList() {
        if (!CashierNameList.isEmpty()) {
            return CashierNameList;
        }
        return null;
    }

    //инициализация обекта наверное для ероров
    JSONObject PJjson;

    //конструктор
    public ParseJson(JSONObject json) {
        //Ошибки
        if(json.isEmpty())
        {
        System.out.println(json.toJSONString());
        }
        else
        {
        this.PJjson = json;
        
        
        // Ловим ошибки
        if(PJjson.get("error")!=null)
        {
        JSONObject Error = (JSONObject) PJjson.get("error");
        setErrorCode((String) Error.get("code"));
        setErrorMessage((String) Error.get("message"));
       if (!"not_cashier".equals(getErrorMessage()))
       {showMessageDialog(null, DesctiptError(getErrorMessage()));}
        }      
        else{
                                   /* if(json.get("params")!=null)
                                    {
                                    JSONArray msg = (JSONArray) json.get("params");
                                    if(msg.size()==1)
                                    {
                                        showMessageDialog(null,msg.toJSONString()+"Выполнено ");
                                    }
                                    }      */
                        //Забираем курсы с сервака            
                                    if(json.get("params")!=null)
                                    {
                                    JSONObject tmp = (JSONObject) json.get("params");
                                    TimetoFinish =  (String) json.get("date_expiration");
                                    JSONArray msg = (JSONArray) tmp.get("currencies"); 
                                    Currencies = new ArrayList();
                                    for (int i = 0; i < msg.size(); i++) {
                                    JSONObject mas2 = (JSONObject) msg.get(i);
                                    Currencies.add(mas2.get("course_buy"));
                                    Currencies.add(mas2.get("course_nbu"));
                                    Currencies.add(mas2.get("course_sale"));
                                    Currencies.add(mas2.get("currency_code"));
                                    Currencies.add(mas2.get("currency_id"));
                                    Currencies.add(mas2.get("currency_name"));
                                    Currencies.add(mas2.get("order_id"));
                                    Currencies.add(mas2.get("quantity"));
                                    Currencies.add(mas2.get("quantity_text"));
                                        System.out.println("Currencies" + mas2.get("course_buy") );
                                    }
                                    } 
              
                                    
                                    
                                    
            }
        
        
        
        
        
        
        
        
        
        
//"super":false,"cashier_id":4,"cash_id":1,"cashier_password":"c4ca4238a0b923820dcc509a6f75849b","surname":"Петров","last_name":"Александрович","first_name":"Петя"
                                    //Касиры список      
                                    if(json.get("cashiers")!=null)
                                    {
                                    CashierNameList = new ArrayList();
                                    if (getErrorCode().equals("08")) {
                                    } else {
                                    JSONArray msg = (JSONArray) json.get("cashiers");
                                      for (int i = 0; i < msg.size(); i++) {
                                    JSONObject mas2 = (JSONObject) msg.get(i);
                                    CashierNameList.add(mas2.get("cashier_id"));
                                    CashierNameList.add(mas2.get("cashier_password"));
                                    CashierNameList.add(mas2.get("cash_id"));
                                    CashierNameList.add(mas2.get("super"));
                                    CashierNameList.add(mas2.get("surname"));
                                    CashierNameList.add(mas2.get("last_name"));
                                    CashierNameList.add(mas2.get("first_name"));
                                    }
                                    }
                                    }
                                    
                                    
                                   
                                    
                 
                 
                 
        }
    }
    
    //---- записывает инфу в бд
    public static void  get_info(JSONObject json) throws SQLException, UnsupportedEncodingException
    {
         //getinfo 
    ArrayList info,infonamespace;
        info = new ArrayList();
        infonamespace = new ArrayList();
        if(json.isEmpty())
        {
        System.out.println(json.toJSONString());
        }
        else
        {
   //"super":false,"cashier_id":4,"cash_id":1,"cashier_password":"c4ca4238a0b923820dcc509a6f75849b","surname":"Петров","last_name":"Александрович","first_name":"Петя"
            JSONObject jo = (JSONObject) json.get("params");
            JSONObject enterprise = (JSONObject) jo.get("enterprise");
                info.add(enterprise.get("bookk_fio"));
                info.add(enterprise.get("bookk_first_name"));
                info.add(enterprise.get("bookk_last_name"));
                info.add(enterprise.get("bookk_surname"));
                info.add(enterprise.get("dir_fio"));
                info.add(enterprise.get("dir_first_name"));
                info.add(enterprise.get("dir_last_name"));
                info.add(enterprise.get("dir_surname"));
                info.add(enterprise.get("enterprise_full_name"));
                info.add(enterprise.get("enterprise_mfo"));
                info.add(enterprise.get("enterprise_name"));
                info.add(enterprise.get("enterprise_okpo"));
                info.add(enterprise.get("enterprise_short_name"));
                info.add(enterprise.get("logo_full_image"));
                info.add(enterprise.get("logo_short_image"));
                info.add(enterprise.get("nat_city"));
                info.add(enterprise.get("nat_city_code"));
                info.add(enterprise.get("nat_house"));
                info.add(enterprise.get("nat_office"));
                info.add(enterprise.get("nat_street"));
                info.add(enterprise.get("payment_account"));
                info.add(enterprise.get("stamp_image"));
                info.add(enterprise.get("ur_city"));
                info.add(enterprise.get("ur_city_code"));
                info.add(enterprise.get("ur_house"));
                info.add(enterprise.get("ur_office"));
                info.add(enterprise.get("ur_street"));
                //--    
                infonamespace.add("bookk_fio");
                infonamespace.add("bookk_first_name");
                infonamespace.add("bookk_last_name");
                infonamespace.add("bookk_surname");
                infonamespace.add("dir_fio");
                infonamespace.add("dir_first_name");
                infonamespace.add("dir_last_name");
                infonamespace.add("dir_surname");
                infonamespace.add("enterprise_full_name");
                infonamespace.add("enterprise_mfo");
                infonamespace.add("enterprise_name");
                infonamespace.add("enterprise_okpo");
                infonamespace.add("enterprise_short_name");
                infonamespace.add("logo_full_image");
                infonamespace.add("logo_short_image");
                infonamespace.add("nat_city");
                infonamespace.add("nat_city_code");
                infonamespace.add("nat_house");
                infonamespace.add("nat_office");
                infonamespace.add("nat_street");
                infonamespace.add("payment_account");
                infonamespace.add("stamp_image");
                infonamespace.add("ur_city");
                infonamespace.add("ur_city_code");
                infonamespace.add("ur_house");
                infonamespace.add("ur_office");
                infonamespace.add("ur_street");
                
                
                
            boolean flag = Insert("info", infonamespace, info);
            //--  
        }
        /*  for (Object item : CashierNameList) 
                    {
                        System.out.println("Name of user  "+ item);
                    }*/
        
    }
//-----
    
    
    private void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    private void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
    
    //запись курсов валют в бд 
    public boolean Write_CurrenciesToSQLite() throws SQLException {
        boolean flag = false;
        if (!Currencies.isEmpty()) {

            int i = 0;
            while (i < Currencies.size()) {

                ArrayList key = new ArrayList();
                key.add("course_buy");
                key.add("course_nbu");
                key.add("course_sale");
                key.add("currency_code");
                key.add("currency_id");
                key.add("currency_name");
                key.add("order_id");
                key.add("quantity");
                key.add("quantity_text");
                key.add("TimetoFinish");                   
                
                
                ArrayList value = new ArrayList();
                value.add(Currencies.get(i).toString());
                value.add(Currencies.get(i+1).toString());
                value.add(Currencies.get(i+2).toString());
                value.add(Currencies.get(i+3).toString());
                value.add(Currencies.get(i+4).toString());
                value.add(Currencies.get(i+5).toString());
                value.add(Currencies.get(i+6).toString());
                value.add(Currencies.get(i+7).toString());
                value.add(Currencies.get(i+8).toString());
                value.add(TimetoFinish);
                flag = Insert("currencies", key, value);

                i += 10;
            }
            
for (Object item : Currencies) {
	
        System.out.println("SIZE "+item.toString());
}
            
            return flag;
        }
        return flag;

    }
    //запись касиров касы и паролей в бд
        public boolean Write_LoginToSQLite() throws SQLException {
        boolean flag = false;
        if (!CashierNameList.isEmpty()) {

            int i = 4;
            while (i < CashierNameList.size()) {

                ArrayList key = new ArrayList();
                key.add("cashier_id");
                key.add("cashier_password");
                key.add("cash_id");
                key.add("super");
                key.add("surname");
                key.add("last_name");
                key.add("first_name");
                key.add("visible");
                ArrayList value = new ArrayList();
                value.add(CashierNameList.get(i - 4).toString());
                value.add(CashierNameList.get(i - 3).toString());
                value.add(CashierNameList.get(i - 2).toString());
                value.add(CashierNameList.get(i - 1).toString());
                value.add(CashierNameList.get(i).toString());
                value.add(CashierNameList.get(i + 1).toString());
                value.add(CashierNameList.get(i + 2).toString());
                value.add("true");
                flag = Insert("user", key, value);

                i += 7;
            }

            return flag;
        }
        return flag;

    }
    
    
    
    
    
    
    
    
    
    
}
