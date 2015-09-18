/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.DecodeUTF.DecodeUTF;
import static com.verbox.sqlite_metod.Insert;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author maxxl
 */
public class ParseJson {

    private String ErrorCode;
    private String ErrorMessage;

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
        JSONObject Error = (JSONObject) PJjson.get("error");
        setErrorCode((String) Error.get("code"));
        setErrorMessage((String) Error.get("message"));
        //Касиры
        CashierNameList = new ArrayList();
//"super":false,"cashier_id":4,"cash_id":1,"cashier_password":"c4ca4238a0b923820dcc509a6f75849b","surname":"Петров","last_name":"Александрович","first_name":"Петя"
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
        /*  for (Object item : CashierNameList) 
                    {
                        System.out.println("Name of user  "+ item);
                    }*/
        }
    }
    
    //----
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
