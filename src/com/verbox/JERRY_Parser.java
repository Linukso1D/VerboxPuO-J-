/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

import static com.verbox.StorageMemory.getInstance;

/**
 *
 * @author maxxl
 * служит в качестве HTML шаблонизатора
 */
public class JERRY_Parser {
private String text;
    public JERRY_Parser(String text) {
    text=this.text;    
    }
    //обработка
    public void Refresh()
    {
            
            
    }
    public String Search()
    {
    StorageMemory SD =getInstance();
    int x,y; 
    x=text.indexOf("{")+1 ;
    y= text.indexOf("}");
    String centerTextItem = text.substring(x,y);
    return centerTextItem;
    }
    
    
    
    
    
    
    //чистка 
    public void ClearText()
    {
    text="";
    }
    //получение
    public String GetText()
    {
        return text;
    }
}
