/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import java.util.HashMap;
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
public class DepositSlotTest {
    
    /**
     * Test of deleteList method, of class DepositSlot.
     */
    @Test
    public void testDeleteList() {
        System.out.println("deleteList");
        
        // Arrage
        HashMap<Customer, Double> envelopes = new HashMap<>();
        BankDatabase theBankDatabase = new BankDatabase();
        Customer theCustomer = theBankDatabase.getCustomer(1234);
        DepositSlot instance = new DepositSlot();
      
        // Act
        envelopes.put(theCustomer,100.0);
        boolean result = instance.deleteList(envelopes, theCustomer, theBankDatabase);
        
        // Assert
        assertTrue(result);
    }
    
}
