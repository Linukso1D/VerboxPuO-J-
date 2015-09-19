/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.Serial_XDD.Serial_XDDGet;
import static com.verbox.Serial_XDD.Serial_XDDGetHash;
import static com.verbox.sqlite_metod.GetMd5;
import static com.verbox.sqlite_metod.ReadSQLite;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author maxxl
 */
public class StorageMemory {

    //generate cashier_id and cashier_password for sign in
    private static JSONObject obj = new JSONObject();
    private String cash_id, cash_password, cashier_id, cashier_password, action_name, lastmes;
    JSONObject params;
    ArrayList get_info;

    public static JSONObject GetSD() throws UnsupportedEncodingException {
        return obj;
    }

    //эти аргументы мы инициализируем из функции логинизации sqlite metod login они хранятся в памяти постоянно
    public StorageMemory(String t1, String t3, String t4, String t5, JSONObject t6, String t7) throws UnsupportedEncodingException {
        Serial_XDDGet();
        String tmp = Serial_XDDGetHash();

        setCash_id(t1);
        setCash_password(tmp);
        setCashier_id(t3);
        setCashier_password(t4);
        setAction_name(t5);
        setParams(t6);
        setLastmes(t7);

        obj.put("cash_id", getCash_id());
        obj.put("cash_password", getCash_password());
        obj.put("cashier_id", getCashier_id());
        obj.put("cashier_password", getCashier_password());
        obj.put("action_name", getAction_name());
        obj.put("params", getParams());
        obj.put("lastmes", getLastmes());

    }
    public void SetInfo() throws SQLException, ClassNotFoundException
    {
     get_info = new ArrayList();
     ArrayList tmp = new ArrayList();
                tmp.add("bookk_fio");               //ФИО бухгалтера
                tmp.add("bookk_first_name");        //Имя бухгалтера
                tmp.add("bookk_last_name");         //отчество
                tmp.add("bookk_surname");           //фамилия бухг
                tmp.add("dir_fio");                 //ФИО директора
                tmp.add("dir_first_name");          //Имя директора
                tmp.add("dir_last_name");           //Отчество Директора 
                tmp.add("dir_surname");             //Фамилия директора
                tmp.add("enterprise_full_name");    //полное имя предприятия
                tmp.add("enterprise_mfo");          //МФО 
                tmp.add("enterprise_name");         // я без понятия что это
                tmp.add("enterprise_okpo");         //ОКПО предприятия
                tmp.add("enterprise_short_name");   //короткое имя предприятия
                tmp.add("logo_full_image");         //имя лого
                tmp.add("logo_short_image");        //Имя еще одной картинки тоже хз
                tmp.add("nat_city");                //Физ адр города
                tmp.add("nat_city_code");           //физ индекс города
                tmp.add("nat_house");               //физ номер дома
                tmp.add("nat_office");              //физ офис
                tmp.add("nat_street");              //физ улица
                tmp.add("payment_account");         //расчетный счет
                tmp.add("stamp_image");             // наверное печать предприятия
                tmp.add("ur_city");                 //юр адрес город
                tmp.add("ur_city_code");            //юр код города
                tmp.add("ur_house");                //юр дом
                tmp.add("ur_office");               //юр дом
                tmp.add("ur_street");               // юр улица
        
       get_info= ReadSQLite(tmp, "info");        
                
                
    }
    //---Методы получения гет инфо структуры
    public  String StorageGetInfo_bookk_fio()
    {
        System.out.println("GET INFO GET1 "+ get_info.get(1));
        return (String) get_info.get(1);
    }
    
    
    //---Методы получения гет инфо структуры конец
    
    

    public String getCash_id() {
        return cash_id;
    }

    public void setCash_id(String cash_id) {
        this.cash_id = cash_id;
    }

    public String getCash_password() {
        return cash_password;
    }

    public void setCash_password(String cash_password) {
        this.cash_password = cash_password;
    }

    public String getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(String cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getCashier_password() {
        return cashier_password;
    }

    public void setCashier_password(String cashier_password) {
        this.cashier_password = cashier_password;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }

    public String getLastmes() {
        return lastmes;
    }

    public void setLastmes(String lastmes) {
        this.lastmes = lastmes;
    }

}
