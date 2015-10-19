/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RefreshTable;

import static com.verbox.Date.getShortDate;
import com.verbox.In;
import static com.verbox.In.getInstanceMain;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import static com.verbox.Setting.GetZeroArr;
import com.verbox.StorageMemory;
import static com.verbox.StorageMemory.getInstance;

/**
 *
 * @author maxxl
 */
public class RefreshTable
{
   //Общая сводка - общее состояние
   public static void GeneralInformation() throws ParseException
   {

	//Общие сведения
	try
	{
	   StorageMemory SD = getInstance();
	   if (!SD.superuser)
	   {
		   
	   In mf = getInstanceMain();
	   Map mapData = new LinkedHashMap<String, ArrayList>();
	   mapData = ReadSQLiteMulti(
			"SELECT `j`.`type`, CASE (`j`.`type`)\n" +
			   "        WHEN \"buy\" THEN \"Покупка\" \n" +
			   "        WHEN \"sale\" THEN \"Продажа\" \n" +
			   "        WHEN \"reversal\" THEN \"Сторно\" \n" +
			   "        WHEN \"replenish\" THEN \"Пополнение\" \n" +
			   "        WHEN \"collection\" THEN \"Инкасация\"   \n" +
			   "    END\n , `s`.`currency_name`,SUM(j.grn_sum),SUM(currency_sum)\n"
			+ "FROM `journal` AS `j`\n"
			+ "INNER JOIN `SDbalance` AS `s` ON `j`.`currency_code` = `s`.`currency_code`\n"
			+ "WHERE `j`.`timedelete`=\"_\" AND DATE(\"" + getShortDate() + "\") = DATE(`j`.`date_create`) AND `s`.`active` = 'true'\n"
			+ "GROUP BY j.type");
	 
	   DefaultTableModel mod = new DefaultTableModel();
	   mf.getjTable2().setModel(mod);

	   DefaultTableModel model = (DefaultTableModel) mf.getjTable2().getModel();
	   model.addColumn("Операция");
	   model.addColumn("Валюта");
	   model.addColumn("Приход по Грн");
	   model.addColumn("Приход по валюте");

	   Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
	   Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
	   while (iteratorMap.hasNext())
	   {
		Map.Entry<String, ArrayList<String>> entry
			   = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
		String key = entry.getKey();
		List<String> values = entry.getValue();

		ArrayList tmpz = new ArrayList();
		tmpz = (ArrayList) mapData.get(key);

		model.addRow(new Object[]
		{
		   tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString()
		});

	   }

	   }
	   else
	   {
		
	   In mf = getInstanceMain();
			   Map mapData = new LinkedHashMap<String, ArrayList>();
			   mapData = ReadSQLiteMulti(
					"SELECT `j`.`type`, CASE (`j`.`type`)\n" +
					   "        WHEN \"buy\" THEN \"Покупка\" \n" +
					   "        WHEN \"sale\" THEN \"Продажа\" \n" +
					   "        WHEN \"reversal\" THEN \"Сторно\" \n" +
					   "        WHEN \"replenish\" THEN \"Пополнение\" \n" +
					   "        WHEN \"collection\" THEN \"Инкасация\"   \n" +
					   "    END\n , `s`.`currency_name`,SUM(j.grn_sum),SUM(currency_sum)\n"
					+ "FROM `journal` AS `j`\n"
					+ "INNER JOIN `SDbalance` AS `s` ON `j`.`currency_code` = `s`.`currency_code`\n"
					+ "WHERE DATE(\"" + getShortDate() + "\") = DATE(`j`.`date_create`) AND `s`.`active` = 'true'\n"
					+ "GROUP BY j.type");

			   DefaultTableModel mod = new DefaultTableModel();
			   mf.getjTable2().setModel(mod);

			   DefaultTableModel model = (DefaultTableModel) mf.getjTable2().getModel();
			   model.addColumn("Операция");
			   model.addColumn("Валюта");
			   model.addColumn("Приход по Грн");
			   model.addColumn("Приход по валюте");

			   Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
			   Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
			   while (iteratorMap.hasNext())
			   {
				Map.Entry<String, ArrayList<String>> entry
					   = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
				String key = entry.getKey();
				ArrayList tmpz = new ArrayList();
				tmpz = (ArrayList) mapData.get(key);

				model.addRow(new Object[]
				{
				   tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString()
				});

			   }
			   //отчет по удаленным
			   Map mapdat = new LinkedHashMap<String, ArrayList>();
			   mapdat = ReadSQLiteMulti(
					"SELECT `j`.`type`, CASE (`j`.`type`)\n" +
					   "        WHEN \"buy\" THEN \"Покупка[x]\" \n" +
					   "        WHEN \"sale\" THEN \"Продажа[x]\" \n" +
					   "        WHEN \"reversal\" THEN \"Сторно[x]\" \n" +
					   "        WHEN \"replenish\" THEN \"Пополнение[x]\" \n" +
					   "        WHEN \"collection\" THEN \"Инкасация[x]\"   \n" +
					   "    END\n , `s`.`currency_name`,SUM(j.grn_sum),SUM(currency_sum)\n"
					+ "FROM `journal` AS `j`\n"
					+ "INNER JOIN `SDbalance` AS `s` ON `j`.`currency_code` = `s`.`currency_code`\n"
					+ "WHERE `j`.`timedelete`!=\"_\" AND DATE(\"" + getShortDate() + "\") = DATE(`j`.`date_create`) AND `s`.`active` = 'true'\n"
					+ "GROUP BY j.type");



			   DefaultTableModel model2 = (DefaultTableModel) mf.getjTable2().getModel();
			   Set<RowFilter.Entry<String, ArrayList<String>>> setmap2 = mapdat.entrySet();
			   Iterator<RowFilter.Entry<String, ArrayList<String>>> IteratorMap = setmap2.iterator();
			   while (IteratorMap.hasNext())
			   {
				Map.Entry<String, ArrayList<String>> entry
					   = (Map.Entry<String, ArrayList<String>>) IteratorMap.next();
				String key = entry.getKey();
				ArrayList tmpz = new ArrayList();
				tmpz = (ArrayList) mapdat.get(key);

				model2.addRow(new Object[]
				{
				   tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString()
				});

			   }
			   
			   

	   }
	}
	catch (ClassNotFoundException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (SQLException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}

   }

   //Подкрепления
   public static void replanish() throws ParseException
   {
	try
	{
	   //подкрепления
	   In mf = getInstanceMain();
	   Map mapData = new LinkedHashMap<String, ArrayList>();
	   mapData = ReadSQLiteMulti(
			"SELECT j.id_journal,j.date_create,j.time_create,j.FIO,j.currency_sum, c.currency_name\n"
			+ "FROM journal AS j\n"
			+ "LEFT JOIN SDbalance AS c ON j.currency_code = c.currency_code\n"
			+ "WHERE type=\"replenish\" ORDER by id_journal DESC");
	   Set<String> keys = mapData.keySet();
	   DefaultTableModel mod = new DefaultTableModel();

	   mf.getjTable1().setModel(mod);
	   mf.getjTable1().setEnabled(false);
	   DefaultTableModel model = (DefaultTableModel) mf.getjTable1().getModel();
	   model.addColumn("Дата");
	   model.addColumn("Время");
	   model.addColumn("Кассир");
	   model.addColumn("Сумма");
	   model.addColumn("Валюта");

	   Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
	   Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
	   while (iteratorMap.hasNext())
	   {
		Map.Entry<String, ArrayList<String>> entry
			   = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
		String key = entry.getKey();
		List<String> values = entry.getValue();

		ArrayList tmpz = new ArrayList();
		tmpz = (ArrayList) mapData.get(key);

		model.addRow(new Object[]
		{
		   tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString(), tmpz.get(4).toString()
		});

	   }

	}
	catch (ClassNotFoundException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (SQLException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}

   }
   //Инкасация
   public static void Сollaction() throws ParseException
   {
   	 try
	 {
	    //подкрепления
In mf = getInstanceMain();
	    Map mapData = new LinkedHashMap<String, ArrayList>();
	    mapData = ReadSQLiteMulti(
			 "SELECT j.id_journal,j.date_create,j.time_create,j.FIO,j.currency_sum, c.currency_name\n"
			 + "FROM journal AS j\n"
			 + "LEFT JOIN SDbalance AS c ON j.currency_code = c.currency_code\n"
			 + "WHERE type=\"collection\" ORDER by id_journal DESC");
	    Set<String> keys = mapData.keySet();
	    DefaultTableModel mod = new DefaultTableModel();
	    mf.getjTable3().setModel(mod);

	    DefaultTableModel model = (DefaultTableModel)  mf.getjTable3().getModel();
	    model.addColumn("Дата");
	    model.addColumn("Время");
	    model.addColumn("Кассир");
	    model.addColumn("Сумма");
	    model.addColumn("Валюта");

	    Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
	    Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
	    while (iteratorMap.hasNext())
	    {
		 Map.Entry<String, ArrayList<String>> entry
			    = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
		 String key = entry.getKey();
		 List<String> values = entry.getValue();

		 ArrayList tmpz = new ArrayList();
		 tmpz = (ArrayList) mapData.get(key);

		 model.addRow(new Object[]
		 {
		    tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString(), tmpz.get(4).toString()
		 });

	    }

	 }
	 catch (ClassNotFoundException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
	 catch (SQLException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
   
   }
   //Кассиры
   public static void BookCaisher() throws ParseException
   {
   try
	 {
	    In mf = getInstanceMain();
	    Map mapData = new LinkedHashMap<String, String>();
	    mapData = ReadSQLiteMulti("SELECT cash_id,surname||\" \"||first_name||\" \"||last_name FROM user where super=\"false\" ;");
	    Set<String> keys = mapData.keySet();
	    DefaultTableModel mod = new DefaultTableModel();
	    mf.getjTable4().setModel(mod);

	    DefaultTableModel model = (DefaultTableModel) mf.getjTable4().getModel();
	    model.addColumn("Фамилия");
	    for (String key : keys)
	    {

		 model.addRow(new Object[]
		 {
		    GetZeroArr((ArrayList) mapData.get(key))
		 });

	    }

	    
	 }
	 catch (ClassNotFoundException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
	 catch (SQLException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
   }
   //Инкасаторы
   public static void inCashier() throws ParseException
   {
    try
	 { In mf = getInstanceMain();
	    //пкункт меню инкассаторы
	    Map mapData = new LinkedHashMap<String, String>();
	    mapData = ReadSQLiteMulti("SELECT cash_id,surname||\" \"||first_name||\" \"||last_name FROM user where super=\"true\" ;");
	    Set<String> keys = mapData.keySet();

	    DefaultTableModel mod = new DefaultTableModel();
	    mf.getjTable5().setModel(mod);
	    DefaultTableModel model = (DefaultTableModel) mf.getjTable5().getModel();
	    model.addColumn("Фамилия");
	    for (String key : keys)
	    {

		 model.addRow(new Object[]
		 {
		    GetZeroArr((ArrayList) mapData.get(key))
		 });

	    }

	 
	 }
	 catch (SQLException | ClassNotFoundException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
   }
   //Журнал
   public static void Journal() throws ParseException
   {
    try
	 {
	    //journal
	   In mf = getInstanceMain();
	    Map mapData = new LinkedHashMap<String, ArrayList>();
	    Map mapDat = new LinkedHashMap<String, ArrayList>();
	    StorageMemory SD = getInstance();

	    if(!SD.superuser){
		 //если кассир
	    mapData = ReadSQLiteMulti("SELECT j.id_journal,\n" +
			   "    j.date_create,\n" +
			   "    j.time_create,\n" +
			   "    j.FIO,\n" +
			   "    j.currency_sum, \n" +
			   "    c.currency_name , \n" +
			   "    CASE (`j`.`type`)\n" +
			   "        WHEN \"buy\" THEN \"Покупка\" \n" +
			   "        WHEN \"sale\" THEN \"Продажа\" \n" +
			   "        WHEN \"reversal\" THEN \"Сторно\" \n" +
			   "        WHEN \"replenish\" THEN \"Пополнение\" \n" +
			   "        WHEN \"collection\" THEN \"Инкасация\"   \n" +
			   "    END\n" +
			   "FROM journal AS j\n" +
			   "LEFT JOIN SDbalance AS c ON j.currency_code = c.currency_code\n" +
			   "WHERE timedelete=\"_\" ORDER by id_journal DESC");
	    Set<String> keys = mapData.keySet();
	    DefaultTableModel mod = new DefaultTableModel();
	    mf.getjTable6().setModel(mod);

	    DefaultTableModel model = (DefaultTableModel) mf.getjTable6().getModel();
	    model.addColumn("Дата");
	    model.addColumn("Время");
	    model.addColumn("Кассир");
	    model.addColumn("Сумма");
	    model.addColumn("Валюта");
	    model.addColumn("Операция");

	    Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
	    Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
	    while (iteratorMap.hasNext())
	    {
		 Map.Entry<String, ArrayList<String>> entry
			    = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
		 String key = entry.getKey();
		 List<String> values = entry.getValue();

		 ArrayList tmpz = new ArrayList();
		 tmpz = (ArrayList) mapData.get(key);

		 model.addRow(new Object[]
		 {
		    tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString(), tmpz.get(4).toString(), tmpz.get(5).toString()
		 });

	    }
	    }
	    else
	    {
		 //суперюсер
	     mapData = ReadSQLiteMulti("SELECT j.id_journal,\n" +
			   "    j.date_create,\n" +
			   "    j.time_create,\n" +
			   "    j.FIO,\n" +
			   "    j.currency_sum, \n" +
			   "    c.currency_name , \n" +
			   "    CASE (`j`.`type`)\n" +
			   "        WHEN \"buy\" THEN \"Покупка\" \n" +
			   "        WHEN \"sale\" THEN \"Продажа\" \n" +
			   "        WHEN \"reversal\" THEN \"Сторно\" \n" +
			   "        WHEN \"replenish\" THEN \"Пополнение\" \n" +
			   "        WHEN \"collection\" THEN \"Инкасация\"   \n" +
			   "    END , \n" +
			    "    j.timedelete \n" +
			   "FROM journal AS j\n" +
			   "LEFT JOIN SDbalance AS c ON j.currency_code = c.currency_code\n" +
			   "ORDER by id_journal DESC");
	 
	    DefaultTableModel mod = new DefaultTableModel();
	    mf.getjTable6().setModel(mod);

	    DefaultTableModel model = (DefaultTableModel) mf.getjTable6().getModel();
	    model.addColumn("Дата");
	    model.addColumn("Время");
	    model.addColumn("Кассир");
	    model.addColumn("Сумма");
	    model.addColumn("Валюта");
	    model.addColumn("Операция");
	    model.addColumn("Удалено");
	    Set<RowFilter.Entry<String, ArrayList<String>>> setMap = mapData.entrySet();
	    Iterator<RowFilter.Entry<String, ArrayList<String>>> iteratorMap = setMap.iterator();
	    while (iteratorMap.hasNext())
	    {
		 Map.Entry<String, ArrayList<String>> entry
			    = (Map.Entry<String, ArrayList<String>>) iteratorMap.next();
		 String key = entry.getKey();
		 List<String> values = entry.getValue();

		 ArrayList tmpz = new ArrayList();
		 tmpz = (ArrayList) mapData.get(key);

		 model.addRow(new Object[]
		 {
		    tmpz.get(0).toString(), tmpz.get(1).toString(), tmpz.get(2).toString(), tmpz.get(3).toString(), tmpz.get(4).toString(), tmpz.get(5).toString(),tmpz.get(6).toString()
		 });

	    
	    }
	    
	    
	    
	   
	 }
	 }
	 catch (ClassNotFoundException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
	 catch (SQLException ex)
	 {
	    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	 }
   }
}
