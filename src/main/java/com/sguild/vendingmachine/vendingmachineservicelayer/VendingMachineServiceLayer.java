/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachineservicelayer;

import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import com.sguild.vendingmachine.vendingmachinedto.ChangePurse;
import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {

    public void loadInventory() throws VendingMachineDataTransferException;

    public void saveInventory() throws VendingMachineDataTransferException;

    public List<Merch> getAllMerch();

    public Merch getItem(int slotNum);

    public BigDecimal getCurrentBalance();

    public void setCurrentBalance(BigDecimal balanceAddition);

    public void addToCurrentBalance(BigDecimal balanceAddition);

    public boolean checkBalance(Merch desire) throws VendingMachineInsufficientFundsException;

    public int getInvSlots();
    
    public boolean checkItemStock(Merch thisMerch) throws VendingMachineNoItemInventoryException;
    
    public BigDecimal getDifference(BigDecimal price);
    
    public int convertChange(BigDecimal currentBalance);
    
    public ChangePurse calculateChange(int penniesFromConvertChange);
    
    public void clearChange();

}
