/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Owner
 */
public class Item {

    String itemID;
    String itemName;
    BigDecimal itemPrice;
    int itemStock;
    BigDecimal userM;
    //int updatedStock;

    public BigDecimal getUserM() {
        return userM;
    }

    public void setUserM(BigDecimal userM) {
        this.userM = userM;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public int setItemStock(int itemInventory) {
        this.itemStock = itemInventory;
        return itemStock;
    }

    public Item(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.itemID);
        hash = 79 * hash + Objects.hashCode(this.itemName);
        hash = 79 * hash + Objects.hashCode(this.itemPrice);
        hash = 79 * hash + this.itemStock;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemStock != other.itemStock) {
            return false;
        }
        if (!Objects.equals(this.itemID, other.itemID)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + itemID + " | Name: " + itemName + " | Price: " + itemPrice + " | Stock: " + itemStock;
    }

}
