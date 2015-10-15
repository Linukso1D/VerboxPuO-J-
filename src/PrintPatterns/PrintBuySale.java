/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrintPatterns;

import com.itextpdf.text.DocumentException;
import com.verbox.In;
import static com.verbox.In.getInstanceMain;
import static com.verbox.PrintHtml.Print;
import static com.verbox.PrintHtml.RenderPDF_img_too;
import com.verbox.StorageMemory;
import static com.verbox.StorageMemory.getInstance;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author maxxl
 */
public class PrintBuySale
{//шаблонизируемчеловека его данные курсы и валюту и вообще всю операцию
   public PrintBuySale() 
   {
	StorageMemory SD = getInstance();
	
	String pre="";
	SD.getAction_name();
	SD.velocity.put("buyer_surname", SD.getBuyer_surname());
	SD.velocity.put("buyer_first_name", SD.getBuyer_first_name());
	SD.velocity.put("buyer_last_name", SD.getBuyer_last_name());
	SD.velocity.put("passport_number", SD.getPassport_number());
	SD.velocity.put("phone_number", SD.getPhone_number());
	SD.velocity.put("currency_code", SD.getCurrency_code());
	SD.velocity.put("currency_course", SD.getCurrency_course());
	SD.velocity.put("currency_sum()", SD.getCurrency_sum());
	SD.velocity.put("grn_sum", SD.getGrn_sum());
	SD.velocity.put("getIdqwi", SD.getIdqwi());
	
	
	pre=SD.PrintTpl.get("13 Квитанція про здійснення валютно-обмінної операції").toString();
	try
	{
	   RenderPDF_img_too(SD.ShablonThisHtml(pre));
	   //   Print(true);
	}
	catch (ParserConfigurationException ex)
	{
	   Logger.getLogger(PrintBuySale.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (SAXException ex)
	{
	   Logger.getLogger(PrintBuySale.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (IOException ex)
	{
	   Logger.getLogger(PrintBuySale.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (DocumentException ex)
	{
	   Logger.getLogger(PrintBuySale.class.getName()).log(Level.SEVERE, null, ex);
	}
	catch (PrinterException ex)
	{
	   Logger.getLogger(PrintBuySale.class.getName()).log(Level.SEVERE, null, ex);
	}
	
   }

}
