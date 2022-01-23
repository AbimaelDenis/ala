/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Abimael
 */
public class DefaultFileChooser {
    public static JFileChooser createFileChooser(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Aquivos de texto (.txt)", "txt");
        
        String baseDirectory = System.getProperty("user.home") + "/Desktop";
        File dir = new File(baseDirectory);
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(dir);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        
        return fileChooser;
    }
}
