/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.util;

import java.util.function.Consumer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Abimael
 */
public class MaxLengthConstraint extends PlainDocument{
    private int maxLength = 0;
   
    public MaxLengthConstraint(int maxLength){
        this.maxLength = maxLength;
    }
             
    @Override
    public void insertString(int offs, String str, AttributeSet attr) throws BadLocationException {

        if (str == null) return;  

        String oldValue = getText(0, getLength());  
        int tamanhoNovo = oldValue.length() + str.length(); 

        if (tamanhoNovo <= maxLength) {  
            super.insertString(offs, str , attr);  
        } else {    
            super.insertString(offs, "", attr); 
        }  
        
    }
    
}
