/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.dao;

/**
 *
 * @author Owner
 */
public class VendingMachineWAPersistenceException extends Exception {
    private String ex;

    public VendingMachineWAPersistenceException(String message) {
        super(message);
    }
    
    public VendingMachineWAPersistenceException (String message, Throwable cause){
                super(message, cause);
    }
}
