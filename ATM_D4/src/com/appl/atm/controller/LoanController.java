/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Transaction;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Payment;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author USER
 */
public class LoanController extends TransactionController{
    
    Payment payment;
    Customer customer;
    
    public LoanController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
	super(theKeypad, theScreen);
    }
    
    @Override
    public int run(){
        Keypad keypad = getKeypad();
        Screen screen = getScreen();
        screen.displayMessage("Loan ID: ");
        int id = keypad.getInput();
        payment = customer.getInvoce(id);
        screen.displayMessage("Enter Nominal: ");
        double amount = keypad.getInput();
        if(customer.getAvailableBalance()>= amount ) {
            if (customer.getTotalBalance() >= amount){
                payment.reduceNominal(amount);
                customer.debit(amount);
            }
        }
        if(payment.getBillNominal() == 0) {
            customer.deleteInvoice(id);
        }
        return 0;
    }
}
