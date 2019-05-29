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
    
    
    public void addLog(String accountNumber, int amount, String Transaction){
        customer.getTransaksiLog().add("[DATE] "+Transaction+"      Account_Number : "+accountNumber+"      with amount $ "+amount);
    }
    
    public void addLogDeposit(String accountNumber, double amount, String Transaction, boolean status){
        customer.getTransaksiLog().add("[DATE] "+Transaction+"      Account_Number : "+accountNumber+"      with amount $ "+amount+ "      Verified : " + status );
    }
    
    @Override
    public int run() {
        Screen screen = getScreen();
        
        if(customer.getTransaksiLog().isEmpty()){
            screen.displayMessageLine("Empty Log Transaction!");
        }
        else{
            for(int i = 0; i < customer.getTransaksiLog().size();i++){
                screen.displayMessageLine(customer.getTransaksiLog().get(i));
            }
        }
        
        return 0;
    }
}
