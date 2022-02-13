/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.application;


import com.mycompany.ala.dao.DaoFactory;
import com.mycompany.ala.dao.MaterialDao;
import com.mycompany.ala.entities.Material;
import com.mycompany.ala.gui.OrderServiceListView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Abimael
 */
public class Program {
    public static void main(String[] args){
        OrderServiceListView oslv = new OrderServiceListView();
        oslv.setTitle("Ordens de Serviço");
        oslv.setVisible(true);  
        
//        MaterialDao dao = DaoFactory.createMaterialDao();
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\LISTA_DE_MATERIAIS_GERAL.txt"), "UTF-8"))){
//            String line = br.readLine();
//            line = br.readLine();
//            String[] field;
//            while(line != null){
//                field = line.split(";");             
//                String code = field[0];
//                String descript = field[1];
//                String unit = field[2];
//                Material material = new Material(code, descript, unit);
//                dao.insert(material);
//                line = br.readLine();
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }
}
