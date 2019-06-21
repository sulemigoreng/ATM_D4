/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.model.Customer;

/**
 *
 * @author ASUS
 */
public class BankStatementController extends TransactionController {
    Customer customer;

    public BankStatementController(Keypad theKeypad, Screen theScreen, Customer customer) {
        super(theKeypad, theScreen);
        this.customer=customer;
    }
    
    // add every transaction executed in log transaction
    public void addLog(String description, double debit, double credit, String information){
        // saved in order [date, type of transaction, debit, credit, available balance, total balance, information]
        customer.getTransaksiLog().add("[DATE]\t"+description+"\t$ "+debit+"\t$ "+credit+"\t\t$ "+customer.getAvailableBalance()+"\t\t$ "+customer.getTotalBalance()+"\t\t"+information);
    }
    
    @Override
    public int run() {
        
        if(customer.getTransaksiLog().isEmpty()){ // if account has no transaction yet
            displayEmptyLogTransaction(); // show empty log transaction
        }
        else{
            displayLogTransaction(); // show log transaction
        }
        
        return 0;
    }
    
    // show text when user doesn't have log transaction
    public void displayEmptyLogTransaction(){
        Screen screen = getScreen();
        
        screen.displayMessage("Account Number : ");
        screen.displayMessageLine(String.valueOf(customer.getAccountNumber()));
        screen.displayMessageLine("Empty Log Transaction!");
    }
    
    // show log transaction
    public void displayLogTransaction(){
        Screen screen = getScreen();
        
        screen.displayMessage("Account Number : ");
        screen.displayMessageLine(String.valueOf(customer.getAccountNumber()));
        screen.displayMessageLine("Date\tDescription\tDebit\tCredit\t\tAvailable Balance\tTotal Balance\tInformation");
        for(int i = 0; i < customer.getTransaksiLog().size();i++){ // get size of transaction
            screen.displayMessageLine(customer.getTransaksiLog().get(i)); // show all history of transcation sorted in descending
        }
    }
}
