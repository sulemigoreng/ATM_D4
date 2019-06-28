/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Customer;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author mufqi
 */
public class BlockAccountController extends TransactionController {
    BankDatabase bankDatabase;
    public BlockAccountController(Keypad theKeypad, Screen theScreen, BankDatabase theBankDatabase) {
        super(theKeypad, theScreen);
        bankDatabase = theBankDatabase;
    }

    @Override
    public int run() {
        BlockAccountViewController viewController = new BlockAccountViewController();
        int accountNumber = viewController.requestAccountNumber();
        
        Customer blockedCustomer = bankDatabase.getCustomer(accountNumber);
        if(blockedCustomer != null) {
            bankDatabase.addBlockedAccount(blockedCustomer);
        } else {
            viewController.displayUserDoesntExist();
        }
        
        return 0;
    }
    
    
}
