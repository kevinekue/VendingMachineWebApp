/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.service;

import com.ke.vendingmachinewebapp.dao.VendingMachineWADao;
import com.ke.vendingmachinewebapp.dao.VendingMachineWAPersistenceException;
import com.ke.vendingmachinewebapp.model.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Owner
 */
@Component
public class VendingMachineWAServiceMemImpl implements VendingMachineWAService {

    @Inject
    VendingMachineWADao dao;
    Item item;

    
    public VendingMachineWAServiceMemImpl(VendingMachineWADao dao) {
        this.dao = dao;
    }
    
    public BigDecimal getMoney(BigDecimal userMoney){
        
        return userMoney;
    }

    @Override
    public BigDecimal[] getChange(BigDecimal userMoney, BigDecimal itemPrice) throws WrongDollarAmountException {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");
        BigDecimal change = userMoney.subtract(itemPrice);
        if (change.compareTo(BigDecimal.ZERO) == -1) {
            throw new WrongDollarAmountException("Insufficient funds.");
        } else {

            BigDecimal[] quarters = change.divideAndRemainder(quarter);
            change = quarters[1];
            BigDecimal[] dimes = change.divideAndRemainder(dime);
            change = dimes[1];
            BigDecimal[] nickels = change.divideAndRemainder(nickel);
            change = nickels[1];
            BigDecimal[] pennies = change.divideAndRemainder(penny);
            BigDecimal[] changeArray = {quarters[0], dimes[0], nickels[0], pennies[0]};
            return changeArray;
        }
    }

    @Override
    public void makePurchaseCheck(Item item) throws WrongItemIDException, VendingMachineWAPersistenceException {
        if (dao.getItemInfo(item.getItemID()) == null) {
            throw new WrongItemIDException("Error: Wrong item ID. ItemID " + item.getItemID() + " isn't recognized.");
        }

    }

    @Override
    public Item getItem(String itemChoice) throws VendingMachineWAPersistenceException {
        return dao.getItemInfo(itemChoice);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineWAPersistenceException {
        return dao.getAllItems();
    }

    @Override
    public boolean itemStockCheck(Item item) throws VendingMachineWAPersistenceException {

        int stock = item.getItemStock();

        if (stock <= 0) {

            throw new VendingMachineWAPersistenceException("We're out of stock for this item, please enter another item ID. ");

        } else {
            return true;
        }
    }

    @Override
    public void sellItem(Item item) {

        try {
            dao.updateInventory(item.getItemID());
        } catch (VendingMachineWAPersistenceException | FileNotFoundException ex) {
            try {
                dao.getAllItems();
            } catch (VendingMachineWAPersistenceException ex1) {
            }
        }

    }

}
