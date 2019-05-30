/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.appl.atm.model.CashDispenser;
import static com.appl.atm.model.Constants.*;

/**
 *
 * @author Mhanif
 */
public class CashDispenserTest {
    private CashDispenser TestClass;
    
    public CashDispenserTest() {
      
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
          TestClass = new CashDispenser();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void IsSufficientTest(){
        boolean testing = TestClass.isSufficientCashAvailable(200);
        assertTrue(testing);
    }
}
