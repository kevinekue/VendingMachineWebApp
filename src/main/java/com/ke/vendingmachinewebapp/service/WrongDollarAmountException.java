/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.service;

/**
 *
 * @author Owner
 */
public class WrongDollarAmountException extends Exception {
    public WrongDollarAmountException (String message){
        super(message);
    }
    
    public WrongDollarAmountException (String message, Throwable cause){
        super(message, cause);
    }
}
