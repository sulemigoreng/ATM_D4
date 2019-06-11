/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import com.appl.atm.model.Customer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ANDRA
 */
public class DepositViewControler {
    Screen screen = new Screen();
    private Keypad keypad;
    
       public void inputAmount() {
        screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
    }
    
    public void envelopednotReceiped() {
        screen.displayMessageLine("Your envelope is not received");
    }
    
    public void envelopeReceived() {
        screen.displayMessageLine("\nYour envelope has been received.");
        screen.displayMessageLine("NOTE: The money just deposited will"
            + " not be available until we verify the amount of any "
            + "enclosed cash and your checks clear.");
        screen.displayMessageLine("check your balance to see the status of your previous deposit");
    }
    
    public void envelopeDeposit(double amount) {
        screen.displayMessage("Please insert a deposit envelope containing $");
        screen.displayDollarAmount(amount);
        screen.displayMessage("/n");
    }
    
    public void showList(HashMap<Customer, Double> theList) {
        screen.displayMessageLine("Account Number\t\tAmount");
        for(Map.Entry<Customer, Double> entry : theList.entrySet()) {
            Customer key = entry.getKey();
            
            screen.displayMessageLine(key.getAccountNumber() + "\t\t\t" + 
                entry.getValue());
        }
    }
}
