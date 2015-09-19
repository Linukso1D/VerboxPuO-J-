/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.Serial_XDD.Serial_XDDGet;
import static com.verbox.Serial_XDD.Serial_XDDGetHash;
import static com.verbox.sqlite_metod.GetMd5;
import java.io.UnsupportedEncodingException;
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
