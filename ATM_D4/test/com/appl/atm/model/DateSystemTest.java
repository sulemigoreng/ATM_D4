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
        Calendar prevDate = bankDatabase.getCalendar();
        bankDatabase.addDate();
        
        assertTrue(prevDate.get(Calendar.DAY_OF_YEAR) + 1 == bankDatabase.getCalendar().get(Calendar.DAY_OF_YEAR));        
    }
    
    @Test
    public void DateRefreshTest() {
        BankDatabase bankDatabase = new BankDatabase();

        assertNotEquals(0, bankDatabase.getCalendar().compareTo(bankDatabase.getCalendar()));
        assertNotEquals(0, bankDatabase.getCalendar().compareTo(bankDatabase.getCalendar()));
        assertNotEquals(0, bankDatabase.getCalendar().compareTo(bankDatabase.getCalendar()));
        assertNotEquals(0, bankDatabase.getCalendar().compareTo(bankDatabase.getCalendar()));
        bankDatabase.addDate();
        assertNotEquals(0, bankDatabase.getCalendar().compareTo(bankDatabase.getCalendar()));

    }
    @Test
    public void WithdrawalLogTest() {
        BankDatabase bankDatabase = new BankDatabase();
        
        Bisnis bisnisCustomer = new Bisnis(1, 1, 1000, 1000);
        
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 10));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 50));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 30));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 20));
        
        assertTrue(bisnisCustomer.isDailyTransferLimitReached(bankDatabase.getCalendar(), 110));
        
        bankDatabase.addDate();

        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 50.0));
        assertTrue(bisnisCustomer.insertTransferLog(bankDatabase.getCalendar(), 500.0));
    }
}
