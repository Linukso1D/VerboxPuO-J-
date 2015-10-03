/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;




import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


/**
 *
 * @author maxxl
 */
public class PrintHtml {
public static String fullhtml;
    /**
     * @param inpHtml
     * @throws java.io.FileNotFoundException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.awt.print.PrinterException
     * @throws com.itextpdf.text.DocumentException
     */
public static boolean Print() throws PrinterException
{
    try {
        PDDocument doc = PDDocument.load("pdf.pdf");
        PrinterJob job = PrinterJob.getPrinterJob();
        PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
        job.setPrintService(printer);
        doc.silentPrint(job);
        doc.close();
        boolean flag = true;
        return flag;
    } catch (IOException ex) {
        Logger.getLogger(PrintHtml.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    public static void RenderPDF_img_too(String inpHtml) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, DocumentException, PrinterException {
                    try
                    {
                      fullhtml=inpHtml;
                      String replaceAll = fullhtml.replaceAll("../../image/", "");
                      Document document = new Document();
                      // step 2
                      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
                      // step 3
                      document.open();
                      // step 4
                      XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
                      InputStream is = new ByteArrayInputStream(replaceAll.getBytes(StandardCharsets.UTF_8));
                      worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));
                      // step 5
                      document.close();
                      PreImgPrint();
                      
                  
                    }
                    catch(DocumentException | IOException E)
                    {
                        JOptionPane.showMessageDialog(null,"Exeption on print or prepare image "+ E);
                    }
}
    public static void PreImgPrint() throws IOException
    {
    
    
    int resolution = Toolkit.getDefaultToolkit().getScreenResolution();

String pdfPath = "name_img";

//load pdf document
PDDocument document = PDDocument.load("pdf.pdf");

List<PDPage> pages = document.getDocumentCatalog().getAllPages();

//Read first page
PDPage page = pages.get(0);

//Convert To Image          
BufferedImage previewImage = page.convertToImage(BufferedImage.TYPE_INT_RGB, resolution);

//Save to file
ImageIO.write(previewImage, "png", new File(pdfPath + ".png"));
    
    
    }
    
    public static BufferedImage ScaleImage(int WIDTH, int HEIGHT, String filename) {
    BufferedImage bi = null;
    try {
        ImageIcon ii = new ImageIcon(filename);//path to image
        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return bi;
}

    
    
    

}