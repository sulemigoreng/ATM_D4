/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
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
    BankDatabase bankDatabase;
    
    public AdminController(Deposit theDeposit, BankDatabase theBankDatabase) {
        deposit = theDeposit;
        bankDatabase = theBankDatabase;
    }
    
    public void confirmDeposit(){
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        DepositSlot updateSlot = new DepositSlot();
        DepositViewControler depositView = new DepositViewControler();
//        HashMap theList = deposit.getList();
        
//        depositView.showList(deposit.getList());
        screen.displayMessage("Choose the account number : ");
        int choosen = keypad.getInput();
        
//        updateSlot.deleteList(deposit.getList(), bankDatabase.getCustomer(choosen),
//            deposit.getAmount());
        
//        depositView.showList(deposit.getList());
    }
}
