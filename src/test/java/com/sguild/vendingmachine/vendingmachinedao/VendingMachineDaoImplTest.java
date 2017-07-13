/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine.vendingmachinedao;

import com.sguild.vendingmachine.vendingmachinedto.Merch;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoImplTest {

    VendingMachineDaoImpl testDao;
    //Map<Integer, Merch> inventory = new HashMap<>();

    public VendingMachineDaoImplTest() {
        testDao = new VendingMachineDaoImpl();
        testDao.setInventoryFile("testFile.txt");

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
     * Test of getAllMerch method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testGetAllMerch() throws VendingMachineDataTransferException {
        Assert.assertEquals(0, testDao.getAllMerch().size());
        System.out.println("testGetAllMerch unloaded results w/ inv size: " + testDao.getAllMerch());
        testDao.loadInventory();
        
        Assert.assertEquals(6, testDao.getAllMerch().size());
        System.out.println("testGetAllMerch once loaded results w/ inv size: " + testDao.getAllMerch());
        
    }

    /**
     * Test of getItem method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testGetItem() throws VendingMachineDataTransferException {
        testDao.loadInventory();
        Merch testMerch = new Merch();
        testMerch.setSlotNum(1);
        testMerch.setItemName("tomato");
        testMerch.setItemCost(new BigDecimal(.50).setScale(2));
        testMerch.setStockCount(18);
        
        System.out.println("testMerch name = " + testMerch.getItemName());
        System.out.println("testMerch cost = " + testMerch.getItemCost());
        
        
        Merch loadedMerch = testDao.getItem(1);
        
        System.out.println("loadedMerch name = " + loadedMerch.getItemName());
        System.out.println("loadedMerch cost = " + loadedMerch.getItemCost());
        
        
        Assert.assertEquals(testMerch.getSlotNum(), loadedMerch.getSlotNum());
        Assert.assertEquals(testMerch.getItemCost(), loadedMerch.getItemCost());
        Assert.assertEquals(testMerch.getItemName(), loadedMerch.getItemName());
        Assert.assertEquals(testMerch.getStockCount(), loadedMerch.getStockCount());
        
        Assert.assertEquals(testDao.getItem(1), loadedMerch);
    }

    /**
     * Test of getInvSlots method, of class VendingMachineDaoImpl.
     */
    @Test
    public void testGetInvSlots() throws VendingMachineDataTransferException {
        testDao.loadInventory();
        int testSlots = testDao.getInvSlots();
        
        Assert.assertEquals(6, testSlots);
        
    }

    /**
     * Test of loadInventory method, of class VendingMachineDaoImpl.
     *
     * @throws
     * com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException
     *
     *
     */
    @Test
    public void testLoadInventory() throws VendingMachineDataTransferException {
        testDao.loadInventory();

        Assert.assertEquals(6, testDao.getAllMerch().size());
        System.out.println("testGetAllMerch once loaded results w/ inv size: " + testDao.getAllMerch());
    }

    /**
     * Test of saveInventory method, of class VendingMachineDaoImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveInventory() throws Exception {
        testDao.loadInventory();
        testDao.getItem(1).setStockCount(18);
        System.out.println("stockCount -18- = " + testDao.getItem(1).getStockCount());
        testDao.saveInventory();
        testDao.loadInventory();
        Assert.assertEquals(18, testDao.getItem(1).getStockCount());
        
        
        testDao.getItem(3).setStockCount(35);
        System.out.println("stockCount -35- = " + testDao.getItem(3).getStockCount());
        testDao.saveInventory();
        testDao.loadInventory();
        Assert.assertEquals(35, testDao.getItem(3).getStockCount());
        
    }

}
