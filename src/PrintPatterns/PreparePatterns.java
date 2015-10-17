/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrintPatterns;

import com.itextpdf.text.DocumentException;
import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import static com.verbox.Date.ParseDateList;
import com.verbox.In;
import static com.verbox.PrintHtml.Print;
import static com.verbox.PrintHtml.RenderPDF_img_too;
import com.verbox.ResizeImage;
import com.verbox.StorageMemory;
import static com.verbox.StorageMemory.getInstance;
import static com.verbox.sqlite_metod.ReadSQLite;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import static com.verbox.sqlite_metod.SELECT;
import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import static jdk.nashorn.internal.objects.NativeArray.map;
import org.xml.sax.SAXException;
import static com.verbox.Setting.GetZeroArr;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author maxxl
 */
public class PreparePatterns
{

   public PreparePatterns(boolean print, boolean show)
   {

	String dateoflist;

	try
	{
	   StorageMemory SD = getInstance();
	   In MainForm = In.getInstanceMain();
	   if(MainForm.getjList1().getSelectedValue() != null)
	   {

		dateoflist = ParseDateList(MainForm.getjList1().getSelectedValue().toString());
		// showMessageDialog(null, dateoflist);

		String pre = null;

		switch (MainForm.getjComboBox1().getSelectedIndex())
		{

		   //печать большого отчета по валютам
		   case 0:
		   {
			pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
			//Прогнать через шаблонизатор 
			SD.TPLveloPrint = new LinkedHashMap<String, ArrayList>();
			SD.TPLveloPrint = ReadSQLiteMulti("SELECT currency_code,quantity_text,currency_name,CAST(course_buy AS DOUBLE),CAST(course_sale AS DOUBLE),CAST(course_nbu AS DOUBLE) FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" ORDER BY `currencies_id` LIMIT 22 ");
			SD.velocity.put("map", SD.TPLveloPrint);
			//положили временный масив в шаблонизатор
			SD.velocity.put("TimetoStart", dateoflist);
			SD.velocity.put("TimetoFinish", SELECT("SELECT TimetoFinish FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" LIMIT 1;"));
			RenderPDF_img_too(SD.ShablonThisHtml(pre));

			break;
		   }
		   //печать маленького отчета по валютам
		   case 1:
		   {
			pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
			//Прогнать через шаблонизатор 
			SD.TPLveloPrint = new LinkedHashMap<String, ArrayList>();
			SD.TPLveloPrint = ReadSQLiteMulti("SELECT currency_code,quantity_text,currency_name,CAST(course_buy AS DOUBLE),CAST(course_sale AS DOUBLE),CAST(course_nbu AS DOUBLE) FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" ORDER BY `currencies_id` LIMIT 3 ");
			//положили временный масив в шаблонизатор
			SD.velocity.put("TimetoStart", dateoflist);
			SD.velocity.put("TimetoFinish", SELECT("SELECT TimetoFinish FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" LIMIT 1;"));
			SD.velocity.put("map", SD.TPLveloPrint);
			//шаблонизируем
			RenderPDF_img_too(SD.ShablonThisHtml(pre));
			break;
		   }
		   case 12:
		   {
		  //печать чеков

			//вытаскиваем html из обьекта шаблонов
			pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
			//достаём данные с бд нужно взять ключ мапа если он есть до продолжать
			SD.ListLinkedForPr.forEach((key,value)->{
			 if(MainForm.getjList1().getSelectedValue().toString().equals(GetZeroArr((ArrayList) value)))
			   {
				try {
				   ArrayList tmpz = new ArrayList();
				   tmpz.add("type");
				   tmpz.add("buyer_surname");
				   tmpz.add("buyer_first_name");
				   tmpz.add("buyer_last_name");
				   tmpz.add("passport_number");
				   tmpz.add("phone_number");
				   tmpz.add("currency_code");
				   tmpz.add("currency_course");
				   tmpz.add("currency_sum");
				   tmpz.add("grn_sum");
				   tmpz.add("receipt_currency");
				   tmpz.add("pfvalue");
				   ArrayList target = ReadSQLite(tmpz, "journal", "WHERE id_journal=\"" + key + "\"");
				   SD.velocity.put("type", target.get(0));
				   SD.velocity.put("buyer_surname", target.get(1));
				   SD.velocity.put("buyer_first_name", target.get(2));
				   SD.velocity.put("buyer_last_name", target.get(3));
				   SD.velocity.put("passport_number", target.get(4));
				   SD.velocity.put("phone_number", target.get(5));
				   SD.velocity.put("currency_code", target.get(6));
				   SD.velocity.put("currency_course", target.get(7));
				   SD.velocity.put("currency_sum", target.get(8));
				   SD.velocity.put("grn_sum", target.get(9));
				   SD.velocity.put("receipt_currency", target.get(10));
				   SD.velocity.put("pfvalue",target.get(11));
				}
				catch (ClassNotFoundException ex) {
				   Logger.getLogger(PreparePatterns.class.getName()).log(Level.SEVERE, null, ex);
				}
				catch (SQLException ex) {
				   Logger.getLogger(PreparePatterns.class.getName()).log(Level.SEVERE, null, ex);
				}

			   }
			   
			   
			});
			
			
			
		  RenderPDF_img_too(SD.ShablonThisHtml(pre));
		   
		   //по умолчанию берем пустой шаблон и печатаем    
			
			break;
		   }

			 
			    
			
		  
		   default:
			pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
			RenderPDF_img_too(pre);

			break;
		}

		ShowDialogMsgBox(show);
		Print(print);                 //
	   }
	}
	catch (ParserConfigurationException | SAXException | IOException | DocumentException | PrinterException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (ClassNotFoundException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (SQLException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (ParseException ex)
	{
	   Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
	}

   }

   public static void ShowDialogMsgBox(boolean a) throws IOException
   {
	if(a)
	{
	   ResizeImage img = new ResizeImage();
	}
   }

}
