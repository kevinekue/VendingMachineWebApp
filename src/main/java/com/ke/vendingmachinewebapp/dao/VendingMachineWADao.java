/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.dao;

import com.ke.vendingmachinewebapp.model.Item;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface VendingMachineWADao {
    
    // Not sure if add needded yet.
    
    List<Item> getAllItems() throws VendingMachineWAPersistenceException;
    
    Item getItemInfo(String itemId) throws VendingMachineWAPersistenceException;
    
    int updateInventory (String itemId) throws FileNotFoundException, VendingMachineWAPersistenceException;
    
}
