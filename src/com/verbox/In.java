/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import com.itextpdf.text.DocumentException;
import static com.verbox.Date.getShortDate;
import static com.verbox.MyMath.round;
import static com.verbox.PrintHtml.PreImgPrint;
import static com.verbox.PrintHtml.Print;

import static com.verbox.Setting.GetDoubleStr;
import static com.verbox.StorageMemory.getInstance;
import static com.verbox.json_metod.SendPost;
import static com.verbox.sqlite_metod.ReadSQLite;
import static com.verbox.sqlite_metod.ReadSQLiteMulti;
import static com.verbox.sqlite_metod.SELECT;
import static com.verbox.sqlite_metod.UPDATE;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import static com.verbox.PrintHtml.RenderPDF_img_too;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.TileObserver;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sun.awt.image.OffScreenImage;

/**
 *
 * @author maxxl
 */
public class In extends javax.swing.JFrame {

    /**
     *
     * @param str
     * @return
     */

    
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Creates new form In
     */
    private static volatile In instance;
    double pf;
    boolean inpf;
public static  In getInstanceMain() throws ClassNotFoundException {
        if (instance == null) {
            synchronized (In.class) {
                if (instance == null) {
                    instance = new In();
                }
                System.out.println("Create new Instanse Main");
            }
        }
        System.out.println("Return Instanse Main");

        return instance;
    }




     private MouseListener mouseListener;


