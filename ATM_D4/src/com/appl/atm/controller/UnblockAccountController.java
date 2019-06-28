/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.model.BankDatabase;

/**
 *
 * @author mufqi
 */
public class UnblockAccountController extends TransactionController{
    BankDatabase bankDatabase;
    public UnblockAccountController(Keypad theKeypad, Screen theScreen, BankDatabase theBankDatabase) {
        super(theKeypad, theScreen);
        bankDatabase = theBankDatabase;
    }

    @Override
    public int run() {
        BlockAccountViewController viewController = new BlockAccountViewController();
        int accountNumber = viewController.requestAccountNumber();
        int unblockIndex = bankDatabase.getBlockedAccountIndex(accountNumber);
        
        if(unblockIndex == -1){
            viewController.displayUserDoesntExist();
        } else {
            bankDatabase.removeBlockedAccount(unblockIndex);
        }
        
        return 0;
    }
}
