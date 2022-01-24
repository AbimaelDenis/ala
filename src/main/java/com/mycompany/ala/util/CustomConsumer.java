/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ala.util;

import java.util.function.Consumer;

/**
 *
 * @author Abimael
 */
public interface CustomConsumer<T, R>{   
    void accept(T t, R r);
}
