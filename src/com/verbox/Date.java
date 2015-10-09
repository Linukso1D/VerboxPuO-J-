/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;
    
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.jdesktop.swingx.JXDatePicker;
/**
 *
 * @author maxxl
 */
public class Date {
    
    /**
     * 
     * @see 
     * @return 
     * @
     * getFullDate возвращает полную дату и время в соответствии с форматом сервера verbox - "2015-03-05 13:00:53"
     */
    public static String getFullDate()
     {
         
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToday = formattedDate.format(calendar.getTime());
         return dateToday;
         //2015-03-05 13:00:53
     }
    
    
    
        public static String ConvertJXPiker(JXDatePicker inpDate) throws ParseException
     {
         
   
       //  showMessageDialog(inpDate, inpDate);

         SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
         String ot =formater.format(inpDate.getDate());
            
         return ot;
         //2015-03-05
     }

    /**
     *
     * возвращает сокращенную дату в формате  "2015-03-05"
     */
    public static String getShortDate()
     {
         
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
            String dateToday = formattedDate.format(calendar.getTime());
         return dateToday;
         //2015-03-05
     }
    
    public static java.util.Date getJXShortDate() throws ParseException
     {
         
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yy");
            String dateToday = formattedDate.format(calendar.getTime());
            
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yy");
            java.util.Date docDate= format.parse(dateToday);
            
            
            
         return docDate;
         //2015-03-05
     }
     // 1 дата и время больше второй?
      public static boolean IsAfterDateCompare(String dateone, String datetwo) throws ParseException
      {
      
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("yyyy-MM-dd HH:mm:ss");
                java.util.Date docDate= format.parse(dateone);
          
                SimpleDateFormat format2 = new SimpleDateFormat();
                format2.applyPattern("yyyy-MM-dd HH:mm:ss");
                java.util.Date docDate2= format.parse(datetwo);
                
                boolean a = docDate.getTime()>docDate2.getTime();
          System.out.println("docDate.getTime "+docDate.getTime()+" docDate2.getTime "+docDate2.getTime() );
      return a;
      }

      
      
      
      //
      public static String ParseDateList(String s) throws java.text.ParseException {
               // оно ищет в листе дату что бы потом выбрать из бд все по этой дате НАВЕРНОЕ 
                Calendar calendar = new GregorianCalendar();
                SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
                String s2 = s.substring(s.indexOf("(")+1 , s.indexOf(")") ) ;
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("yyyy-MM-dd");
                java.util.Date docDate= format.parse(s2);
                System.out.println("DATE =" + docDate);
                String dateToday = formattedDate.format(docDate);
                return dateToday;
            }
}
