/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.MyMath.round;
import static com.verbox.Serial_XDD.Serial_XDDGet;
import static com.verbox.Serial_XDD.Serial_XDDGetHash;
import static com.verbox.sqlite_metod.GetMd5;
import static com.verbox.sqlite_metod.ReadSQLite;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.RowFilter.Entry;
import org.json.simple.JSONObject;

/**
 *
 * @author maxxl
 */
public class StorageMemory {

    //generate cashier_id and cashier_password for sign in
    private static JSONObject obj = new JSONObject();
    private String cash_id, cash_password, cashier_id, cashier_password, action_name, lastmess,patterns,FIO;
    JSONObject params;
    private ArrayList get_info;
//поля покупателя валюты для отправки запроса
    private String 
            buyer_first_name,
            buyer_last_name,
            buyer_surname,buyer_resident,
            passport_number,
            passport_serial,
            phone_number,
            currency_code,
            currency_course,
            currency_sum,
            grn_sum,
            receipt_currency,
            taxpf,
            receipt_number;
//поля покупателя валюты
    
    //хранение курсов в обьекте
    private int 
            id_operation, //receipt_currency
            idqwi,  // квитанция
            idqwiadmin; //для инкасаторов
public int Pfsell,
            Pfbuy;     //пенсионный фонд на продажу

      
    //хранение курсов в обьекте
    //массив валюты тест
    public  Map curse,balance;
    public  ArrayList TempForSelectDropdown;
    
    //Массив шаблонов на печать 
    public Map PrintTpl;
    
    StorageMemory() {
    }

    public void setTempForSelectDropdown(ArrayList TempForSelectDropdown) {
        this.TempForSelectDropdown = TempForSelectDropdown;
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
        obj.put("lastmes", getLastmess());
        return obj;
    }
    public void ClearObj()
    {
        obj.clear();
    }
    //эти аргументы мы инициализируем из функции логинизации sqlite metod login они хранятся в памяти постоянно

