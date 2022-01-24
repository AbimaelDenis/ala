/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.util;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Abimael
 */
public class JSpinnerListener {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void setDateChangeListener(JSpinner spn) {
        spn.addChangeListener(createDateChangeListener(spn));
    }
    
    public static void setFixedLength(JTextField txt, int fixedLength){
        txt.addKeyListener(createFixedLengthListener(txt, fixedLength));
    }
        
    //Listeners Request
    private static ChangeListener createDateChangeListener(JSpinner spn) {
        return createChangeListener(spn, (spinner) -> {
            if (((Date) spinner.getValue()).getTime() > new Date().getTime()) {
                spinner.setValue(new Date());
                System.out.println("true");
            } 
        });
    }
    
   
    private static KeyListener createFixedLengthListener(JTextField txt, int fixedLength){
      return createKeyListener(txt, (textField, e) -> {
        if(txt.getText().length() != (fixedLength-1) || txt.getText().length() != (fixedLength+1)){
            if(e.getKeyCode() != 39 && e.getKeyCode() != 37)
                e.consume();               
        }
      });  
    }

    //Listeners Factory
    private static ChangeListener createChangeListener(JSpinner spn, Consumer<JSpinner> con) {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                con.accept(spn);               
            }
        };
    }
         
    private static KeyListener createKeyListener(JTextField txt, CustomConsumer<JTextField, KeyEvent> cCon){
        KeyListener keyListener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
                    cCon.accept(txt, e);
            }
            
            @Override
            public void keyPressed(KeyEvent e){
                    cCon.accept(txt, e);
            }
                
            @Override
            public void keyReleased(KeyEvent e){}
        };
        return keyListener;      
    }  
}
