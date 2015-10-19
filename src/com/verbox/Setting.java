/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.verbox;

import static com.verbox.sqlite_metod.InviZ;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import static com.verbox.sqlite_metod.UPDATE_cheked;
import static com.verbox.sqlite_metod.cureList_GetLog;
import static com.verbox.sqlite_metod.cureList_GetPass;
import static com.verbox.sqlite_metod.cureList_SetCash;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author maxxl
 */
public class Setting extends javax.swing.JFrame {
private Map Active,Bablo;
    /** Creates new form Setting
     * @return  */
public static String GetZeroArr(ArrayList tmpz )
{
  
   
 return (String)tmpz.get(0);
}

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Setting() throws SQLException, ClassNotFoundException {
        initComponents();
	  StorageMemory SD = StorageMemory.getInstance();
        Active= new HashMap<String,String>();
        Bablo=new HashMap<String,Double>();
        jTextField23.setText(cureList_GetLog());
        jTextField24.setText(cureList_GetPass());
        //UPDATE_cheked
        jPanel1.setVisible(SD.superuser);
        try{
        Active = ReadSQLiteMulti("SELECT currency_code,active FROM `SDbalance` ORDER BY `id_SDbalance` DESC LIMIT 23");
        Bablo = ReadSQLiteMulti("SELECT currency_code,balance FROM `SDbalance` ORDER BY `id_SDbalance` DESC LIMIT 23");
        

            

            
        
        
        jTextField1.setText(GetZeroArr((ArrayList) Bablo.get("840")));
        jTextField2.setText(GetZeroArr((ArrayList) Bablo.get("978")));
        jTextField3.setText(GetZeroArr((ArrayList) Bablo.get("643")));
        jTextField4.setText(GetZeroArr((ArrayList) Bablo.get("826")));
        jTextField5.setText(GetZeroArr((ArrayList) Bablo.get("756")));
        jTextField6.setText(GetZeroArr((ArrayList) Bablo.get("124")));
        jTextField7.setText(GetZeroArr((ArrayList) Bablo.get("30")));
        jTextField8.setText(GetZeroArr((ArrayList) Bablo.get("208")));
        jTextField9.setText(GetZeroArr((ArrayList) Bablo.get("578")));
        jTextField10.setText(GetZeroArr((ArrayList) Bablo.get("752")));
        jTextField11.setText(GetZeroArr((ArrayList) Bablo.get("352")));
        jTextField12.setText(GetZeroArr((ArrayList) Bablo.get("392")));
        jTextField13.setText(GetZeroArr((ArrayList) Bablo.get("156")));
        jTextField14.setText(GetZeroArr((ArrayList) Bablo.get("974")));
        jTextField15.setText(GetZeroArr((ArrayList) Bablo.get("203")));
        jTextField16.setText(GetZeroArr((ArrayList) Bablo.get("985")));
        jTextField17.setText(GetZeroArr((ArrayList) Bablo.get("348")));
        jTextField18.setText(GetZeroArr((ArrayList) Bablo.get("376")));
        jTextField19.setText(GetZeroArr((ArrayList) Bablo.get("398")));
        jTextField20.setText(GetZeroArr((ArrayList) Bablo.get("440")));
        jTextField21.setText(GetZeroArr((ArrayList) Bablo.get("498")));
        jTextField22.setText(GetZeroArr((ArrayList) Bablo.get("946")));
        jTextField25.setText(GetZeroArr((ArrayList) Bablo.get("980")));
        
        //включенность
        jCheckBox1.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("840"))));
        jCheckBox2.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("978"))));
        jCheckBox3.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("643"))));
        jCheckBox4.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("826"))));
        jCheckBox5.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("756"))));
        jCheckBox6.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("124"))));
        jCheckBox7.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("30"))));
        jCheckBox8.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("208"))));
        jCheckBox9.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("578"))));
        jCheckBox10.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("752"))));
        jCheckBox11.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("352"))));
        jCheckBox12.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("392"))));
        jCheckBox13.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("156"))));
        jCheckBox14.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("974"))));
        jCheckBox15.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("203"))));
        jCheckBox16.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("985"))));
        jCheckBox17.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("348"))));
        jCheckBox18.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("376"))));
        jCheckBox19.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("398"))));
        jCheckBox20.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("440"))));
        jCheckBox21.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("498"))));
        jCheckBox22.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("946"))));
        jCheckBox23.setSelected(Boolean.parseBoolean(GetZeroArr((ArrayList) Active.get("980"))));
        
        
        }
        catch(NullPointerException e)
        {
            showMessageDialog(null, "Повреждена база данных");
        }
        /*  
      
        

       */ 
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents()
   {

      jPanel1 = new javax.swing.JPanel();
      jTextField23 = new javax.swing.JTextField();
      jTextField24 = new javax.swing.JTextField();
      jLabel3 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jButton1 = new javax.swing.JButton();
      jPanel2 = new javax.swing.JPanel();
      jCheckBox1 = new javax.swing.JCheckBox();
      jCheckBox2 = new javax.swing.JCheckBox();
      jCheckBox3 = new javax.swing.JCheckBox();
      jCheckBox4 = new javax.swing.JCheckBox();
      jCheckBox5 = new javax.swing.JCheckBox();
      jCheckBox6 = new javax.swing.JCheckBox();
      jCheckBox7 = new javax.swing.JCheckBox();
      jCheckBox8 = new javax.swing.JCheckBox();
      jCheckBox9 = new javax.swing.JCheckBox();
      jCheckBox10 = new javax.swing.JCheckBox();
      jCheckBox11 = new javax.swing.JCheckBox();
      jCheckBox12 = new javax.swing.JCheckBox();
      jCheckBox13 = new javax.swing.JCheckBox();
      jCheckBox14 = new javax.swing.JCheckBox();
      jCheckBox15 = new javax.swing.JCheckBox();
      jCheckBox16 = new javax.swing.JCheckBox();
      jCheckBox17 = new javax.swing.JCheckBox();
      jCheckBox18 = new javax.swing.JCheckBox();
      jCheckBox19 = new javax.swing.JCheckBox();
      jCheckBox20 = new javax.swing.JCheckBox();
      jCheckBox21 = new javax.swing.JCheckBox();
      jCheckBox22 = new javax.swing.JCheckBox();
      jLabel1 = new javax.swing.JLabel();
      jTextField1 = new javax.swing.JTextField();
      jTextField2 = new javax.swing.JTextField();
      jTextField3 = new javax.swing.JTextField();
      jTextField4 = new javax.swing.JTextField();
      jTextField5 = new javax.swing.JTextField();
      jTextField6 = new javax.swing.JTextField();
      jTextField7 = new javax.swing.JTextField();
      jTextField8 = new javax.swing.JTextField();
      jTextField9 = new javax.swing.JTextField();
      jTextField10 = new javax.swing.JTextField();
      jTextField11 = new javax.swing.JTextField();
      jTextField12 = new javax.swing.JTextField();
      jTextField13 = new javax.swing.JTextField();
      jTextField14 = new javax.swing.JTextField();
      jTextField15 = new javax.swing.JTextField();
      jTextField16 = new javax.swing.JTextField();
      jTextField17 = new javax.swing.JTextField();
      jTextField18 = new javax.swing.JTextField();
      jTextField19 = new javax.swing.JTextField();
      jTextField20 = new javax.swing.JTextField();
      jTextField21 = new javax.swing.JTextField();
      jTextField22 = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      jCheckBox23 = new javax.swing.JCheckBox();
      jTextField25 = new javax.swing.JTextField();

      setBackground(new java.awt.Color(255, 255, 255));

      jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Авторизация"));

      jLabel3.setText("Логин");

      jLabel4.setText("Пароль");

      jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jLabel5.setText("<html>Логин и пароль для Http авторизации <br />такой же как и доступ к настройкам.</html>");
      jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

      jButton1.setText("Сохранить");
      jButton1.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jButton1MouseClicked(evt);
         }
      });
      jButton1.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            jButton1ActionPerformed(evt);
         }
      });

      org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
               .add(jPanel1Layout.createSequentialGroup()
                  .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                     .add(jTextField24, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                     .add(jTextField23))))
            .addContainerGap())
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel1Layout.createSequentialGroup()
            .add(40, 40, 40)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jTextField23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jLabel3))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jTextField24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jLabel4))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jButton1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      jLabel5.getAccessibleContext().setAccessibleName("");

      jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Активные валюты"));

      jCheckBox1.setText("Доллар  США");
      jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener()
      {
         public void stateChanged(javax.swing.event.ChangeEvent evt)
         {
            jCheckBox1StateChanged(evt);
         }
      });
      jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox1MouseClicked(evt);
         }
      });
      jCheckBox1.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            jCheckBox1ActionPerformed(evt);
         }
      });

      jCheckBox2.setText("Евро");
      jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox2MouseClicked(evt);
         }
      });

      jCheckBox3.setText("Російський рубль");
      jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox3MouseClicked(evt);
         }
      });

      jCheckBox4.setText("Англійський фунт стерлінгів");
      jCheckBox4.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox4MouseClicked(evt);
         }
      });

      jCheckBox5.setText("Швейцарський франк");
      jCheckBox5.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox5MouseClicked(evt);
         }
      });

      jCheckBox6.setText("Канадський доллар");
      jCheckBox6.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox6MouseClicked(evt);
         }
      });

      jCheckBox7.setText("Австралійський доллар");
      jCheckBox7.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox7MouseClicked(evt);
         }
      });

      jCheckBox8.setText("Данська крона");
      jCheckBox8.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox8MouseClicked(evt);
         }
      });

      jCheckBox9.setText("Норвезька крона");
      jCheckBox9.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox9MouseClicked(evt);
         }
      });

      jCheckBox10.setText("Шведська крона");
      jCheckBox10.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox10MouseClicked(evt);
         }
      });

      jCheckBox11.setText("Ісландська крона");
      jCheckBox11.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox11MouseClicked(evt);
         }
      });

      jCheckBox12.setText("Японська крона");
      jCheckBox12.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox12MouseClicked(evt);
         }
      });

      jCheckBox13.setText("Юань Женьміньбі");
      jCheckBox13.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox13MouseClicked(evt);
         }
      });

      jCheckBox14.setText("Білоруський рубль");
      jCheckBox14.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox14MouseClicked(evt);
         }
      });

      jCheckBox15.setText("Чеська крона");
      jCheckBox15.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox15MouseClicked(evt);
         }
      });

      jCheckBox16.setText("Польський золотий");
      jCheckBox16.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox16MouseClicked(evt);
         }
      });

      jCheckBox17.setText("Угорський форинт");
      jCheckBox17.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox17MouseClicked(evt);
         }
      });

      jCheckBox18.setText("Новий ізраїльський шеке");
      jCheckBox18.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox18MouseClicked(evt);
         }
      });

      jCheckBox19.setText("Казахстанський теньге");
      jCheckBox19.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox19MouseClicked(evt);
         }
      });

      jCheckBox20.setText("Литовський лит");
      jCheckBox20.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox20MouseClicked(evt);
         }
      });

      jCheckBox21.setText("Молдавський лей");
      jCheckBox21.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox21MouseClicked(evt);
         }
      });

      jCheckBox22.setText("Новий румунський лей");
      jCheckBox22.addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            jCheckBox22MouseClicked(evt);
         }
      });
      jCheckBox22.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            jCheckBox22ActionPerformed(evt);
         }
      });

      jLabel1.setText("Валюта в кассе");

      jTextField1.setEditable(false);
      jTextField1.setText(" ");

      jTextField2.setEditable(false);
      jTextField2.setText(" ");

      jTextField3.setEditable(false);
      jTextField3.setText(" ");

      jTextField4.setEditable(false);
      jTextField4.setText(" ");

      jTextField5.setEditable(false);
      jTextField5.setText(" ");

      jTextField6.setEditable(false);
      jTextField6.setText(" ");

      jTextField7.setEditable(false);
      jTextField7.setText(" ");

      jTextField8.setEditable(false);
      jTextField8.setText(" ");

      jTextField9.setEditable(false);
      jTextField9.setText(" ");

      jTextField10.setEditable(false);
      jTextField10.setText(" ");

      jTextField11.setEditable(false);
      jTextField11.setText(" ");

      jTextField12.setEditable(false);
      jTextField12.setText(" ");

      jTextField13.setEditable(false);
      jTextField13.setText(" ");

      jTextField14.setEditable(false);
      jTextField14.setText(" ");

      jTextField15.setEditable(false);
      jTextField15.setText(" ");

      jTextField16.setEditable(false);
      jTextField16.setText(" ");

      jTextField17.setEditable(false);
      jTextField17.setText(" ");

      jTextField18.setEditable(false);
      jTextField18.setText(" ");

      jTextField19.setEditable(false);
      jTextField19.setText(" ");

      jTextField20.setEditable(false);
      jTextField20.setText(" ");

      jTextField21.setEditable(false);
      jTextField21.setText(" ");

      jTextField22.setEditable(false);
      jTextField22.setText(" ");

      jLabel2.setText("Валюта в кассе");

      jCheckBox23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      jCheckBox23.setSelected(true);
      jCheckBox23.setText("Гривна");
      jCheckBox23.setEnabled(false);

      jTextField25.setEnabled(false);

      org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel2Layout.createSequentialGroup()
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                           .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jCheckBox12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                  .add(18, 18, 18)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jLabel2)
                     .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
               .add(jPanel2Layout.createSequentialGroup()
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jCheckBox21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jCheckBox11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jCheckBox22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                  .add(18, 18, 18)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
               .add(jPanel2Layout.createSequentialGroup()
                  .add(jCheckBox23)
                  .add(18, 18, 18)
                  .add(jTextField25)))
            .addContainerGap(17, Short.MAX_VALUE))
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createSequentialGroup()
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel2Layout.createSequentialGroup()
                  .add(22, 22, 22)
                  .add(jLabel2)
                  .add(18, 18, 18)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                     .add(jCheckBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                     .add(jCheckBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jCheckBox13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jCheckBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jCheckBox4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jCheckBox5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                           .add(jCheckBox6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                              .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                              .add(jCheckBox17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                              .add(jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(8, 8, 8)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jCheckBox8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(jCheckBox9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jCheckBox20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                     .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
               .add(jPanel2Layout.createSequentialGroup()
                  .add(21, 21, 21)
                  .add(jLabel1)))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jCheckBox21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jCheckBox10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jCheckBox11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jCheckBox22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jTextField22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(18, 18, 18)
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jCheckBox23)
               .add(jTextField25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(79, Short.MAX_VALUE))
      );

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        
       try {
    //install login&password httpconn
        if(cureList_SetCash("",jTextField23.getText(),jTextField24.getText()))
        {
        showMessageDialog(null, "Сохранено");
        
        }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(rootes.class.getName()).log(Level.SEVERE, null, ex);
        }


// TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jCheckBox22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox22ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
   
       // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
 if(jCheckBox1.isSelected())
    {
        
        try {
            
            UPDATE_cheked("840",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("840",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
if(jCheckBox2.isSelected())
    {
        
        try {
            
            UPDATE_cheked("978",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("978",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseClicked
if(jCheckBox3.isSelected())
    {
        
        try {
            
            UPDATE_cheked("643",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("643",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3MouseClicked

    private void jCheckBox4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox4MouseClicked
if(jCheckBox4.isSelected())
    {
        
        try {
            
            UPDATE_cheked("826",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("826",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4MouseClicked

    private void jCheckBox5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox5MouseClicked
if(jCheckBox5.isSelected())
    {
        
        try {
            
            UPDATE_cheked("756",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("756",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5MouseClicked

    private void jCheckBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox6MouseClicked
if(jCheckBox6.isSelected())
    {
        
        try {
            
            UPDATE_cheked("124",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("124",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6MouseClicked

    private void jCheckBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox7MouseClicked
if(jCheckBox7.isSelected())
    {
        
        try {
            
            UPDATE_cheked("30",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("30",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7MouseClicked

    private void jCheckBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox8MouseClicked
if(jCheckBox8.isSelected())
    {
        
        try {
            
            UPDATE_cheked("208",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("208",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox8MouseClicked

    private void jCheckBox9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox9MouseClicked
if(jCheckBox9.isSelected())
    {
        
        try {
            
            UPDATE_cheked("578",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("578",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox9MouseClicked

    private void jCheckBox10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox10MouseClicked
if(jCheckBox10.isSelected())
    {
        
        try {
            
            UPDATE_cheked("752",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("752",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox10MouseClicked

    private void jCheckBox11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox11MouseClicked
if(jCheckBox11.isSelected())
    {
        
        try {
            
            UPDATE_cheked("352",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("352",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox11MouseClicked

    private void jCheckBox12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox12MouseClicked
if(jCheckBox12.isSelected())
    {
        
        try {
            
            UPDATE_cheked("392",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("392",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox12MouseClicked

    private void jCheckBox13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox13MouseClicked
if(jCheckBox13.isSelected())
    {
        
        try {
            
            UPDATE_cheked("156",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("156",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox13MouseClicked

    private void jCheckBox14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox14MouseClicked
if(jCheckBox14.isSelected())
    {
        
        try {
            
            UPDATE_cheked("974",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("974",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox14MouseClicked

    private void jCheckBox15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox15MouseClicked
if(jCheckBox15.isSelected())
    {
        
        try {
            
            UPDATE_cheked("203",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("203",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox15MouseClicked

    private void jCheckBox16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox16MouseClicked
if(jCheckBox16.isSelected())
    {
        
        try {
            
            UPDATE_cheked("985",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("985",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox16MouseClicked

    private void jCheckBox17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox17MouseClicked
if(jCheckBox17.isSelected())
    {
        
        try {
            
            UPDATE_cheked("348",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("348",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox17MouseClicked

    private void jCheckBox18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox18MouseClicked
if(jCheckBox18.isSelected())
    {
        
        try {
            
            UPDATE_cheked("376",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("376",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox18MouseClicked

    private void jCheckBox19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox19MouseClicked
if(jCheckBox19.isSelected())
    {
        
        try {
            
            UPDATE_cheked("398",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("398",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox19MouseClicked

    private void jCheckBox20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox20MouseClicked
if(jCheckBox20.isSelected())
    {
        
        try {
            
            UPDATE_cheked("440",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("440",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox20MouseClicked

    private void jCheckBox21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox21MouseClicked
if(jCheckBox21.isSelected())
    {
        
        try {
            
            UPDATE_cheked("498",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("498",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox21MouseClicked

    private void jCheckBox22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox22MouseClicked
if(jCheckBox22.isSelected())
    {
        
        try {
            
            UPDATE_cheked("946",true);
        } catch (SQLException ex) {
            Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
 else
 {
 
     try {
         UPDATE_cheked("946",false);
     } catch (SQLException ex) {
         Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
     }
 
 }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox22MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Setting().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Setting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButton1;
   private javax.swing.JCheckBox jCheckBox1;
   private javax.swing.JCheckBox jCheckBox10;
   private javax.swing.JCheckBox jCheckBox11;
   private javax.swing.JCheckBox jCheckBox12;
   private javax.swing.JCheckBox jCheckBox13;
   private javax.swing.JCheckBox jCheckBox14;
   private javax.swing.JCheckBox jCheckBox15;
   private javax.swing.JCheckBox jCheckBox16;
   private javax.swing.JCheckBox jCheckBox17;
   private javax.swing.JCheckBox jCheckBox18;
   private javax.swing.JCheckBox jCheckBox19;
   private javax.swing.JCheckBox jCheckBox2;
   private javax.swing.JCheckBox jCheckBox20;
   private javax.swing.JCheckBox jCheckBox21;
   private javax.swing.JCheckBox jCheckBox22;
   private javax.swing.JCheckBox jCheckBox23;
   private javax.swing.JCheckBox jCheckBox3;
   private javax.swing.JCheckBox jCheckBox4;
   private javax.swing.JCheckBox jCheckBox5;
   private javax.swing.JCheckBox jCheckBox6;
   private javax.swing.JCheckBox jCheckBox7;
   private javax.swing.JCheckBox jCheckBox8;
   private javax.swing.JCheckBox jCheckBox9;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JTextField jTextField1;
   private javax.swing.JTextField jTextField10;
   private javax.swing.JTextField jTextField11;
   private javax.swing.JTextField jTextField12;
   private javax.swing.JTextField jTextField13;
   private javax.swing.JTextField jTextField14;
   private javax.swing.JTextField jTextField15;
   private javax.swing.JTextField jTextField16;
   private javax.swing.JTextField jTextField17;
   private javax.swing.JTextField jTextField18;
   private javax.swing.JTextField jTextField19;
   private javax.swing.JTextField jTextField2;
   private javax.swing.JTextField jTextField20;
   private javax.swing.JTextField jTextField21;
   private javax.swing.JTextField jTextField22;
   private javax.swing.JTextField jTextField23;
   private javax.swing.JTextField jTextField24;
   private javax.swing.JTextField jTextField25;
   private javax.swing.JTextField jTextField3;
   private javax.swing.JTextField jTextField4;
   private javax.swing.JTextField jTextField5;
   private javax.swing.JTextField jTextField6;
   private javax.swing.JTextField jTextField7;
   private javax.swing.JTextField jTextField8;
   private javax.swing.JTextField jTextField9;
   // End of variables declaration//GEN-END:variables

}
