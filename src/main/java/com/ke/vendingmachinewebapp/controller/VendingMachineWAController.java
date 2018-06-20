/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.vendingmachinewebapp.controller;

import com.ke.vendingmachinewebapp.dao.VendingMachineWAPersistenceException;
import com.ke.vendingmachinewebapp.model.Item;
import com.ke.vendingmachinewebapp.model.Money;
import com.ke.vendingmachinewebapp.service.VendingMachineWAService;
import com.ke.vendingmachinewebapp.service.WrongDollarAmountException;
//import com.ke.vendingmachinewebapp.service.WrongItemIDException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Owner
 */
@Controller

public class VendingMachineWAController {

    @Inject
    VendingMachineWAService service;
    Money userM = new Money();
//    Item item ;
    String itemId;
    String message;
    String changee;
    int stock;

    public VendingMachineWAController(VendingMachineWAService service) {
//        this.item = item;
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String item(Model model) throws FileNotFoundException, VendingMachineWAPersistenceException {

        model.addAttribute("items", service.getAllItems());
        model.addAttribute("userMoney", userM.getUserMoney());
        model.addAttribute("userPickId", itemId);
        model.addAttribute("purchaseAttemptMessage", message);
        model.addAttribute("item.itemStock", stock);
        model.addAttribute("change", changee);

        return "index";
    }

    @RequestMapping(value = "/addUserMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request) {
        String money = request.getParameter("userMoneyy");
        String addedMoney = request.getParameter("add-userMoney");
        double n = Double.parseDouble(money);
        if (addedMoney.isEmpty()) {
            userM.setUserMoney(new BigDecimal(n).setScale(2, RoundingMode.HALF_UP));
        } else {
            double m = Double.parseDouble(addedMoney);

            BigDecimal o = BigDecimal.valueOf(m);
            BigDecimal p = BigDecimal.valueOf(n);
            BigDecimal testt = o.add(p);

            userM.setUserMoney(testt.setScale(2, RoundingMode.HALF_UP));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/pickItem", method = RequestMethod.POST)
    public String getItemId(HttpServletRequest request) throws VendingMachineWAPersistenceException {

        String value = request.getParameter("testid");
        itemId = value;

        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request) throws VendingMachineWAPersistenceException {

        Item item = service.getItem(itemId);
        if (item == null) {
            message = "Please select an item to purchase";
        } else if (item.getItemStock() <= 0) {
            message = "We're out of stock for this item, please select another one.";
        } else {
            if (userM.getUserMoney().compareTo(item.getItemPrice()) >= 0) {
                message = "Successful Purchase";
                service.sellItem(item);

            } else {
                BigDecimal change = item.getItemPrice().subtract(userM.getUserMoney());
                message = "Insufficient funds. Please add " + change + " to purchase this item.";
            }
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.POST)
    public String getChange(HttpServletRequest request) throws VendingMachineWAPersistenceException, WrongDollarAmountException {

        Item item = service.getItem(itemId);
        if (item == null) {
            changee = "No item purchased. You're getting " + userM.getUserMoney() + " back";
        } else if (userM.getUserMoney().compareTo(item.getItemPrice()) >= 0) {
            BigDecimal[] changeA = service.getChange(userM.getUserMoney(), item.getItemPrice());
            changee = "You'll be getting " + changeA[0] + " quarters, " + changeA[1] + " dimes, " + changeA[2] + " nickels, " + changeA[3] + " pennies.";
            userM.setUserMoney(BigDecimal.ZERO);
        } else {
            changee = "No change for you!";
        }
        return "redirect:/";

    }
}
