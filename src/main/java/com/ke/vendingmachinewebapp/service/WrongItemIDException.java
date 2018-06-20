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
class WrongItemIDException extends Exception {
    public WrongItemIDException (String message){
        super(message);
    }
    
    public WrongItemIDException(String  message, Throwable cause) {
        super(message, cause);
    }
}
