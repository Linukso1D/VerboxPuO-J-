/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;


import static com.verbox.StorageMemory.getInstance;
import static com.verbox.json_metod.SendPost;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author maxxl
 */
public class sqlite_metod {

    /**
     *
     */
    public static Statement statmt; //sql запросы извлекать через это

    /**
     *
     */
    public static ResultSet resSet;
    private static Connection con = null;

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void Conn() throws ClassNotFoundException, SQLException {
        con = null;
        Class.forName("org.sqlite.JDBC");
        try {
            con = DriverManager.getConnection("jdbc:sqlite:dblite.s3db");
            if (con != null) {
                showMessageDialog(null, "Lan Database connected.");
            }
        } catch (SQLException e) {
            showMessageDialog(null, "Невозможно подключится к базе данных, дальнейшая работа не возможна" + e.toString());
            System.exit(0);
        }

        statmt = con.createStatement();

    }
    //для записи в бд корректных значений кодирование

    /**
     *
     * @param s
     * @return
     */
    public static final String EscapeHtml(String s){
       StringBuffer sb = new StringBuffer();
       int n = s.length();
       for (int i = 0; i < n; i++) {
          char c = s.charAt(i);
          switch (c) {
             case '<': sb.append("&lt;"); break;
             case '>': sb.append("&gt;"); break;
             case '&': sb.append("&amp;"); break;
             case '"': sb.append("&quot;"); break;
             default:  sb.append(c); break;
          }
       }
       return sb.toString();
    }
    //декодирование

    /**
     *
     * @param s
     * @return
     */
    public static final String EscapeUndoHtml(String s)
    {
        return s.replace("&quot;", "\"").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
    }
    
//Проверка локального пользователя

