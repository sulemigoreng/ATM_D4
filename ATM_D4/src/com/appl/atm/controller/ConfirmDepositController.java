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
import com.appl.atm.view.AdminViewController;
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
    Deposit deposit;
    DepositSlot updateSlot;
    BankDatabase bankDatabase;
    Keypad keypad;
    Screen screen;
    BankStatementController bankStatement;
    
    public ConfirmDepositController(Transaction theDeposit, DepositSlot theDepositSlot,
        BankDatabase theBankDatabase, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        
        updateSlot = theDepositSlot;
        bankDatabase = theBankDatabase;
    }
    
    @Override
    public int run(){
        screen = getScreen();
        keypad = getKeypad();
        DepositViewControler depositView = new DepositViewControler();
        HashMap<Customer, Double> theList = bankDatabase.getList();
        
        depositView.showList(bankDatabase.getList());
        screen.displayMessage("Choose the account number : ");
        int choosen = keypad.getInput();
        bankStatement = new BankStatementController(keypad,screen,bankDatabase.getCustomer(choosen));
        bankStatement.addLogDeposit(String.valueOf(choosen),theList.get(bankDatabase.getCustomer(choosen)), "Deposit", true);
        updateSlot.deleteList(bankDatabase.getList(), bankDatabase.getCustomer(choosen),
            bankDatabase);
        
        depositView.showList(bankDatabase.getList());
        
        return 0;
    }
}
