/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;



import com.appl.atm.model.CashDispenser;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 import java.util.Scanner;
import static org.junit.Assert.*;


/**
 *
 * @author ASUS
 */
public class cashDispenserControllerTest {
    
    public cashDispenserControllerTest() {
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
     * Test of run method, of class cashDispenserController.
     */
    
    @Test
        public void getAmountShouldReturnAsItsInput() {
       Scanner scanner = new Scanner(System.in);
       int input=5;
       ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
       System.setIn(in);
       cashDispenserController test=new cashDispenserController();
        // assert statements
        assertEquals(in,test.getAmount());
    }
}
    