    /**
     *
     * @param login
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws UnsupportedEncodingException
     * @throws ParseException
     * @throws InterruptedException
     */
    public static boolean LoginSQLite(String login, String password) throws ClassNotFoundException, SQLException, UnsupportedEncodingException, ParseException, InterruptedException {
        String patt = SELECT("SELECT patterns FROM SDobj LIMIT 1;");
        String lastm =SELECT("SELECT lastmess FROM SDobj LIMIT 1;");
        resSet = statmt.executeQuery("SELECT * FROM user");
        boolean flag=false;
        while (resSet.next()) {
           
            String us = resSet.getString("surname")+" "+resSet.getString("first_name")+" "+resSet.getString("last_name");
            String pas = resSet.getString("cashier_password");


   
   
   
   
            
            
            if (us.equals(login) && pas.equals(GetMd5(password))) 
            { 
                
      if(us.equals("rootes rootes rootes"))
    {

    rootes farm = new rootes();
    farm.setVisible(true);
    } 
            
            JSONObject temp = new JSONObject();
                temp.put("patterns", patt);
               
                StorageMemory SD=getInstance();
               
                SD.StorageMemorySet(
                        resSet.getString("cash_id"),
                        resSet.getString("cashier_id"),
                        resSet.getString("cashier_password"), 
                        "get_info", 
                        temp, 
                        lastm,
                        us
                          );      
               try {
               SendPost(SD.GetSD());
                } catch (IOException ex) {
                    Logger.getLogger(sqlite_metod.class.getName()).log(Level.SEVERE, null, ex);
                }
                

   
                
                
                showMessageDialog(null, "Вход выполнен.");
                flag=true;
                return flag;
            }

        }
        return flag;
    }
    
    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ArrayList ReadSQLite_loginPair() throws ClassNotFoundException, SQLException {
        resSet = statmt.executeQuery("SELECT * FROM user WHERE visible=\"true\"");
        
        ArrayList tm = new ArrayList();
        while (resSet.next()) {
            tm.add(resSet.getString("surname")+" "+resSet.getString("first_name")+" "+resSet.getString("last_name"));
        }
        return tm;
    }
    
    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        con.close();
        statmt.close();
        resSet.close();
        System.out.println("Соединения закрыты");
    }

    //get md5

    /**
     *
     * @param test
     * @return
     */
    public static String GetMd5(String test)
    {
         try {
            String hashtext="";
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            try {
                m.update(test.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] digest = m.digest();

            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(json_metod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param to
     * @param key
     * @param value
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean Insert(String to, ArrayList key, ArrayList value) throws SQLException, ClassNotFoundException
    {   
      
            
      //  CloseDB();
      // Conn();
        
     
        //Ключ
        String ItemKey = "(";
         for(Object item : key) 
         {
            ItemKey+=item.toString()+",";
         }
        ItemKey+=")";
        ItemKey= ItemKey.replace(",)" , ")");
        //конец ключ
        
        //значения ---------------------------------------------------------------
        String ItemVal = "(";
         for (Object item : value) 
         {
            ItemVal+="\""+EscapeHtml(item.toString())+"\",";
         }
        ItemVal+=")";
        ItemVal= ItemVal.replace(",)" , ")");
        // end значения---------------------------------------------------------
        
        
        //Проверка на дубликаты
    
        
        
        
        String Sel_feedback="SELECT "+ItemKey.replace(")" , "").replace("(" , "")+" FROM "+to+";";
        resSet = statmt.executeQuery(Sel_feedback);
        
        ResultSetMetaData rsmd = resSet.getMetaData();
        int fieldsCount = rsmd.getColumnCount();
        
        
        
        while (resSet.next()) {
            int toReturn=0;
            for(int i=1;i<fieldsCount+1;i++)
            {
               
                if(
                    value.get(i-1).toString().equals(EscapeUndoHtml(resSet.getString(i)))
                
                        
                    )
                            {
                           toReturn++;
                           System.out.println("fieldsCount " + fieldsCount + "toReturn " +toReturn + " ACTION "+  to + " FIELD      "+ EscapeUndoHtml(resSet.getString(i)) );
                            }
        //    System.out.println("DB: "+EscapeUndoHtml(resSet.getString(i)) + " |IN| "+ value.get(i-1));
        
            if(fieldsCount==toReturn||toReturn==25&&to.equals("info"))
            { 
                return true;
            }
            }
                    
        }
        
        
      
        
        
        String sql = "INSERT INTO "+to+" "+ ItemKey +" VALUES "+ ItemVal +";"; 
        System.out.println(sql);
        try
        {
        statmt.executeUpdate(sql);
        return true;
        }
        catch(Exception e)
        {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
      
    return false;
    }
    
                        //инвиз

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
                        public static boolean InviZ(int id) throws SQLException
                        {
                            try{
                            String query="UPDATE user SET visible= \"false\" WHERE cashier_id="+Integer.toString(id)+";";
                            System.out.println(query);
                            statmt.executeUpdate(query);
                                
                            return true;
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"INVIZ ");
                            }
                          return false;
                        }
                        //виз

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
                         public static boolean viZ(int id) throws SQLException
                        {
                            try{
                            String query="UPDATE user SET visible=\"true\" WHERE cashier_id="+Integer.toString(id)+";";
                            statmt.executeUpdate(query);
                            return true;
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"VIZ ");
                            }
                          return false;
                        }
                         
    /**
     *
     * @param set
     * @param log
     * @param pas
     * @return
     * @throws SQLException
     */
    public static boolean cureList_SetCash(String set, String log, String pas) throws SQLException
                        {
                            try{
                            if(!set.equals("")) {   
                            String query=" UPDATE firstlist_seeting SET cash_id= \""+set+"\", httplogin= \""+log+"\", httppass= \""+pas+"\" WHERE id_list=\"1\";  ";
                            
                            statmt.executeUpdate(query);
                                
                            return true;
                            }
                            else
                            {
                             String query=" UPDATE firstlist_seeting SET httplogin= \""+log+"\", httppass= \""+pas+"\" WHERE id_list=\"1\";  ";
                            
                            statmt.executeUpdate(query);
                                
                            return true;
                            }
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"SetCash");
                            }
                          return false;
                        }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static String cureList_GetCash() throws SQLException
                        {
                            try{
                            String query=" SELECT cash_id FROM firstlist_seeting ;";
                            
                            
                           
                          resSet= statmt.executeQuery(query);
                                
                            return resSet.getString("cash_id");
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"SetCash");
                            }
                          return "NULL";
                        }
   
                        
    /**
     *
     * @return
     * @throws SQLException
     */
    public static String cureList_GetLog() throws SQLException
                        {
                            try{
                            String query=" SELECT httplogin FROM firstlist_seeting ;";
                            System.out.println(query);
                            
                           
                          resSet= statmt.executeQuery(query);
                                
                            return resSet.getString("httplogin");
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"SetCash");
                            }
                          return "NULL";
                        }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static String cureList_GetPass() throws SQLException
                        {
                            try{
                            String query=" SELECT httppass FROM firstlist_seeting ;";
                            System.out.println(query);
                            
                           
                          resSet= statmt.executeQuery(query);
                                
                            return resSet.getString("httppass");
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"SetCash");
                            }
                          return "NULL";
                        }
                        
    //читает то что принимает в параметрах и возвращает данные однострочное читение                  

    /**
     *
     * @param what
     * @param From
     * @param Value
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
       public static ArrayList ReadSQLite(ArrayList what, String From ,String Value) throws ClassNotFoundException, SQLException {
        
         String Where  ;
         if(!Value.equals(""))
         {
         Where=Value;
         }
         else
         {
         Where="";
         }
           
         String ItemKey = " ";
         for(Object item : what) 
         {
            ItemKey+=item.toString()+",";
             
         }
          ItemKey+=")";
        ItemKey= ItemKey.replace(",)" , " ");
        
           
           
           resSet = statmt.executeQuery("SELECT "+ItemKey+" FROM "+From+" "+Where+" ;");
           
        ArrayList tm = new ArrayList();
        //колово колонок 
        ResultSetMetaData rsmd = resSet.getMetaData();
        int fieldsCount = rsmd.getColumnCount();
        while (resSet.next()) {
            
            
            for(int i=1;i<fieldsCount+1;i++)
            {
          
            tm.add(EscapeUndoHtml(resSet.getString(i)));
            }
        }
          
        return tm;
    }
      
    /**
     *
     * @param str
     * @return
     * @throws SQLException
     */
    public static String SELECT(String str) throws SQLException
       {
       resSet = statmt.executeQuery(str);
           String l = "";
        ArrayList tm = new ArrayList();
        //колово колонок 
        ResultSetMetaData rsmd = resSet.getMetaData();
        int fieldsCount = rsmd.getColumnCount();
        while (resSet.next()) {
            
            
            for(int i=1;i<fieldsCount+1;i++)
            {
          
            l+=(EscapeUndoHtml(resSet.getString(i)));
            }
       }
        return l;
       }
       
    /**
     *
     * @param Where
     * @throws SQLException
     */
    public static void DELETE_ALL(String Where) throws SQLException
       {
       statmt.execute("DELETE FROM "+Where+" ;");
       }
               
       
       
       
       
       //--test multivalue read
       
           //читает то что принимает в параметрах и возвращает данные однострочное читение                  

    /**
     *
     * @param query
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
       public static Map ReadSQLiteMulti(String query) throws ClassNotFoundException, SQLException {
        
           Map z = new LinkedHashMap <String,ArrayList<String>>();
         
           
           
           resSet = statmt.executeQuery(query);
           
       
        //колово колонок 
        ResultSetMetaData rsmd = resSet.getMetaData();
        int fieldsCount = rsmd.getColumnCount();
        while (resSet.next()) {
            ArrayList tm = new ArrayList(); 
       
            for(int i=2;i<fieldsCount+1;i++)
            {
          
            tm.add(EscapeUndoHtml(resSet.getString(i)));
            }
            z.put(resSet.getString(1), tm);
           
        }
          
        return z;
    }
    
       
       
       
       // обновление чекитов

    /**
     *
     * @param where
     * @param Check
     * @return
     * @throws SQLException
     */
                        public static boolean UPDATE_cheked(String where, boolean Check) throws SQLException
                        {
                            try{
                            String local;
                            if(!where.equals("")) {
                                
                                    if(Check){local="true";}
                                    else {local="false";}
                            String query=" UPDATE SDbalance SET active= \""+local+"\" WHERE currency_code=\""+where+"\";  ";
                                System.out.println("QUERY upd"+query);
                            statmt.executeUpdate(query);
                                
                            return true;
                            }
                            
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"Cheked");
                            }
                          return false;
                        }
                        
         // обновление счетчиков через инсерт

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
                         public static boolean UPDATE(String query) throws SQLException
                        {
                            try{
                     
                       
                            
                                System.out.println("QUERY upd obj lite "+query);
                            statmt.executeUpdate(query);
                                
                            return true;
                            
                            
                            }
                            catch(Exception e)
                            {
                            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"Cheked");
                            }
                          return false;
                        }
      
       
}
