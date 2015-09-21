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
    private ArrayList get_info;

    

    StorageMemory() {
    }

    private static volatile StorageMemory instance;

    public static  StorageMemory getInstance() {
        if (instance == null) {
            synchronized (StorageMemory.class) {
                if (instance == null) {
                    instance = new StorageMemory();
                }
                System.out.println("Create new Instanse Singlton");
            }
        }
        System.out.println("Return Instanse Singlton");

        return instance;
    }

    
    
    // добавляет данные в обьект OBJ из класса и ретурн
    public JSONObject GetSD() throws UnsupportedEncodingException {
        obj.clear();
        obj.put("cash_id", getCash_id());
        obj.put("cash_password", getCash_password());
        obj.put("cashier_id", getCashier_id());
        obj.put("cashier_password", getCashier_password());
        obj.put("action_name", getAction_name());
        obj.put("params", getParams());
        obj.put("lastmes", getLastmes());
        return obj;
    }
    public void ClearObj()
    {
        obj.clear();
    }
    //эти аргументы мы инициализируем из функции логинизации sqlite metod login они хранятся в памяти постоянно

    public void StorageMemorySet(String t1, String t3, String t4, String t5, JSONObject t6, String t7) throws UnsupportedEncodingException {
        Serial_XDDGet();
        String tmp = Serial_XDDGetHash();

        setCash_id(t1);
        setCash_password(tmp);
        setCashier_id(t3);
        setCashier_password(t4);
        setAction_name(t5);
        setParams(t6);
        setLastmes(t7);



    }

    public void SetInfo() throws SQLException, ClassNotFoundException {
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

        get_info = ReadSQLite(tmp, "info");

    }
    //---Методы получения гет инфо структуры

    //ФИО бухг
    public String StorageGetInfo(String who) {

        int index = 0;
              
    switch (who) {
                
                case "bookk_fio": index=0; break;               //ФИО бухгалтера
                case "bookk_first_name": index=1; break;        //Имя бухгалтера
                case "bookk_last_name": index=2; break;         //отчество
                case "bookk_surname": index=3; break;           //фамилия бухг
                case "dir_fio": index=4; break;                 //ФИО директора
                case "dir_first_name": index=5; break;          //Имя директора
                case "dir_last_name": index=6; break;           //Отчество Директора 
                case "dir_surname": index=7; break;             //Фамилия директора
                case "enterprise_full_name": index=8; break;    //полное имя предприятия
                case "enterprise_mfo": index=9; break;          //МФО 
                case "enterprise_name": index=10; break;         // я без понятия что это
                case "enterprise_okpo": index=11; break;         //ОКПО предприятия
                case "enterprise_short_name": index=12; break;   //короткое имя предприятия
                case "logo_full_image": index=13; break;         //имя лого
                case "logo_short_image": index=14; break;        //Имя еще одной картинки тоже хз
                case "nat_city": index=15; break;                //Физ адр города
                case "nat_city_code": index=16; break;           //физ индекс города
                case "nat_house": index=17; break;               //физ номер дома
                case "nat_office": index=18; break;              //физ офис
                case "nat_street": index=19; break;              //физ улица
                case "payment_account": index=20; break;         //расчетный счет
                case "stamp_image": index=21; break;             // наверное печать предприятия
                case "ur_city": index=22; break;                 //юр адрес город
                case "ur_city_code": index=23; break;            //юр код города
                case "ur_house": index=24; break;                //юр дом
                case "ur_office": index=25; break;               //юр дом
                case "ur_street": index=26; break;               // юр улица  
    }
    
        if (get_info.get(index) == null) {
            return "NULL SD getinfo memory";
        } else {
            return (String) get_info.get(index);
        }
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
