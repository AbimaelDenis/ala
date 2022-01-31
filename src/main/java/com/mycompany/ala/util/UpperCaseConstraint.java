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
public class UpperCaseConstraint extends PlainDocument {

    private int maxLength = 0;

    public UpperCaseConstraint(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet attr) throws BadLocationException {

        String oldValue = getText(0, getLength());
        int newLength = oldValue.length() + str.length();
        if (newLength <= maxLength) {
            super.insertString(offs, str.replaceAll("[^A-Za-z0-9-/\\s]", "").toUpperCase(), attr);
        }
    }

}
