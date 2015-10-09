/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;
import static com.verbox.PrintHtml.Print;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author maxxl
 */
public class ResizeImage {

    public ResizeImage() throws IOException {
        
       
        final BufferedImage bi = ImageIO.read(new File("name_img.png"));

        Runnable r = new Runnable() {
            public void run() {
                In tmpobj = null;
                try {
                    tmpobj = In.getInstanceMain();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ResizeImage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ResizeImage.class.getName()).log(Level.SEVERE, null, ex);
                }
                JLabel unresize = new JLabel(new ImageIcon(bi));

                int width = (int)(bi.getWidth()*.85);
                int height = (int)(bi.getHeight()*.85);

               int imgWidth = (int)(bi.getWidth()*.75);
                int imgHeight = (int)(bi.getHeight()*.75);
                if (imgWidth*height < imgHeight*width) {
                    width = imgWidth*height/imgHeight;
                } else {
                    height = imgHeight*width/imgWidth;
                }
                BufferedImage newImage = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = newImage.createGraphics();
                try {
                    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);

                    g.clearRect(0, 0, width, height);
                    g.drawImage(bi, 0, 0, width, height, null);
                } finally {
                    g.dispose();
                }
    


              //  JOptionPane.showMessageDialog(null, p);
                
                
                
                Object[] options = {"Печать",
                    "Отмена."
                    };
                JInternalFrame frame = new JInternalFrame();
    int n = JOptionPane.showOptionDialog(frame,
    "",
    "Предварительный просмотр",
    JOptionPane.YES_NO_OPTION,
    JOptionPane.PLAIN_MESSAGE,
    new ImageIcon(newImage),
    options,
    options[1]);
    if (n == JOptionPane.YES_OPTION) {
       boolean pr = false;
                    try {
                        pr = Print(true);
                    } catch (PrinterException ex) {
                        Logger.getLogger(ResizeImage.class.getName()).log(Level.SEVERE, null, ex);
                    }
       if(pr)
       {
            JOptionPane.showMessageDialog(null,"Напечатал");
       }
       else
       {
           JOptionPane.showMessageDialog(null,"Проверьте конфигурацию принтера");
       }
}
                
                
                
            }
        };
        SwingUtilities.invokeLater(r);
        
        
        
    }




       
    
}

