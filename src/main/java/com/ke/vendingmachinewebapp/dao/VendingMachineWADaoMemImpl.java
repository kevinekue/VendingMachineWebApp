/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.dao;

import com.ke.vendingmachinewebapp.model.Item;
import com.ke.vendingmachinewebapp.model.Money;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kevin_E
 */

@Component
public class VendingMachineWADaoMemImpl implements VendingMachineWADao {

//    public static final String INVENTORY_FILE = f;
    public static final String DELIMITER = "::";
    Item item;
    Money money;
    private Map<String, Item> itemsForSale = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
   

    private void loadInventory() throws VendingMachineWAPersistenceException {
        Scanner scanner;
        File f = new File(getClass().getClassLoader().getResource("inventory.txt").getFile());

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(f)));
        } catch (FileNotFoundException ex) {
            throw new VendingMachineWAPersistenceException("-_- Could not load roster data into memory.", ex);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemStock(Integer.parseInt(currentTokens[3]));

            itemsForSale.put(currentItem.getItemID(), currentItem);
        }
        scanner.close();
    }

    private void writeInventory() throws VendingMachineWAPersistenceException, FileNotFoundException, IOException {
        File f = new File(getClass().getClassLoader().getResource("inventory.txt").getFile());

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(f));
        } catch (IOException e) {
            throw new VendingMachineWAPersistenceException("Could not save Stock data.", e);
        }

        List<Item> itemsForSale = this.getAllItems();
        for (Item currentItem : itemsForSale) {
            out.println(currentItem.getItemID() + DELIMITER + currentItem.getItemName() + DELIMITER + currentItem.getItemPrice() + DELIMITER + currentItem.getItemStock());

            out.flush();
        }
        out.close();
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineWAPersistenceException {

        loadInventory();

        return new ArrayList<Item>(itemsForSale.values());
    }

    @Override
    public Item getItemInfo(String itemId) throws VendingMachineWAPersistenceException {

        return itemsForSale.get(itemId);

    }

    @Override
    public int updateInventory(String itemID) throws VendingMachineWAPersistenceException {

        Item item = itemsForSale.get(itemID);
        int updatedStock;
        if (item.getItemStock() > 0) {
            updatedStock = item.getItemStock() - 1;
            item.setItemStock(updatedStock);
        } else {
            updatedStock = 0;
        }

        try {
            writeInventory();
        } catch (IOException | VendingMachineWAPersistenceException ex) {
            throw new VendingMachineWAPersistenceException("couldn't write the updated inventory to the audit log", ex);
        }

        return item.setItemStock(updatedStock);
    }

}
