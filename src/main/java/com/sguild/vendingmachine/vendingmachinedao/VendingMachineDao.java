/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedao;

import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDao {
    
    public void setInventoryFile(String inventoryFile);

    public List<Merch> getAllMerch();

    public Merch getItem(int slotNum);
    
    public int getInvSlots();

    public void loadInventory() throws VendingMachineDataTransferException;

    public void saveInventory() throws VendingMachineDataTransferException;
    
}
