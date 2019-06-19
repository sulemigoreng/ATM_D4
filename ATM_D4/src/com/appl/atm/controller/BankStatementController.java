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
    
    
    public void addLog(String description, double debit, double credit, String information){
        customer.getTransaksiLog().add("[DATE]\t"+description+"\t$ "+debit+"\t$ "+credit+"\t\t$ "+customer.getAvailableBalance()+"\t\t$ "+customer.getTotalBalance()+"\t\t"+information);
    }
    
    @Override
    public int run() {
        Screen screen = getScreen();
        
        if(customer.getTransaksiLog().isEmpty()){
            screen.displayMessage("Account Number : ");
            screen.displayMessageLine(String.valueOf(customer.getAccountNumber()));
            screen.displayMessageLine("Empty Log Transaction!");
        }
        else{
            screen.displayMessage("Account Number : ");
            screen.displayMessageLine(String.valueOf(customer.getAccountNumber()));
            screen.displayMessageLine("Date\tDescription\tDebit\tCredit\t\tAvailable Balance\tTotal Balance\tInformation");
            for(int i = 0; i < customer.getTransaksiLog().size();i++){
                screen.displayMessageLine(customer.getTransaksiLog().get(i));
            }
        }
        
        return 0;
    }
}
