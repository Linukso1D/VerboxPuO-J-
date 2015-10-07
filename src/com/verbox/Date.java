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

}
