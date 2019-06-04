/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.appl.atm.controller.AddAccountViewController;
import com.appl.atm.view.Keypad;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
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
public class AdminViewControllerTest {
    private final InputStream systemIn = System.in;
    private final OutputStream systemOut = System.out;
    
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    
    private AddAccountViewController testObject;
    private String inputTest;
    private Keypad keypad;
    
    public AdminViewControllerTest() {
        testObject = new AddAccountViewController();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    
    private void provideInput(String data){
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    
    private String getOutput(){
        return testOut.toString();
    }
    
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut((PrintStream) systemOut);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
//    @Test
//    public void testReqAccountNumber(){
//        //simulasi user menginputkan 1234
//        final String testInput = "1234";
//        provideInput(testInput);
//        
//        assertEquals("Uji user input 1234", testInput, getOutput());
//    }
}
