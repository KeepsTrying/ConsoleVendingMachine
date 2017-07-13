/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Merch {

    private int slotNum;
    private String itemName;
    private BigDecimal itemCost;
    private int stockCount;
    
    @Override
    public String toString() {
        return "Desire: " + itemName + " ||  Cost: $" + itemCost + " || User's Credit: ";
    }

    public void Merch(int slotNum) {
        this.slotNum = slotNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

}
