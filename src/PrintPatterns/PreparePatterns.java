/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrintPatterns;

import com.itextpdf.text.DocumentException;
import static com.verbox.Date.ParseDateList;
import com.verbox.In;
import static com.verbox.PrintHtml.Print;
import static com.verbox.PrintHtml.RenderPDF_img_too;
import com.verbox.ResizeImage;
import com.verbox.StorageMemory;
import static com.verbox.StorageMemory.getInstance;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import static com.verbox.sqlite_metod.SELECT;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author maxxl
 */
public class PreparePatterns
{


   public PreparePatterns(boolean print,boolean show)
   {

	String dateoflist;

	try
	{
	   StorageMemory SD = getInstance();
	   In MainForm = In.getInstanceMain();
	   dateoflist = ParseDateList(MainForm.getjList1().getSelectedValue().toString());
	   // showMessageDialog(null, dateoflist);
	   String pre = null;
	   switch (MainForm.getjComboBox1().getSelectedIndex())
	   {
		//печать большого отчета по валютам
		case 0:
		   pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
		   //Прогнать через шаблонизатор 
		   SD.TPLveloPrint = new LinkedHashMap<String, ArrayList>();
		   SD.TPLveloPrint = ReadSQLiteMulti("SELECT currency_code,quantity_text,currency_name,CAST(course_buy AS DOUBLE),CAST(course_sale AS DOUBLE),CAST(course_nbu AS DOUBLE) FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" ORDER BY `currencies_id` LIMIT 22 ");
		   SD.velocity.put("map", SD.TPLveloPrint);
		   //положили временный масив в шаблонизатор
		   SD.velocity.put("TimetoStart", dateoflist);
		   SD.velocity.put("TimetoFinish", SELECT("SELECT TimetoFinish FROM `currencies` WHERE TimetoStart=\"" + dateoflist + "\" LIMIT 1;"));
		   //шаблонизируем

		   RenderPDF_img_too(SD.ShablonThisHtml(pre));
		   break;
		case 1:
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

		//по умолчанию берем пустой шаблон и печатаем    
		default:
		   pre = SD.PrintTpl.get(String.valueOf(MainForm.getjComboBox1().getSelectedItem())).toString();
		   RenderPDF_img_too(pre);

		   break;
	   }

	  
	   ShowDialogMsgBox(show);
         Print(print)  ;                 //
	   // Print();
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
	catch (Throwable ex)
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
