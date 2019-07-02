/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Bisnis;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.MasaDepan;
import com.appl.atm.model.Siswa;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.DepositViewControler;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.view.View;
import java.util.HashMap;
/**
 *
 * @author Zara Veda
 */
public class ConfirmDepositController extends TransactionController {
    DepositSlot depositSlot;
    BankDatabase bankDatabase;
    Keypad keypad;
    Screen screen;
    BankStatementController bankStatement;
    public ConfirmDepositController(DepositSlot theDepositSlot,
        BankDatabase theBankDatabase, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);

        depositSlot = theDepositSlot;
        bankDatabase = theBankDatabase;
    }

    public int run(){
        screen = getScreen();
        keypad = getKeypad();
        DepositViewControler screenView = new DepositViewControler();

        /* Getting the list of deposit slot */
        HashMap<Customer, Double> theList = bankDatabase.getList();

        screenView.showList(bankDatabase.getList());
        screen.displayMessage("\nChoose the account number (or 0 to cancel) : ");
        int choosen = keypad.getInput();

        if(choosen != 0) {
            if(theList.containsKey(bankDatabase.getCustomer(choosen))){
                bankStatement = new BankStatementController(keypad,screen,bankDatabase.getCustomer(choosen));
                double depositSize = theList.get(bankDatabase.getCustomer(choosen));
                deleteSlot(bankDatabase, choosen);
                bankStatement.addLog("Deposit  ", 0.0, depositSize, "Verified : [" + true + "]");
            }
            
                
            /* Showing the updated deposit slot */
            screen.displayMessage("\n");
            screenView.showList(bankDatabase.getList());
        } else {
            screen.displayMessageLine("Back to menu..");
        }

        return 0;
    }

    private void deleteSlot(BankDatabase bankDatabase, int choosen) {
        /* Slot tidak kosong
         * Mendelete salah satu envelope yang diterima oleh admin
         */

        depositSlot.deleteList(bankDatabase.getList(), bankDatabase.getCustomer(choosen),
            bankDatabase);
    }
}
