/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedao;

/**
 *
 * @author apprentice
 */
public interface VMAuditDao {
    
    public void writeAuditEntry(String entry) throws VendingMachineDataTransferException;
    
}
