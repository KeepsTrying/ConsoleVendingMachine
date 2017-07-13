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
public class VendingMachineDataTransferException extends Exception {

    public VendingMachineDataTransferException(String message) {
        super(message);
    }

    public VendingMachineDataTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}
