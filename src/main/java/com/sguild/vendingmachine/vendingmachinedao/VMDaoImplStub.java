/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedao;

import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class VMDaoImplStub implements VendingMachineDao {
    
    private Merch testMerch;
    private Map<Integer, Merch> testMap = new HashMap<>();
    
    
    public VMDaoImplStub() {
        testMerch = new Merch();
        testMerch.setSlotNum(1);
        testMerch.setItemName("test");
        testMerch.setItemCost(new BigDecimal(1.00).setScale(2));
        testMerch.setStockCount(10);
        
        testMap.put(1, testMerch);    
    }
    
    

    @Override
    public void setInventoryFile(String inventoryFile) {
        throw new UnsupportedOperationException("Not supported in Service Layer unit testing");
    }

    @Override
    public List<Merch> getAllMerch() {
        return new ArrayList<Merch>(testMap.values());
    }

    @Override
    public Merch getItem(int slotNum) {
        if (slotNum == testMerch.getSlotNum()) {
        return testMap.get(slotNum);
        } else {
            return null;
        }
    }

    @Override
    public int getInvSlots() {
        return testMap.size();
    }

    @Override
    public void loadInventory() throws VendingMachineDataTransferException {
        throw new UnsupportedOperationException("Not supported in Service Layer unit testing"); 
    }

    @Override
    public void saveInventory() throws VendingMachineDataTransferException {
        throw new UnsupportedOperationException("Not supported in Service Layer unit testing"); 
    }
    
}
