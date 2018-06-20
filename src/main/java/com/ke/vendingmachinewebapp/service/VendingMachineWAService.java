/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.service;
import com.ke.vendingmachinewebapp.dao.VendingMachineWAPersistenceException;
import com.ke.vendingmachinewebapp.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface VendingMachineWAService {
    

    void makePurchaseCheck (Item item) throws WrongItemIDException, VendingMachineWAPersistenceException;


    BigDecimal [] getChange (BigDecimal userMoney, BigDecimal itemPrice) throws WrongDollarAmountException;
    
    
    Item getItem (String itemChoice) throws VendingMachineWAPersistenceException;
    
    public List<Item> getAllItems () throws VendingMachineWAPersistenceException;
    
    boolean itemStockCheck (Item item)  throws VendingMachineWAPersistenceException;
    
    
    public void sellItem(Item item);
    

}
