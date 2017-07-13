/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author apprentice
 */
public class VMAuditDaoImpl implements VMAuditDao {
    
    public static final String AUDIT_FILE = "vmAudit.txt";

    @Override
    public void writeAuditEntry(String auditEntry) throws VendingMachineDataTransferException {
        PrintWriter auditWriter;
        
        try {
            auditWriter = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachineDataTransferException("Could not persist audit info.", e);
        }
        
        
        LocalDateTime timestamp = LocalDateTime.now();
        auditWriter.println(timestamp.toString() + " : " + auditEntry);
        auditWriter.flush();
        auditWriter.close();
      
    }
    
}
