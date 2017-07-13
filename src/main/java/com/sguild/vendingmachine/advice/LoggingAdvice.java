/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.advice;

import com.sguild.vendingmachine.vendingmachinedao.VMAuditDao;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {

    VMAuditDao auditDao;

    public LoggingAdvice(VMAuditDao thisDao) {
        this.auditDao = thisDao;
    }

    public void createAuditEntry(JoinPoint method, Throwable exceptionType) {
        Object[] args = method.getArgs();
        String auditEntry = method.getSignature().getName() + ": " + exceptionType;

        /*
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }

         */
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDataTransferException e) {
            System.err.println("Error: Could not add Audit Entry to LoggingAdvice");
        }
    }
}
