/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.CashDispenser;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Zara Veda
 */
public class CheckCashDispenserController extends TransactionController {
    Keypad keypad;
    Screen screen;
    CashDispenser cashDispenser;
    
    public CheckCashDispenserController(CashDispenser theCashDispenser,
        Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        
        cashDispenser = theCashDispenser;
    }
    
    @Override
    public int run() {
        screen = getScreen();
        keypad = getKeypad();
        
        screen.displayMessageLine("Amount in dispenser : ");
        screen.displayDollarAmount(cashDispenser.getAmountCashDispenser());
        screen.displayMessageLine("\n" + "Lots of money : " +
            cashDispenser.getAmountCashDispenser()/20);
        return 0;
    }
    
}
