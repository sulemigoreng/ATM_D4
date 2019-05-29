/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.model.Withdrawal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Riyanzani
 */
public class WithdrawalTest {
    private Withdrawal W;
    private int AccountNumber;
    private BankDatabase BD;
    private CashDispenser CD;
    
    public WithdrawalTest() {
        W = new Withdrawal(AccountNumber, BD, CD);
    }
    
    @Test
    public void getAmountTest(){
        W.setAmount(2000);
        assertEquals("Test Get Amount", 2000, W.getAmount());
    }
    
    @Test
    public void getCashDispenserTest(){
        assertEquals("Test Get Dispenser" ,CD , W.getCashDispenser());
    }
    
}
