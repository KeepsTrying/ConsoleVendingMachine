/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.ui;

import com.sguild.vendingmachine.vendingmachinedto.ChangePurse;
import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayAvailableOptions(List<Merch> inventory) {
        System.out.println("Slot    Item        Price");
        System.out.println("-------------------------");
        for (Merch thisMerch : inventory) {
            if (thisMerch.getStockCount() > 0) {
                io.print(thisMerch.getSlotNum() + ")    " + thisMerch.getItemName() + "    " + thisMerch.getItemCost());
            } else {
                io.print(thisMerch.getSlotNum() + ")  " + thisMerch.getItemName() + " is out of stock.");
            }
        }
        io.print(" ");
    }

    public boolean displayExitOption() {
        boolean makePurchase = true;
        String userInput = io.readString("Would you like to make a purchase?  Please type Yes or No");
        if (userInput.equalsIgnoreCase("yes")) {
            makePurchase = true;
        } else {
            makePurchase = false;
        }
        return makePurchase;
    }

    public void displayExitMsg() {
        io.print("Watching your weight, eh?  I don't blame ya....");
    }

    public BigDecimal requestBalanceAddition() {
        boolean isValid = false;
        BigDecimal balanceAddition;
        
        do {
        balanceAddition = io.readBigD("How much would you like to add to your balance?");
        if (balanceAddition.compareTo(ZERO) < 0) {
            io.print("Auto-Magic Vending Machines only take positive forms of currency.");
            io.print(" ");
            isValid = false;
        } else {
            isValid = true;
        }
        } while (!isValid);
        return balanceAddition;
    }

    public void printCurrentBalance(String prompt) {
        io.print(prompt);
    }

    public int requestPurchase(int max) {
        int desire = io.readInt("What slot number would you like to purchase from?", 1, max);
        return desire;
    }

    public boolean displayNoStockMenu() {
        System.out.println("Item is out of stock.");
        System.out.println("1) Select another item.");
        System.out.println("2) Take the money and run.");
        boolean willContinue = io.readIntBoolean("What would you like to do?");
        return willContinue;
    }

    public boolean displayInsuffFunds(Merch desire, BigDecimal difference) {
        io.print("Insufficient Funds. Please add $" + difference + " to purchase " + desire.getItemName() + " or select another item.");
        boolean response = io.readIntBoolean("Would you like to:    1) Continue Shopping    2) Take the money and run?");
        return response;
    }

    public void pleaseEnjoy(Merch desire) {
        io.print("Thank you for selecting Auto-Magic Vending!");
        io.print("Please enjoy your " + desire.getItemName() + "!");
        io.print(" ");
    }
    
    public void dispenseChange(ChangePurse usersChange) {
        System.out.println("**You hear the ringing of change from the change dispenser**");
        System.out.println("** You pull out your change: **");
        viewChange(usersChange.getDollars(), " Dollars");
        viewChange(usersChange.getQuarters(), " Quarters");
        viewChange(usersChange.getDimes(), " Dimes");
        viewChange(usersChange.getNickles(), " Nickles");
        viewChange(usersChange.getPennies(), " Pennies");
    }
    
    public void viewChange(int quantity, String identifier) {
        io.print(quantity + " " + identifier);
    }

}
