/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinecontroller;

import com.sguild.vendingmachine.ui.VendingMachineView;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import com.sguild.vendingmachine.vendingmachinedto.Merch;
import com.sguild.vendingmachine.vendingmachineservicelayer.VendingMachineInsufficientFundsException;
import com.sguild.vendingmachine.vendingmachineservicelayer.VendingMachineNoItemInventoryException;
import com.sguild.vendingmachine.vendingmachineservicelayer.VendingMachineServiceLayer;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer jeeves;

    public VendingMachineController(VendingMachineServiceLayer jeeves, VendingMachineView view) {
        this.jeeves = jeeves;
        this.view = view;
    }

    public void run() throws VendingMachineDataTransferException {

        boolean validSelection = false;
        boolean isStocked = false;
        boolean hasFunds = false;
        boolean willContinue = true;
do {
        do {
            try {
                do {

                    displayInventory();

                    willContinue = allowExitOption();

                    displayCurrentBalance("Your current balance is: $");

                    addToBalance();

                    displayCurrentBalance("Your new balance is: $");

                    Merch desire = selectItem();

                    isStocked = validateItemInventory(desire);

                    if (isStocked) {
                        validSelection = isStocked;

                        try {
                            hasFunds = validateSufficientFunds(desire);
                            jeeves.setCurrentBalance(jeeves.getDifference(desire.getItemCost()));
                            int stockCount = desire.getStockCount();
                            desire.setStockCount(stockCount - 1);
                            jeeves.saveInventory();
                            view.pleaseEnjoy(desire);
                            saveAndExit();
                            willContinue = false;
                        } catch (VendingMachineInsufficientFundsException exceptionType) {
                            hasFunds = false;
                            BigDecimal difference = jeeves.getDifference(desire.getItemCost()).negate();
                            willContinue = view.displayInsuffFunds(desire, difference);
                            if (!willContinue) {
                                view.displayExitMsg();
                                saveAndExit();
                            }
                        }
                    }
                } while (!hasFunds);

            } catch (VendingMachineNoItemInventoryException exceptionType) {
                boolean willSelectDiffMerch = false;

                willSelectDiffMerch = view.displayNoStockMenu();
                if (willSelectDiffMerch) {
                    //displayInventory();
                    //displayCurrentBalance("Your current balance is: $");
                    validSelection = false;
                } else {
                    view.displayExitMsg();
                    saveAndExit();
                }
            }
        } while (!validSelection);

} while(willContinue);
        //purchaseItem();
        //dispenseChange();
    }

    public void displayInventory() throws VendingMachineDataTransferException {
        jeeves.loadInventory();
        List<Merch> inventory = jeeves.getAllMerch();
        view.displayAvailableOptions(inventory);
    }

    public boolean allowExitOption() throws VendingMachineDataTransferException {
        boolean desiresPurchase = view.displayExitOption();
        if (!desiresPurchase) {
            System.out.println("Watching your weight, eh?  I don't blame ya....");
            jeeves.saveInventory();
            if (jeeves.getCurrentBalance().compareTo(ZERO) > 0) {
                dispenseChange();
            }  
        }   
        return desiresPurchase;
    }

    public void saveAndExit() throws VendingMachineDataTransferException {
        jeeves.saveInventory();
        if (jeeves.getCurrentBalance().compareTo(ZERO) > 0) {
            dispenseChange();
        }
    }

    public void displayCurrentBalance(String prompt) {
        view.printCurrentBalance(prompt + jeeves.getCurrentBalance());
    }

    public void addToBalance() {
        jeeves.addToCurrentBalance(view.requestBalanceAddition());
    }

    public Merch selectItem() {
        Merch desire = jeeves.getItem(view.requestPurchase(jeeves.getInvSlots()));
        return desire;
    }

    /*
    public BigDecimal purchaseItem() throws VendingMachineInsufficientFundsException, VendingMachineDataTransferException {
        //displayInventory();
        //Merch itemPurchase = jeeves.getItem(view.requestPurchase(jeeves.getInvSlots()));
        if (true) {
           
        if (jeeves.getStockCount(itemPurchase) > 0) {
            if (jeeves.checkBalance(itemPurchase)) {
                jeeves.setCurrentBalance(jeeves.getDifference(itemPurchase.getItemCost()));
                int stockCount = jeeves.getStockCount(itemPurchase);
                itemPurchase.setStockCount(stockCount - 1);
                jeeves.saveInventory();
                System.out.println("Thank you for selecting Auto-Magic Vending!");
                System.out.println("Please enjoy your " + itemPurchase.getItemName() + "!");
            } else {
                throw new VendingMachineInsufficientFundsException("Insufficient Funds. Please add $" + jeeves.getDifference(itemPurchase.getItemCost().negate()) + " or select another item.");
            }
        } else {
            System.out.println("Item " + itemPurchase.getSlotNum() + ", " + itemPurchase.getItemName() + " is Out of Stock");
            System.out.println("    ===    -Please select another item.  ===  ");
            purchaseItem();
             
        }
        return jeeves.getCurrentBalance();
    }
     */
    public void dispenseChange() {
        BigDecimal remainingBalance = jeeves.getCurrentBalance();
        view.dispenseChange(jeeves.calculateChange(jeeves.convertChange(remainingBalance)));
        jeeves.clearChange();
        //jeeves.clearChange();
        //instantiate changePurse in service layer
        //changePurse takes in remaining balance, sets its own fields
    }

    public boolean validateSufficientFunds(Merch desire) throws VendingMachineInsufficientFundsException {
        //if (jeeves.checkBalance(desire).compareTo(0) >= 0) {
        boolean hasFunds = false;
        hasFunds = jeeves.checkBalance(desire);
        return hasFunds;
    }

    public boolean validateItemInventory(Merch desire) throws VendingMachineNoItemInventoryException {
        boolean inStock = false;
        inStock = jeeves.checkItemStock(desire);
        return inStock;
    }

}