    /**
     *
     * @throws ClassNotFoundException
     */
    public In() throws ClassNotFoundException {
        
        
        
      
        
        initComponents();
        //ныкать панели
        HideEl();
        //  размер окна
        setSize(1200, 800);
        jPanel11.setVisible(true);
        //Присвоение гет инфо
        StorageMemory obj = getInstance();
jFormattedTextField1.setText(getShortDate());
jFormattedTextField2.setText(getShortDate());
        try {
            jTextField19.setText(obj.StorageGetInfo("enterprise_full_name"));
            jTextField20.setText(obj.StorageGetInfo("enterprise_short_name"));
            //fiz addr
            jTextField21.setText(obj.StorageGetInfo("nat_city_code"));
            jTextField22.setText(obj.StorageGetInfo("nat_city"));
            jTextField23.setText(obj.StorageGetInfo("nat_street"));
            jTextField24.setText(obj.StorageGetInfo("nat_house"));
            jTextField25.setText(obj.StorageGetInfo("nat_office"));
            //ur addr
            jTextField26.setText(obj.StorageGetInfo("ur_city_code"));
            jTextField27.setText(obj.StorageGetInfo("ur_city"));
            jTextField28.setText(obj.StorageGetInfo("ur_street"));
            jTextField29.setText(obj.StorageGetInfo("ur_house"));
            jTextField30.setText(obj.StorageGetInfo("ur_office"));
            // mfo okpo Rs
            jTextField31.setText(obj.StorageGetInfo("enterprise_mfo"));
            jTextField32.setText(obj.StorageGetInfo("enterprise_okpo"));
            jTextField33.setText(obj.StorageGetInfo("payment_account"));
            //Директор данные
            jTextField37.setText(obj.StorageGetInfo("dir_fio"));
            jTextField34.setText(obj.StorageGetInfo("dir_surname"));
            jTextField35.setText(obj.StorageGetInfo("dir_first_name"));
            jTextField36.setText(obj.StorageGetInfo("dir_last_name"));
            //Бухгалтер данные
            jTextField38.setText(obj.StorageGetInfo("bookk_fio"));
            jTextField39.setText(obj.StorageGetInfo("bookk_surname"));
            jTextField40.setText(obj.StorageGetInfo("bookk_first_name"));
            jTextField41.setText(obj.StorageGetInfo("bookk_last_name"));
            
            //курсы USD
           RefreshINF();
            
        } catch (NullPointerException e) {
            showMessageDialog(null, "Не удалось загрузить информацию от предприятии!!!");
            System.out.println("EXEPTION" + e);
        }

        //заполнения дропдауна активными валютами
        try {
            ArrayList tm = new ArrayList();
            Map ListActiveCourse = new HashMap<String, String>();
            ListActiveCourse = ReadSQLiteMulti(
                    "SELECT sd.currency_code,c.currency_name FROM\n"
                    + "SDbalance AS sd\n"
                    + "INNER JOIN \n"
                    + "currencies AS c ON c.currency_code=sd.currency_code\n"
                    + "WHERE sd.active=\"true\" ORDER BY `id_SDbalance` ");
            ListActiveCourse.put("980", "[Гривна]");
            Set<String> keys = ListActiveCourse.keySet();
            for (String key : keys) {
                jComboBox2.addItem(ListActiveCourse.get(key));
                tm.add(key);

            }
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            StorageMemory SD = getInstance();
            SD.setTempForSelectDropdown(tm);

        } catch (NullPointerException e) {
            showMessageDialog(null, "Не удалось получить список валют проверьте активность валют в настройках.");
            System.out.println("EXEPTION" + e);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }

      

        

    
    
    
    // list listener
    jList1.addMouseListener(new MouseAdapter() 
{
    @Override
    public void mouseClicked(MouseEvent mouseEvent) 
    {


         JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 1) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        
                        //событие щелчка на лист в отчетах
                        String dateoflist;
                       
                        try {
                            StorageMemory SD =getInstance();
                           
                            dateoflist=ParseDateList(o.toString());
                            // showMessageDialog(null, dateoflist);
                            String pre = null;
                            switch(jComboBox1.getSelectedIndex())
                            {
                                //печать большого отчета по валютам
                                case 0:
                                        pre = SD.PrintTpl.get(String.valueOf(jComboBox1.getSelectedItem())).toString();
                                        //Прогнать через шаблонизатор 
                                        SD.TPLveloPrint=new LinkedHashMap<String,ArrayList>();
                                        SD.TPLveloPrint= ReadSQLiteMulti("SELECT currency_code,quantity_text,currency_name,CAST(course_buy AS DOUBLE),CAST(course_sale AS DOUBLE),CAST(course_nbu AS DOUBLE) FROM `currencies` WHERE TimetoStart=\""+dateoflist+"\" ORDER BY `currencies_id` LIMIT 22 ");
                                        SD.velocity.put("map", SD.TPLveloPrint);
                                        //положили временный масив в шаблонизатор
                                        SD.velocity.put("TimetoStart", dateoflist);
                                        SD.velocity.put("TimetoFinish", SELECT("SELECT TimetoFinish FROM `currencies` WHERE TimetoStart=\""+dateoflist+"\" LIMIT 1;"));
                                        //шаблонизируем
                                        
                                        RenderPDF_img_too(SD.ShablonThisHtml(pre));break;
                                case 1:
                                        pre = SD.PrintTpl.get(String.valueOf(jComboBox1.getSelectedItem())).toString();
                                        //Прогнать через шаблонизатор 
                                        SD.TPLveloPrint=new LinkedHashMap<String,ArrayList>();
                                        SD.TPLveloPrint= ReadSQLiteMulti("SELECT currency_code,quantity_text,currency_name,CAST(course_buy AS DOUBLE),CAST(course_sale AS DOUBLE),CAST(course_nbu AS DOUBLE) FROM `currencies` WHERE TimetoStart=\""+dateoflist+"\" ORDER BY `currencies_id` LIMIT 3 ");
                                        //положили временный масив в шаблонизатор
                                        SD.velocity.put("TimetoStart", dateoflist);
                                        SD.velocity.put("TimetoFinish", SELECT("SELECT TimetoFinish FROM `currencies` WHERE TimetoStart=\""+dateoflist+"\" LIMIT 1;"));
                                        SD.velocity.put("map", SD.TPLveloPrint);
                                        //шаблонизируем
                                        RenderPDF_img_too(SD.ShablonThisHtml(pre));
                                        
                                        break;
                                        
                                   //по умолчанию берем пустой шаблон и печатаем    
                                   default: pre = SD.PrintTpl.get(String.valueOf(jComboBox1.getSelectedItem())).toString(); 
                                        RenderPDF_img_too(pre);
                                        
                                        break;
                            }
                        
               

   ResizeImage img = new ResizeImage();
   
                            //
                           

                  
                          
                          // Print();
                            
                            
                        } catch (ParserConfigurationException | SAXException | IOException | DocumentException | PrinterException ex) {
                            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Throwable ex) {
                            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        System.out.println("clicked on: " + o.toString());
                    }
                }
    }

            private String ParseDateList(String s) throws java.text.ParseException {
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
        });
    
       
    
        // combobox 1 listener
        jComboBox1.addActionListener (new ActionListenerImpl());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField7 = new javax.swing.JFormattedTextField();
        jTextField8 = new javax.swing.JFormattedTextField();
        jTextField9 = new javax.swing.JFormattedTextField();
        jTextField10 = new javax.swing.JFormattedTextField();
        jTextField11 = new javax.swing.JFormattedTextField();
        jTextField12 = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Обмен валют");
        setBackground(new java.awt.Color(252, 252, 252));
        setBounds(new java.awt.Rectangle(10, 10, 0, 0));
        setMaximumSize(new java.awt.Dimension(500, 500));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        jPanel1.setMinimumSize(new java.awt.Dimension(640, 480));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Подкрепления", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(491, 675));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "ы", null, null, "1", "2"},
                {null, "ы", null, null, null, "1"},
                {null, null, null, null, null, "2"},
                {"1", null, "2", "3", "5", "4"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Код валюты", "Номер документа", "Дата", "Время", "Сумма", "Пр.документа"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Инкасация", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(474, 675));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ы", "890", null, "4", "6", "6"},
                {"ы", "65", "2", "2", "1", "2"},
                {"ы", "4", null, null, null, null},
                {null, "8", null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Номер документа", "Код валюты", "Дата", "Время", "Сумма", "Пр. документа"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                .add(jScrollPane3)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Общее состояние", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(474, 675));

