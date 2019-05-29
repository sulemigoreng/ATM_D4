package com.appl.atm.model;

import com.appl.atm.model.BankDatabase;
import java.util.Calendar;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateSystemTest {
    
    public DateSystemTest() {

    }
    
    @Test 
    public void DateIncrementTest() {
        BankDatabase bankDatabase = new BankDatabase();
        Calendar prevDate = bankDatabase.getDate();
        bankDatabase.addDate();
        
        assertTrue(prevDate.get(Calendar.DAY_OF_YEAR) + 1 == bankDatabase.getDate().get(Calendar.DAY_OF_YEAR));        
    }
    
    @Test
    public void DateRefreshTest() {
        BankDatabase bankDatabase = new BankDatabase();

        assertNotEquals(0, bankDatabase.getDate().compareTo(bankDatabase.getDate()));
        assertNotEquals(0, bankDatabase.getDate().compareTo(bankDatabase.getDate()));
        assertNotEquals(0, bankDatabase.getDate().compareTo(bankDatabase.getDate()));
        assertNotEquals(0, bankDatabase.getDate().compareTo(bankDatabase.getDate()));
        bankDatabase.addDate();
        assertNotEquals(0, bankDatabase.getDate().compareTo(bankDatabase.getDate()));

    }
    @Test
    public void WithdrawalLogTest() {
        BankDatabase bankDatabase = new BankDatabase();
        
        Bisnis bisnisCustomer = new Bisnis(1, 1, 1000, 1000);
        
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 10));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 50));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 30));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 20));
         
        assertEquals(110.0, bisnisCustomer.getSameDayTransactionAmount(Customer.ETransactionKind.TRANSFER, bankDatabase.getDate()), 0.0);
      
        bankDatabase.addDate();

        assertEquals(0.0, bisnisCustomer.getSameDayTransactionAmount(Customer.ETransactionKind.TRANSFER, bankDatabase.getDate()), 0.0);
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 50.0));
        assertEquals(50.0, bisnisCustomer.getSameDayTransactionAmount(Customer.ETransactionKind.TRANSFER, bankDatabase.getDate()), 0.0);
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getDate(), 500.0));

        assertEquals(550.0, bisnisCustomer.getSameDayTransactionAmount(Customer.ETransactionKind.TRANSFER, bankDatabase.getDate()), 0.0);
    }
}
