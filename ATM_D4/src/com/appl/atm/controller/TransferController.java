/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transfer;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
/**
 *
 * @author dewan
 */

public class TransferController extends TransactionController{
    private Transfer transaction;
    private TransferViewController viewC;
    
    
    public TransferController (Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        transaction = (Transfer) theTransaction;
    }

    @Override
    public int run() {
        
        return 0;
    }
}
