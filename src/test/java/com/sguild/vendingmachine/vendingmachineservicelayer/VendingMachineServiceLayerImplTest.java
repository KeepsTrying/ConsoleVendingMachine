/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachineservicelayer;

import com.sguild.vendingmachine.vendingmachinedao.VMAuditDao;
import com.sguild.vendingmachine.vendingmachinedao.VMAuditDaoImpl;
import com.sguild.vendingmachine.vendingmachinedao.VMDaoImplStub;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDao;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import com.sguild.vendingmachine.vendingmachinedto.ChangePurse;
import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer jeeves;
    
    
    
    public VendingMachineServiceLayerImplTest() {
    VendingMachineDao testDao = new VMDaoImplStub();
    VMAuditDao testAuditDao = new VMAuditDaoImpl();
    jeeves = new VendingMachineServiceLayerImpl(testDao, testAuditDao);
    }
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   

    

    /**
     * Test of checkBalance method, of class VendingMachineServiceLayerImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckBalance() throws Exception {
        jeeves.setCurrentBalance(new BigDecimal(10.00).setScale(2));
        Assert.assertTrue(jeeves.checkBalance(jeeves.getItem(1)));
        
    }

    /**
     * Test of getAllMerch method, of class VendingMachineServiceLayerImpl.
     * @throws com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException
     */
    @Test
    public void testGetAllMerch() throws VendingMachineDataTransferException {
        Assert.assertEquals(1, jeeves.getAllMerch().size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetItem() {
        Merch testItem = jeeves.getItem(1);
        
        Assert.assertEquals(jeeves.getItem(1), testItem);
    }

    /**
     * Test of getStockCount method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetStockCount() {
        int stockCount = jeeves.getItem(1).getStockCount();
        Assert.assertEquals(10, stockCount);
    }

    /**
     * Test of getCurrentBalance method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetCurrentBalance() {
        jeeves.setCurrentBalance(new BigDecimal(10.00).setScale(2));
        Assert.assertEquals(new BigDecimal(10.00).setScale(2), jeeves.getCurrentBalance());
    }

    /**
     * Test of addToCurrentBalance method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testAddToCurrentBalance() {
        jeeves.setCurrentBalance(new BigDecimal(10.00).setScale(2));
        jeeves.addToCurrentBalance(new BigDecimal(5.00).setScale(2));
        
        Assert.assertEquals(jeeves.getCurrentBalance(), new BigDecimal(15.00).setScale(2));
    }

    /**
     * Test of setCurrentBalance method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testSetCurrentBalance() {
        jeeves.setCurrentBalance(new BigDecimal(10.00).setScale(2));
        BigDecimal testBalance = new BigDecimal(10.00).setScale(2);
        Assert.assertEquals(testBalance, jeeves.getCurrentBalance());
    }

    /**
     * Test of getInvSlots method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetInvSlots() {
        int invSlots = jeeves.getInvSlots();
        Assert.assertEquals(1, invSlots);
    }

    /**
     * Test of checkItemStock method, of class VendingMachineServiceLayerImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testCheckItemStock() throws Exception {
        boolean isStocked = jeeves.checkItemStock(jeeves.getItem(1));
        Assert.assertTrue(isStocked);
    }

    /**
     * Test of getDifference method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetDifference() {
        jeeves.setCurrentBalance(new BigDecimal(10.00).setScale(2));
        BigDecimal testPrice = new BigDecimal(5.00).setScale(2);
        BigDecimal testDifference = jeeves.getDifference(testPrice);
        BigDecimal expectedResult = new BigDecimal(5.00).setScale(2);
        Assert.assertEquals(expectedResult, testDifference);
    }

    /**
     * Test of convertChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testConvertChange() {
        jeeves.setCurrentBalance(new BigDecimal("1.99").setScale(2));
        int testPennies = jeeves.convertChange(jeeves.getCurrentBalance());
        
        Assert.assertEquals(199, testPennies);
    }

    /**
     * Test of calculateChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testCalculateChange() {
        ChangePurse testPurse = jeeves.calculateChange(199);
        
        Assert.assertTrue(testPurse.getClass() == ChangePurse.class);
    }

    /**
     * Test of clearChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testClearChange() {
        jeeves.setCurrentBalance(new BigDecimal("1.99"));
        System.out.println("testClearChange: balance starts at $" + jeeves.getCurrentBalance());
        jeeves.clearChange();
        System.out.println("testClearChange results in a balance of $: " + jeeves.getCurrentBalance());
        BigDecimal expectedResult = new BigDecimal("0.00");
        Assert.assertEquals(expectedResult, jeeves.getCurrentBalance());
    }
    
}
