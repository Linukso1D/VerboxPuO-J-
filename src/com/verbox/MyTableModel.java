package com.verbox;

import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Запрещает редактирование строк в таблицах
 * 
 * @author maxxl
 */
public class MyTableModel extends AbstractTableModel {

      @Override
      public boolean isCellEditable(int row, int column){  
          return false;  
      }

    @Override
    public int getRowCount() {
          return 0;
       
    }

    @Override
    public int getColumnCount() {
          return 0;
      
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
          return null;

    }

}