/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachineservicelayer;

import com.sguild.vendingmachine.vendingmachinedao.VMAuditDao;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDao;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import com.sguild.vendingmachine.vendingmachinedto.ChangePurse;
import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VMAuditDao auditDao;

    BigDecimal currentBalance = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VMAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void loadInventory() throws VendingMachineDataTransferException {
        dao.loadInventory();
    }

    @Override
    public void saveInventory() throws VendingMachineDataTransferException {
        dao.saveInventory();
    }

    @Override
    public boolean checkBalance(Merch desire) throws VendingMachineInsufficientFundsException {
        boolean sufficientBalance = false;

        if (getDifference(desire.getItemCost()).compareTo(ZERO) >= 0) {
            sufficientBalance = true;
        } else {
            sufficientBalance = false;
            throw new VendingMachineInsufficientFundsException("Insufficient funds for " + desire.getItemName() + " || Item cost: $" + desire.getItemCost() + " || User's Credit: $" + currentBalance);
        }

        return sufficientBalance;
    }

    @Override
    public List<Merch> getAllMerch() {
        return dao.getAllMerch();
    }

    @Override
    public Merch getItem(int slotNum) {
        return dao.getItem(slotNum);
    }

    @Override
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void addToCurrentBalance(BigDecimal balanceAddition) {
        this.currentBalance = currentBalance.add(balanceAddition);
    }

    @Override
    public void setCurrentBalance(BigDecimal balanceAddition) {
        this.currentBalance = balanceAddition;
    }

    @Override
    public int getInvSlots() {
        int totalMerchs = dao.getInvSlots();
        return totalMerchs;
    }

    @Override
    public boolean checkItemStock(Merch thisMerch) throws VendingMachineNoItemInventoryException {
        boolean itemStocked = false;
        if (thisMerch.getStockCount() > 0) {
            itemStocked = true;
        } else {
            itemStocked = false;
            throw new VendingMachineNoItemInventoryException(thisMerch.getItemName() + " is out of stock.");
        }
        return itemStocked;
    }

    @Override
    public BigDecimal getDifference(BigDecimal price) {
        BigDecimal difference = currentBalance.subtract(price);
        return difference;
    }

    @Override
    public int convertChange(BigDecimal currentBalance) {
        BigDecimal totalPenniesBD = currentBalance.movePointRight(2);
        int totalPenniesInt = totalPenniesBD.intValue();
        return totalPenniesInt;
    }

    @Override
    public ChangePurse calculateChange(int penniesFromConvertChange) {
        ChangePurse usersChange = new ChangePurse(penniesFromConvertChange);
        return usersChange;
    }

    @Override
    public void clearChange() {
        //usersChange.setDollars(0);
        //usersChange.setQuarters(0);
        //usersChange.setDimes(0);
        //usersChange.setNickles(0);
        //usersChange.setPennies(0);
        currentBalance = currentBalance.subtract(currentBalance);
    }


}