        jButton4.setBackground(new java.awt.Color(235, 255, 235));
        jButton4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton4.setText("Открыть день");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Грн",  new Integer(552), null,  new Integer(416),  new Integer(1),  new Integer(4),  new Integer(64)},
                {"Доллар",  new Integer(1),  new Integer(2),  new Integer(3),  new Integer(5),  new Integer(4),  new Integer(7)},
                {"Евро",  new Integer(3),  new Integer(1),  new Integer(1),  new Integer(5),  new Integer(6),  new Integer(1)},
                {"Руб",  new Integer(1),  new Integer(1),  new Integer(2),  new Integer(3),  new Integer(4), null}
            },
            new String [] {
                "Валюта", "Остаток на начало дня", "Подкрепление", "Инкассировано", "Приход", "Расход", "Итого"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton5.setBackground(new java.awt.Color(255, 225, 225));
        jButton5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton5.setText("Закрыть день");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel11Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton4)
                    .add(jButton5)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Данные покупателя", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel7.setText("Фамилия");

        jLabel8.setText("Имя");

        jLabel9.setText("Отчество");

        jLabel10.setText("Серия паспорта");

        jLabel11.setText("Номер паспорта");

        jLabel12.setText("Номер телефона");

        jCheckBox1.setText("Резидент");

        try {
            jTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????????????????????")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("??")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("+##(###) ### ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCheckBox1)
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .add(jTextField7)
                            .add(jTextField10))
                        .add(22, 22, 22)
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .add(jTextField11))
                            .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jTextField9)
                            .add(jTextField12))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jLabel9))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(jLabel11)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jCheckBox1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Курсы валют", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel13.setText("Покупка");

        jLabel14.setText("Продажа");

        jSeparator1.setForeground(new java.awt.Color(216, 216, 216));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("USD");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setText("EUR");

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel17.setText("RUB");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField13.setText("28.035");
        jTextField13.setFocusable(false);

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField14.setText("28.035");
        jTextField14.setFocusable(false);

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField15.setText("28.035");
        jTextField15.setFocusable(false);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField16.setText("28.035");
        jTextField16.setFocusable(false);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField17.setText("28.035");
        jTextField17.setFocusable(false);
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        jTextField18.setText("28.035");
        jTextField18.setFocusable(false);
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("НБУ");
        jLabel46.setToolTipText("");

        jLabel47.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel47.setText("USD");

        jLabel48.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel48.setText("EUR");

        jLabel49.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel49.setText("RUB");

        jTextField42.setEditable(false);
        jTextField42.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField42.setText("28.035");
        jTextField42.setFocusable(false);

        jTextField43.setEditable(false);
        jTextField43.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField43.setText("28.035");
        jTextField43.setFocusable(false);

        jTextField44.setEditable(false);
        jTextField44.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField44.setText("28.035");
        jTextField44.setFocusable(false);

        jTextField45.setEditable(false);
        jTextField45.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField45.setText("28.035");
        jTextField45.setFocusable(false);

        jTextField46.setEditable(false);
        jTextField46.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField46.setText("28.035");
        jTextField46.setFocusable(false);

        jTextField47.setEditable(false);
        jTextField47.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField47.setText("28.035");
        jTextField47.setFocusable(false);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("В наличии");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("UKR");

        jTextField48.setEditable(false);
        jTextField48.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jLabel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jLabel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .add(9, 9, 9)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel8Layout.createSequentialGroup()
                                        .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .add(jPanel8Layout.createSequentialGroup()
                                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextField15)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextField13)
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jTextField14)
                                            .add(jPanel8Layout.createSequentialGroup()
                                                .add(0, 0, Short.MAX_VALUE)
                                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                                    .add(jTextField43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(jTextField42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(jTextField44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                        .add(18, 18, Short.MAX_VALUE))))
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(jLabel51)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(30, 30, 30))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField16)
                    .add(jTextField18)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField17)
                    .add(jTextField45)
                    .add(jTextField46)
                    .add(jTextField47)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jLabel50)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jTextField48))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jSeparator1)
                        .addContainerGap())
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel13)
                            .add(jLabel14))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(21, 21, 21)
                                .add(jTextField17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(21, 21, 21)
                                .add(jTextField18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(21, 21, 21)
                                .add(jLabel46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabel47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(30, 30, 30)
                                .add(jLabel50)
                                .add(24, 24, 24)
                                .add(jTextField45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jTextField46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(21, 21, 21)
                                .add(jTextField47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 96, Short.MAX_VALUE)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel8Layout.createSequentialGroup()
                                .add(jLabel51)
                                .add(66, 66, 66))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel8Layout.createSequentialGroup()
                                .add(jTextField48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(63, 63, 63))))))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Операции", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(51, 51, 51))); // NOI18N

        jButton1.setText("Купить");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });

        jButton2.setText("Продать");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setText("UKR");

        jButton6.setText("Пополнение");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });

        jButton7.setText("Инкасация");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField3)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(55, 55, 55)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField6))
                .add(18, 18, 18)
                .add(jButton6)
                .add(18, 18, 18)
                .add(jButton7)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .add(jTextField6))
                .add(18, 18, 18)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton6)
                    .add(jButton7))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Кассиры", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(484, 675));

        jTable4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {"Фамилия Имя Отчество", "380934030891"},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ф.И.О.", "Номер"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(1).setMinWidth(100);
            jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable4.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Инкассаторы", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(484, 675));

        jTable5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"фывфывфыв", "380934030891"},
                {"Фамилия", "380934030891"},
                {"Имя ", "380934030891"},
                {"Отчество", "380934030891"}
            },
            new String [] {
                "Ф.И.О.", "Номер"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(1).setMinWidth(100);
            jTable5.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable5.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setPreferredSize(new java.awt.Dimension(391, 675));

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane6.setViewportView(jList1);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Тип документа:");

        jLabel45.setText("Перечень документов:");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel2.setText("Дата от");

        jLabel3.setText("Дата до");

        jLabel4.setText("-");

        org.jdesktop.layout.GroupLayout jPanel16Layout = new org.jdesktop.layout.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane6)
                    .add(jComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel16Layout.createSequentialGroup()
                        .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel45, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel16Layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jFormattedTextField2))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel16Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jLabel44)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
                .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel45)
                .add(18, 18, 18)
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 519, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jOptionPane1.setMessage("Предварительный просмотр");
        jOptionPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jOptionPane1.setMaximumSize(new java.awt.Dimension(0, 0));
        jOptionPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jOptionPane1.setPreferredSize(new java.awt.Dimension(20, 90));

        org.jdesktop.layout.GroupLayout jPanel17Layout = new org.jdesktop.layout.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .add(jOptionPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel17Layout.createSequentialGroup()
                .add(jOptionPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Журнал операций", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(484, 675));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"25221 ", "фывфывфыв", "фывфыв", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Дата", "Время", "Операция", "Кассир"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setMaxWidth(200);
            jTable6.getColumnModel().getColumn(1).setMaxWidth(200);
            jTable6.getColumnModel().getColumn(2).setMaxWidth(800);
            jTable6.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane7)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Данные о предприятии"));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Название предприятия");

        jLabel19.setText("Полное:");

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setText("ТОВ \"ФІНАНСОВА АКАДЕМІЯ АБСОЛЮТ ФІНАНС\"");

        jLabel20.setText("Короткое:");

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setText("ТОВ \"ФК АБСОЛЮТ ФІНАНС\"");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Адрес");

        jLabel22.setText("Физический:");

        jLabel23.setText("Юридический:");

        jLabel24.setText("Индекс");

        jLabel25.setText("Город");

        jLabel26.setText("Улица");

        jLabel27.setText("Дом");

        jLabel28.setText("Офис");

        jTextField21.setText("1");

        jTextField22.setText("1");

        jTextField23.setText("1");

        jTextField24.setText("1");

        jTextField25.setText("1");

        jTextField26.setText("1");

        jTextField27.setText("1");

        jTextField28.setText("1");

        jTextField29.setText("1");

        jTextField30.setText("1");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("Реквизиты");

        jLabel30.setText("МФО:");

        jLabel31.setText("ОКПО:");

        jLabel32.setText("Расчетный счет:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("Главный административный состав предприятия");

        jLabel34.setText("ДИРЕКТОР ПРЕДПРИЯТИЯ:");

        jLabel35.setText("ГЛАВНЫЙ БУХГАЛТЕР ПРЕДПРИЯТИЯ:");

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Фамилия");

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Имя");

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Отчество");

        jTextField34.setText(" ");

        jTextField35.setText(" ");

        jTextField36.setText(" ");

        jLabel39.setText("Ф.И.О.");

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Ф.И.О");

        jTextField38.setText(" ");

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Фамилия");

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Имя");

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Отчество");

        jTextField39.setText(" ");

        jTextField40.setText(" ");

        jTextField41.setText(" ");

        org.jdesktop.layout.GroupLayout jPanel15Layout = new org.jdesktop.layout.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator4)
                    .add(jSeparator2)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(84, 84, 84)
                        .add(jTextField19)
                        .add(18, 18, 18)
                        .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(jTextField20))
                    .add(jSeparator3)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jLabel21)
                            .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(84, 84, 84)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel24)
                            .add(jTextField21)
                            .add(jTextField26))
                        .add(39, 39, 39)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel25)
                            .add(jTextField22)
                            .add(jTextField27))
                        .add(39, 39, 39)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel26)
                            .add(jTextField23)
                            .add(jTextField28))
                        .add(38, 38, 38)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel27)
                            .add(jTextField24)
                            .add(jTextField29))
                        .add(38, 38, 38)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextField30)
                            .add(jTextField25)
                            .add(jLabel28)))
                    .add(jSeparator5)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel18)
                            .add(jLabel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel15Layout.createSequentialGroup()
                                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(jLabel30, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabel31, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                                    .add(jLabel32))
                                .add(84, 84, 84)
                                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jTextField31, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .add(jTextField32)
                                    .add(jTextField33)))
                            .add(jLabel33)
                            .add(jPanel15Layout.createSequentialGroup()
                                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel38, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel34, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel36, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel37, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jLabel39))
                                .add(39, 39, 39)
                                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jPanel15Layout.createSequentialGroup()
                                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(jTextField34, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .add(jTextField35)
                                            .add(jTextField36)
                                            .add(jTextField37))
                                        .add(237, 237, 237)
                                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                    .add(jLabel35))
                                .add(33, 33, 33)
                                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jTextField38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jTextField41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(0, 301, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel21)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel24)
                        .add(jLabel25)
                        .add(jLabel26)
                        .add(jLabel27)
                        .add(jLabel28)))
                .add(18, 18, 18)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(jLabel29)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jLabel31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jTextField32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel32)
                    .add(jTextField33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel33)
                .add(18, 18, 18)
                .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel34)
                    .add(jLabel35))
                .add(18, 18, 18)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel39)
                    .add(jTextField37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel40)
                    .add(jTextField38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(13, 13, 13)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel36)
                    .add(jTextField34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel41)
                    .add(jTextField39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel37)
                    .add(jTextField35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel42)
                    .add(jTextField40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel38)
                    .add(jTextField36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel43)
                    .add(jTextField41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
            .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                            .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)))
                    .add(jPanel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 60, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 681, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel17, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar2.setPreferredSize(new java.awt.Dimension(56, 31));

        jMenu3.setText("Файл");

        jMenuItem1.setText("Выйти");
        jMenuItem1.setMargin(new java.awt.Insets(0, -10, 0, 30));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem8.setText("Настройки");
        jMenuItem8.setMargin(new java.awt.Insets(0, -10, 0, 30));
        jMenuItem8.setPreferredSize(new java.awt.Dimension(99, 22));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar2.add(jMenu3);

        jMenu1.setText("Касса");

        jMenuItem2.setText("Общее состояние");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Подкрепления");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Инкасация");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Обмен валют");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        jMenu4.setText("Справочники");

        jMenuItem5.setText("Кассиры");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Инкассаторы");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("О предприятии");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuBar2.add(jMenu4);

        jMenu5.setText("Журнал операций");
        jMenu5.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu5MenuSelected(evt);
            }
        });
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu5);

        jMenu6.setText("Отчеты");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu6);

        setJMenuBar(jMenuBar2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        HideEl();
        jPanel11.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        HideEl();
        jPanel9.setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        HideEl();
        jPanel10.setVisible(true);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        HideEl();
        jPanel6.setVisible(true);
        jPanel7.setVisible(true);
        jPanel8.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        HideEl();
        jPanel12.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
      /*  try {
            //пкункт меню инкассаторы
          Map mapData = new LinkedHashMap<String,String>();
     
         
           mapData=  ReadSQLiteMulti("SELECT cash_id,surname||\" \"||first_name||\" \"||last_name FROM user where super=\"true\" ;");
           
         Set<String> keys = mapData.keySet();
      
	for (String key : keys) {
              DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
	      model.addRow(new Object[]{ mapData.get(key)});  
	}
    
                    
                   
               
            
            
            
            
            
            
            
            
            
            HideEl();
            jPanel13.setVisible(true); 
       } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }  */
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        HideEl();
        jPanel15.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu5MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu5MenuSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MenuSelected

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        HideEl();
        jPanel14.setVisible(true);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
       //Отчеты 
        HideEl();
        jPanel16.setVisible(true);
        jPanel17.setVisible(true);
        StorageMemory SD = getInstance();
        
        try {
            ArrayList order = new ArrayList();
            SD.PrintTpl= new LinkedHashMap <String,String>();
            SD.PrintTpl=ReadSQLiteMulti("SELECT pattern_id||\" \"||name,html FROM print group by pattern_id ORDER BY pattern_id LIMIT 18 ;");
            //WHERE order_id="+order.get(i)+"
            Set<String> keys = SD.PrintTpl.keySet();
            
           
	
        
        
        
	for (String key : keys) 
        {
	 //   System.out.println("key of balance "+key+ " balance "+ balance.get(key));
            jComboBox1.addItem(key);
        }
        
        
        
            // добавление елементов в лист на форме
       /*     DefaultListModel listModel = new DefaultListModel();
                
               ArrayList tmp = new ArrayList();
               tmp.add("order_id");
               String date1 = jFormattedTextField1.getText();
               String date2 = jFormattedTextField1.getText();
               order= ReadSQLite(tmp,"currencies","Where DATE(TimetoStart) BETWEEN DATE(\""+date1+"\") AND DATE(\""+date2+"\") GROUP BY order_id;");
               for(int i=0;i<order.size();i++)
               {
                   listModel.addElement(order.get(i));
                   
               }
            jList1.setModel(listModel);
        */
        
        
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
//openday
        StorageMemory SD = getInstance();
        try {
            SD.setAction_name("openday");

            ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));

// TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        StorageMemory SD = getInstance();
        try {
            SD.setAction_name("closeday");

            ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));
        } catch (IOException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Setting setup;
        try {
            setup = new Setting();
            setup.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        StorageMemory SD = getInstance();
       try{
            double bal = Double.parseDouble(GetDoubleStr((ArrayList) SD.balance.get("980")));
            if ((Double.parseDouble(jTextField3.getText()) + bal) > 0) 
            {  //проверка на отрицательный баланс

                double sum;
                if (inpf) {
                    sum = pf;
                } else {
                    sum = 0;
                }
                sum += Double.parseDouble(jTextField3.getText());
                SD.OperationX(
                        jComboBox2.getSelectedIndex(),
                        Resident(),
                        jTextField7.getText(),
                        jTextField8.getText(),
                        jTextField9.getText(),
                        sum,
                        Double.parseDouble(jTextField6.getText()),
                        "buy",
                        jTextField10.getText(),
                        Integer.parseInt(jTextField11.getText()),
                        jTextField12.getText()
                );

                try {
                    ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));
                } catch (IOException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                showMessageDialog(null, "Недостаточно средств в кассе");
            }
         }
        catch(NumberFormatException e)
                {
                showMessageDialog(null, "Проверьте введены ли все поля корректно.");
                jTextField6.setText("");
                jTextField3.setText("");
                }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        pf = 0;
        inpf = false;
        if (!jTextField6.getText().equals("")) {

            StorageMemory SD = getInstance();

            String code = (String) SD.TempForSelectDropdown.get(jComboBox2.getSelectedIndex());

            ArrayList tmpz = new ArrayList();
            tmpz = (ArrayList) SD.curse.get(code);
            if (SD.Pfbuy != 0) {
                inpf = true;
                pf = round((Double.parseDouble((String) tmpz.get(0))) * Double.parseDouble((String) jTextField6.getText()) / 100 * SD.Pfbuy, 2);
                jTextField3.setText((Double.toString(round((Double.parseDouble((String) tmpz.get(0))) * Double.parseDouble((String) jTextField6.getText()) / 100 * SD.Pfbuy + ((Double.parseDouble((String) tmpz.get(0))) * Double.parseDouble((String) jTextField6.getText())), 2))));
            } else {
                jTextField3.setText((Double.toString(round(Double.parseDouble((String) tmpz.get(0)) * Double.parseDouble((String) jTextField6.getText()), 2))));
            }
            double tmp = Double.parseDouble((String) jTextField3.getText());
            double tmp2 = Double.parseDouble((String) jTextField6.getText());
            if (tmp > 0) {
                jTextField3.setText(Double.toString(round(tmp * (-1), 2)));
            }
            if (tmp2 < 0) {
                jTextField6.setText(Double.toString(round(tmp2 * (-1), 2)));
            }

        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        pf = 0;
        inpf = false;
        if (!jTextField6.getText().equals("")) {

            inpf = false;
            StorageMemory SD = getInstance();

            String code = (String) SD.TempForSelectDropdown.get(jComboBox2.getSelectedIndex());

            ArrayList tmpz = new ArrayList();
            tmpz = (ArrayList) SD.curse.get(code);
            if (SD.Pfsell != 0) {
                inpf = true;
                pf = round((Double.parseDouble((String) tmpz.get(1))) * Double.parseDouble((String) jTextField6.getText()) / 100 * SD.Pfsell, 2);
                jTextField3.setText((Double.toString(round((Double.parseDouble((String) tmpz.get(1))) * Double.parseDouble((String) jTextField6.getText()) / 100 * SD.Pfsell + ((Double.parseDouble((String) tmpz.get(1))) * Double.parseDouble((String) jTextField6.getText())), 2))));
            } else {
                jTextField3.setText((Double.toString(round(Double.parseDouble((String) tmpz.get(1)) * Double.parseDouble((String) jTextField6.getText()), 2))));
            }

            double tmp = Double.parseDouble((String) jTextField6.getText());
            if (tmp > 0) {
                jTextField6.setText(Double.toString(round(tmp * (-1), 2)));
            }
            double tmp2 = Double.parseDouble((String) jTextField3.getText());
            if (tmp2 < 0) {
                jTextField3.setText(Double.toString(round(tmp2 * (-1), 2)));
            }
        }


    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        StorageMemory SD = getInstance();

      try{
            String code = (String) SD.TempForSelectDropdown.get(jComboBox2.getSelectedIndex());
            double bal = Double.parseDouble(GetDoubleStr((ArrayList) SD.balance.get(code)));
            if ((Double.parseDouble(jTextField6.getText()) + bal) > 0) {
showMessageDialog(null, (Double.parseDouble(jTextField6.getText()) + bal));
                double sum;
                if (inpf) {
                    sum = pf;
                } else {
                    sum = 0;
                }
                if (pf != 0) {
                    sum = Double.parseDouble(jTextField3.getText()) - pf;
                } else {
                    sum = Double.parseDouble(jTextField3.getText());

                }
                sum = round(sum, 2);

                
                
                
                
                
                SD.OperationX(
                        jComboBox2.getSelectedIndex(),
                        Resident(),
                        jTextField7.getText(),
                        jTextField8.getText(),
                        jTextField9.getText(),
                        sum,
                        Double.parseDouble(jTextField6.getText()),
                        "sale",
                        jTextField10.getText(),
                        Integer.parseInt(jTextField11.getText()),
                        jTextField12.getText()
                );

                try {
                    ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));
                } catch (IOException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                }
                    } else {
                        showMessageDialog(null, "Недостаточно денег в кассе");
                    }
      }
        catch(NumberFormatException e)
                {
                showMessageDialog(null, "Проверьте введены ли все поля корректно.");
                jTextField6.setText("");
                jTextField3.setText("");
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed

        /*   
         */
// TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (!isNumeric(jTextField3.getText())) {
            showMessageDialog(null, "Воодить можно только числа.");
            jTextField3.setText("");

        }// TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
