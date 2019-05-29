/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zara Veda
 */
public class DepositTest {
    
    /**
     * Test of execute method, of class Deposit.
     */
    
    BankDatabase theBankDatabase = new BankDatabase();
    DepositSlot theDepositSlot = new DepositSlot();
    Deposit instance = new Deposit(1234, theBankDatabase, theDepositSlot);
    
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        int expResult = 1;
        int result = instance.execute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmount method, of class Deposit.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        instance.setAmount(20.0);
        double expResult = 20;
        double result = instance.getAmount();
        assertEquals(expResult, result, 20.0);
        
    }

    /**
     * Test of getDepositSlot method, of class Deposit.
     */
    @Test
    public void testGetDepositSlot() {
        System.out.println("getDepositSlot");
        DepositSlot result = instance.getDepositSlot();
        assertNotNull(result);
    }
    
}
