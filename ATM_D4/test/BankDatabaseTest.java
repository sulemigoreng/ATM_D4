/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.appl.atm.model.Admin;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naufal Rajabi
 */
public class BankDatabaseTest {
    private BankDatabase testObject;
    private Customer testCustomer;

    public BankDatabaseTest() {
        testObject = new BankDatabase();
        testCustomer = testObject.getCustomer(1234);
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGetAccount(){
        assertNotNull("Uji akun 1234", testObject.getAccount(1234));
        assertNull("Uji akun sembarang", testObject.getAccount(0000));
    }
    
    @Test
    public void testGetCustomer(){
        assertNotNull("Uji akun 1234", testObject.getCustomer(1234));
        assertNull("Uji akun sembarang", testObject.getCustomer(0000));
    }
    
    @Test
    public void testIsUSerBlocked(){
        assertFalse("Uji akun 1234 belum terblokir", testObject.isUserBlocked(1234));
        
        //test setelah penambahan akun terblokir
        testObject.addBlockedAccount(testCustomer);
        assertTrue("Uji akun 1234 terblokir", testObject.isUserBlocked(1234));
        
        //test ketika sudah akun terblokir sudah di remove, index 0
        testObject.removeBlockedAccount(0);
        assertFalse("Uji akun 1234 setelah unblock", testObject.isUserBlocked(1234));
    }
    
    @Test
    public void testGetBlockedAccountIndex(){
        assertEquals("Uji akun 1234 sebelum block", -1, testObject.getBlockedAccountIndex(1234));
        
        testObject.addBlockedAccount(testCustomer);
        assertEquals("Uji akun 1234 setelah block", 0, testObject.getBlockedAccountIndex(1234));
        
        testObject.removeBlockedAccount(0);
        assertEquals("Uji akun 1234 setelah unblock", -1, testObject.getBlockedAccountIndex(1234));
    }
    
    @Test
    public void testIsUserExist(){
        assertTrue("Uji akun admin", testObject.isUserExist(1));
        assertTrue("Uji akun 1234", testObject.isUserExist(1234));
        assertFalse("Uji akun sembarang", testObject.isUserExist(0000));
    }
    
    
}
