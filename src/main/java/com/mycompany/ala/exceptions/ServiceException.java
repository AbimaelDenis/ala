/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.exceptions;

/**
 *
 * @author Abimael
 */
public class ServiceException extends RuntimeException{
    
   public ServiceException(String msg){
       super(msg);
   }
}
