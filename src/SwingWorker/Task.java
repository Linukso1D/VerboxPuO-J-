/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingWorker;

import PrintPatterns.PreparePatterns;
import com.verbox.In;
import java.text.ParseException;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.SwingWorker;

/**
 *
 * @author maxxl
 */
class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() throws ClassNotFoundException, ParseException {
           //do in background
	     In maIn= In.getInstanceMain();
	     if(maIn.getjProgressBar1().getValue()==0)
	 {
	    PreparePatterns ab = new PreparePatterns(false,true) ;
	 }
else
	 {
	    showMessageDialog(null, "Операция уже запущена");
	 }
	     
	     
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
           
	     
	     
        }
	  
    }