    public void StorageMemorySet(String t1, String t3, String t4, String t5, JSONObject t6, String t7, String t8) throws UnsupportedEncodingException {
        Serial_XDDGet();
        String tmp = Serial_XDDGetHash();

        setCash_id(t1);
        setCash_password(tmp);
        setCashier_id(t3);
        setCashier_password(t4);
        setAction_name(t5);
        setParams(t6);
        setLastmess(t7);
        setFIO(t8);


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

        get_info = ReadSQLite(tmp, "info","ORDER BY `enterprise_id` DESC LIMIT 1");

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

    public String getLastmess() {
        return lastmess;
    }

    public void setLastmess(String lastmes) {
        this.lastmess = lastmes;
    }



//методы покупателя
    
    public String getBuyer_first_name() {
        return buyer_first_name;
    }

    public void setBuyer_first_name(String buyer_first_name) {
        this.buyer_first_name = buyer_first_name;
    }

    public String getBuyer_last_name() {
        return buyer_last_name;
    }

    public void setBuyer_last_name(String buyer_last_name) {
        this.buyer_last_name = buyer_last_name;
    }

    public String getBuyer_surname() {
        return buyer_surname;
    }

    public void setBuyer_surname(String buyer_surname) {
        this.buyer_surname = buyer_surname;
    }

    public String getBuyer_resident() {
        return buyer_resident;
    }

    public void setBuyer_resident(String buyer_resident) {
        this.buyer_resident = buyer_resident;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getPassport_serial() {
        return passport_serial;
    }

    public void setPassport_serial(String passport_serial) {
        this.passport_serial = passport_serial;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_course() {
        return currency_course;
    }

    public void setCurrency_course(String currency_course) {
        this.currency_course = currency_course;
    }

    public String getCurrency_sum() {
        return currency_sum;
    }

    public void setCurrency_sum(String currency_sum) {
        this.currency_sum = currency_sum;
    }

    public String getGrn_sum() {
        return grn_sum;
    }

    public void setGrn_sum(String grn_sum) {
        this.grn_sum = grn_sum;
    }

    public String getReceipt_currency() {
        return receipt_currency;
    }

    public void setReceipt_currency(String receipt_currency) {
        this.receipt_currency = receipt_currency;
    }

    public String getTaxpf() {
        return taxpf;
    }

    public void setTaxpf(String taxpf) {
        this.taxpf = taxpf;
    }

    public String getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(String receipt_number) {
        this.receipt_number = receipt_number;
    }

    public int getId_operation() {
        return id_operation;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public int getIdqwi() {
        return idqwi;
    }

    public void setIdqwi(int idqwi) {
        this.idqwi = idqwi;
    }

    public int getIdqwiadmin() {
        return idqwiadmin;
    }

    public void setIdqwiadmin(int idqwiadmin) {
        this.idqwiadmin = idqwiadmin;
    }

    public int getPfsell() {
        return Pfsell;
    }

    public void setPfsell(int Pfsell) {
        this.Pfsell = Pfsell;
    }

 

    public String getPatterns() {
        return patterns;
    }

    public void setPatterns(String patterns) {
        this.patterns = patterns;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    //===========================================================================================================
    //===========================================================================================================
    //===========================================================================================================
    //функция подсчета принимает код валюты / резидент ли /  фио покупателя / сумму денег / название операции/ паспортные данные
//! должна вернуть готовый обьект JSON!
    public void OperationX(
            int code,        //код валюты
            String resident,    //резидент не резидент
            String Fn,          //Имя
            String Ln,          //Отчество
            String Sn,          //Фамилия
            double sum,          //бабло в грн
            double csum,            // в валюте
            String typeOx,       //Тип операции
           
            String pspS,         //Серия пасспорта
            int pspCode,         //код паспорта
            String Phone
    )
            {
            buyer_first_name=Fn;
            buyer_last_name=Ln;
            buyer_surname=Sn;
            buyer_resident=resident;
            passport_number=Integer.toString(pspCode);
            passport_serial=pspS;
            phone_number=Phone;
            //ПФ
                        if (typeOx.equals("sale"))
                        {
                                taxpf=Integer.toString(Pfsell);
                        }
                       
                        if (typeOx.equals("buy"))
                        {
                                taxpf=Integer.toString(Pfbuy);
                        }
                   
            //Код валюты передает кнопка купить /../ и получаем из дропдауна индекс выбраного и подтягиваем из массива.
            currency_code=(String) TempForSelectDropdown.get(code);
            //получить курс по данной валюте
                                            if(typeOx.equals("buy"))
                                            {  //купить 
                                                    ArrayList tmpz = new ArrayList();
                                                    tmpz=(ArrayList) curse.get(currency_code);
                                                    currency_course=Double.toString(Double.parseDouble((String)tmpz.get(0))*Double.parseDouble((String)tmpz.get(3)));
                                                    action_name="buy";
                                                    
                                               //     grn_sum=Double.toString(sum*Double.parseDouble((String)tmpz.get(0)));
                                            }
                                            else if(typeOx.equals("sale"))
                                            {
                                                // продать  
                                                    ArrayList tmpz = new ArrayList();
                                                    tmpz=(ArrayList) curse.get(currency_code);
                                                    currency_course=Double.toString(Double.parseDouble((String)tmpz.get(1))*Double.parseDouble((String)tmpz.get(3)));
                                                    action_name="sale";
                                              // grn_sum=Double.toString(round(sum*Double.parseDouble((String)tmpz.get(1)),2));
                                            }
                                          
            currency_sum=Double.toString(csum);
           
            grn_sum=Double.toString(sum);
            
            receipt_currency=Integer.toString(idqwi);
            
            
            
            JSONObject forparam = new JSONObject();
            //если купить/продать
            if(typeOx.equals("sale")||typeOx.equals("buy")){
            
            
            forparam.put("buyer_first_name", buyer_first_name);
            forparam.put("buyer_last_name", buyer_last_name);
            forparam.put("buyer_surname", buyer_surname);
            forparam.put("buyer_resident", buyer_resident);
            forparam.put("passport_number", passport_number);
            forparam.put("passport_serial", passport_serial);
            forparam.put("phone_number", phone_number);
            forparam.put("currency_code", currency_code);
            forparam.put("currency_course", currency_course);
            forparam.put("currency_sum", currency_sum);
            forparam.put("grn_sum", grn_sum);
            forparam.put("receipt_currency", receipt_currency);
            forparam.put("buyer_first_name", buyer_first_name);
            forparam.put("taxpf", taxpf);
            setParams(forparam);
            }
            //есди подкрепление или инкасация
            if(typeOx.equals("replenish")||typeOx.equals("collection")){
              action_name=typeOx;
               forparam.put("currency_code", currency_code);
               forparam.put("currency_sum", currency_sum);
               forparam.put("patterns", patterns);
               
                setReceipt_number(Integer.toString(idqwiadmin));
                forparam.put("receipt_number", getReceipt_number());
               setParams(forparam);
                
               
                
                
            }
                
                    
                    
                    
                    
           }
    //===========================================================================================================
    //===========================================================================================================
    //===========================================================================================================
    //===========================================================================================================
    //инициализация основных компонентов курсов для подсчета
    public void initCourse() throws ClassNotFoundException, SQLException
    {
    
        //получаем курсы с локал бд в ключе номер валюты в массиве посчитанный курс за единицу валюты
        curse=new HashMap<String,ArrayList<String>>();
        curse=ReadSQLiteMulti("SELECT currency_code,course_buy/CAST(quantity AS DOUBLE),course_sale/CAST(quantity AS DOUBLE),course_nbu/CAST(quantity AS DOUBLE),quantity,currency_name FROM `currencies` ORDER BY `currencies_id` DESC LIMIT 22");
        
        //получаем баланс в ключе ид валюты в массиве баланс
        balance=new HashMap<String,Double>();
        balance=ReadSQLiteMulti("SELECT currency_code,balance FROM `SDbalance` ORDER BY `id_SDbalance` DESC LIMIT 23");
        
        
        
        
        

        if(curse!=null||!curse.isEmpty())
        {   
    Set<Entry<String, ArrayList<String>>> setMap = curse.entrySet(); 
   Iterator<Entry<String,  ArrayList<String>>> iteratorMap = setMap.iterator(); 
        System.out.println("\nHashMap with Multiple Values"); 
        
        while(iteratorMap.hasNext()) { 
            Map.Entry<String, ArrayList<String>> entry =  
            (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
            String key = entry.getKey(); 
            List<String> values = entry.getValue(); 
            System.out.println("Key = '" + key + "' has values: " + values); 
      } 


        }
                ArrayList tmpz = new ArrayList();
                tmpz=(ArrayList) curse.get("978");
                System.out.println("GET OBJ "+Double.parseDouble((String)tmpz.get(1))+5); 
        
        
        //map balance
        Set<String> keys = balance.keySet();

	// Loop over String keys.
	for (String key : keys) {
	    System.out.println("key of balance "+key+ " balance "+ balance.get(key));
	}


       
        
        ArrayList tmp=new ArrayList();
        ArrayList res=new ArrayList();
        tmp.add("course_buy");
        tmp.add("course_nbu");
        tmp.add("course_sale");   
        tmp.add("quantity"); 
                // переделать на взятие последних елементов

        
        res.clear();
        tmp.clear();
        tmp.add("id_operation");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.id_operation=Integer.parseInt((String)res.get(0));
         
        res.clear();
        tmp.clear();
        tmp.add("idqwi");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.idqwi=Integer.parseInt((String) res.get(0));
        
        res.clear();
        tmp.clear();
        tmp.add("idqwiadmin");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.idqwiadmin=Integer.parseInt((String) res.get(0));
        
        res.clear();
        tmp.clear();
        tmp.add("Pfsell");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.Pfsell=Integer.parseInt((String) res.get(0));
        
        res.clear();
        tmp.clear();
        tmp.add("Pfbuy");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.Pfbuy=Integer.parseInt((String) res.get(0));
        
        
        res.clear();
        tmp.clear();
        tmp.add("lastmess");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.lastmess= (String) res.get(0);
        
        res.clear();
        tmp.clear();
        tmp.add("patterns");
        res= ReadSQLite(tmp, "SDobj",""); 
        this.patterns= (String) res.get(0);
        
        

        showMessageDialog(null, 
                " Lastmess "+ lastmess+
                "\n Patterns "+ patterns+
                "\n Id operation "+ id_operation+
                "\n Idqwi " + idqwi +
                "\n idqwiadmin "+idqwiadmin+
                "\n Pfsell " + Pfsell+
                "\n Pfbuy "+Pfbuy

        
        
        

            

            

            
                
                );
    }
    
}
