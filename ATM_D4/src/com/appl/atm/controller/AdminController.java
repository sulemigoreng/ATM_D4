/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.DepositViewControler;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.HashMap;
/**
 *
 * @author Zara Veda
 */
public class AdminController {
    Deposit deposit;
    DepositSlot updateSlot;
    BankDatabase bankDatabase;
    private BankStatementController bankStatement;
    
    public AdminController(Transaction theDeposit, DepositSlot theDepositSlot,
        BankDatabase theBankDatabase) {
        updateSlot = theDepositSlot;
        bankDatabase = theBankDatabase;
    }
    
    public void confirmDeposit(){
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        DepositViewControler depositView = new DepositViewControler();
        HashMap<Customer, Double> theList = bankDatabase.getList();
        
        depositView.showList(bankDatabase.getList());
        screen.displayMessage("Choose the account number : ");
        int choosen = keypad.getInput();
        
        if (theList.containsKey(bankDatabase.getCustomer(choosen))){
            bankStatement = new BankStatementController(keypad,screen,bankDatabase.getCustomer(choosen));
            bankStatement.addLogDeposit(String.valueOf(choosen),theList.get(bankDatabase.getCustomer(choosen)), "Deposit", true);
            updateSlot.deleteList(bankDatabase.getList(), bankDatabase.getCustomer(choosen),
            bankDatabase);
            depositView.showList(bankDatabase.getList());
        }
    }
}