//replanish -popolnenie
        StorageMemory SD = getInstance();
//валюты
        String code = (String) SD.TempForSelectDropdown.get(jComboBox2.getSelectedIndex());
        double bal = Double.parseDouble(GetDoubleStr((ArrayList)SD.balance.get(code)));
        if (bal-(Double.parseDouble(jTextField6.getText()) ) > 0) {
            System.out.println("Пополнение");
           

                SD.OperationX(
                        jComboBox2.getSelectedIndex(),
                        Resident(),
                        jTextField7.getText(),
                        jTextField8.getText(),
                        jTextField9.getText(),
                        0,
                        Double.parseDouble(jTextField6.getText()),
                        "replenish",
                        jTextField10.getText(),
                        Integer.parseInt(jTextField11.getText()),
                        jTextField12.getText()
                );

                try {
                    ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));
                } catch (IOException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                }

            

        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        StorageMemory SD = getInstance();
//валюты
        String code = (String) SD.TempForSelectDropdown.get(jComboBox2.getSelectedIndex());
        double bal = Double.parseDouble(GetDoubleStr((ArrayList)SD.balance.get(code)));
        if (bal-(Double.parseDouble(jTextField6.getText()) ) > 0) {
         System.out.println("инкасация");
                SD.OperationX(
                        jComboBox2.getSelectedIndex(),
                        Resident(),
                        jTextField7.getText(),
                        jTextField8.getText(),
                        jTextField9.getText(),
                        0,
                        Double.parseDouble(jTextField6.getText()),
                        "collection",
                        jTextField10.getText(),
                        Integer.parseInt(jTextField11.getText()),
                        jTextField12.getText()
                );

                try {
                    ParseJson pjs = new ParseJson(SendPost(SD.GetSD()));
                } catch (IOException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
//наведение на пополнение
if(!jTextField6.getText().equals(""))
{
      double tmp = Double.parseDouble((String) jTextField6.getText());
            if (tmp < 0) {
                jTextField6.setText(Double.toString(round(tmp * (-1), 2)));
            }  
}
// TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
if(!jTextField6.getText().equals("")){
        double tmp = Double.parseDouble((String) jTextField6.getText());
            if (tmp > 0) {
                jTextField6.setText(Double.toString(round(tmp * (-1), 2)));
            }        // TODO add your handling code here:
}
    }//GEN-LAST:event_jButton7MouseEntered

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Login_Form frm = new Login_Form();
            StorageMemory SD = getInstance();
            SD=null;
            In.this.setVisible(false);
            frm.setVisible(true);
            // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
       try{
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        jComboBox2.setSelectedIndex((jComboBox2.getSelectedIndex()+1));
        jComboBox2.setSelectedIndex((jComboBox2.getSelectedIndex()-1));

       }
       catch(Exception e)
       {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
       }
        

// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     */
    public void HideEl() {
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jPanel8.setVisible(false);
        jPanel9.setVisible(false);
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        jPanel12.setVisible(false);
        jPanel13.setVisible(false);
        jPanel14.setVisible(false);
        jPanel15.setVisible(false);
        jPanel16.setVisible(false);
        jPanel17.setVisible(false);
    }
public  void RefreshINF()
{
    StorageMemory obj=getInstance();
 ArrayList tmpz = new ArrayList();
            tmpz=(ArrayList) obj.curse.get("840");
                
            jTextField13.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(0)),2)));
            jTextField16.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(1)),2)));
            jTextField42.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(2)),2)));
            
            //Euro
 
            tmpz = new ArrayList();
            tmpz=(ArrayList) obj.curse.get("978");
                
            jTextField14.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(0)),2)));
            jTextField17.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(1)),2)));
            jTextField43.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(2)),2)));
            
             //Ru
 
            tmpz = new ArrayList();
            tmpz=(ArrayList) obj.curse.get("643");
                
            jTextField15.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(0)),2)));
            jTextField18.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(1)),2)));
            jTextField44.setText(Double.toString(round(Double.parseDouble((String)tmpz.get(2)),2)));
            
            
                    jTextField45.setText(Double.toString(Double.parseDouble(GetDoubleStr((ArrayList)obj.balance.get("840")))));
                    jTextField46.setText(Double.toString(Double.parseDouble(GetDoubleStr((ArrayList)obj.balance.get("978")))));
                    jTextField47.setText(Double.toString(Double.parseDouble(GetDoubleStr((ArrayList)obj.balance.get("643")))));
                    jTextField48.setText(Double.toString(Double.parseDouble(GetDoubleStr((ArrayList)obj.balance.get("980")))));

}
    /**
     *
     * @param args
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
            java.util.logging.Logger.getLogger(In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        UIDefaults def = UIManager.getLookAndFeelDefaults();
        def.put("TabbedPane.foreground", Color.RED);
        def.put("TabbedPane.textIconGap", new Integer(16));
        def.put("TabbedPane.background", Color.BLUE);
        def.put("TabbedPane.tabInsets", new Insets(10, 10, 10, 10));
        def.put("TabbedPane.selectedTabPadInsets", new Insets(10, 20, 10, 20));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new In().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public JComboBox getjComboBox1() {
        return jComboBox1;
    }

    public JList getjList1() {
        return jList1;
    }

    public void setjOptionPane1(JOptionPane jOptionPane1) {
        this.jOptionPane1 = jOptionPane1;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JFormattedTextField jTextField10;
    private javax.swing.JFormattedTextField jTextField11;
    private javax.swing.JFormattedTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JFormattedTextField jTextField7;
    private javax.swing.JFormattedTextField jTextField8;
    private javax.swing.JFormattedTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    private class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                StorageMemory SD= getInstance();
                String htm="";
             
                
                
                //при нажатии
                int select = jComboBox1.getSelectedIndex()+1;
                showMessageDialog(null, "ПИК "+select);
                //заглушки выбора пунтиков меню
                
                switch(select){
                    
                    //отчет по курсам
                    case 1:{
                        //  htm="";
                        showMessageDialog(null, "Зашел в Case 1");
                        DefaultListModel listModel = new DefaultListModel();
                        ArrayList order = new ArrayList();
                        ArrayList tmp = new ArrayList();
                        tmp.add("order_id||\"-й Приказ (\"||TimetoStart||\")\"");
                        String date1 = jFormattedTextField1.getText();
                        String date2 = jFormattedTextField2.getText();
                        order = ReadSQLite(tmp,"currencies","Where DATE(TimetoStart) BETWEEN DATE(\""+date1+"\") AND DATE(\""+date2+"\") GROUP BY order_id ORDER BY `currencies_id`  DESC LIMIT 23   ;");
                        for(int i=0;i<order.size();i++)
                        {
                            listModel.addElement(order.get(i));
                        }
                        jList1.setModel(listModel);
                        
                        
                        /*    RenderPDF_img_too(htm);
                        BufferedImage img = new BufferedImage(1,1,1);
                        img = ScaleImage(474, 672, "name_img.png");
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel(icon);
                        JOptionPane.showMessageDialog(null, label);
                        jOptionPane1.add(label);
                        
                        */
                        
                        break;
                    }
                    case 2: showMessageDialog(null, "Зашел в Case 2");
                    DefaultListModel listModel = new DefaultListModel();
                    ArrayList order = new ArrayList();
                    ArrayList tmp = new ArrayList();
                    tmp.add("order_id||\"-й Приказ [3 валюты] (\"||TimetoStart||\")\"");
                    String date1 = jFormattedTextField1.getText();
                    String date2 = jFormattedTextField2.getText();
                    order = ReadSQLite(tmp,"currencies","Where DATE(TimetoStart) BETWEEN DATE(\""+date1+"\") AND DATE(\""+date2+"\") GROUP BY order_id ORDER BY `currencies_id`  DESC LIMIT 23   ;");
                    for(int i=0;i<order.size();i++)
                    {
                        listModel.addElement(order.get(i));
                    }
                    jList1.setModel(listModel);
                    break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    {
                        //htm="";
                        showMessageDialog(null, "Зашел в Case = "+select);
                        
                        DefaultListModel listMod = new DefaultListModel();
                        listMod.addElement("Пустой шаблон (2015-10-03)");
                        jList1.setModel(listMod);
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        break;
                        
                        
                        
                    }
                    
                    
                    
                    
                    
                    
                    default:
                        showMessageDialog(null, "Несуществует документа!");
                        break;
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(In.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
public String Resident()
{
if(jCheckBox1.isSelected())
{
return "1";
}
else return "0";
}
 
 
}
