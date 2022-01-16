/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.application;


import com.mycompany.ala.gui.OrderServiceListView;


/**
 *
 * @author Abimael
 */
public class Program {
    public static void main(String[] args){
        OrderServiceListView oslv = new OrderServiceListView();
        oslv.setTitle("Ordens de Serviço");
        oslv.setVisible(true);  
    }
}
