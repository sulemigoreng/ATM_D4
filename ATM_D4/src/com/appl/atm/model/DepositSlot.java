/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;
import java.util.HashMap;
import com.appl.atm.model.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Annazar
 */
public class DepositSlot {
    public boolean isEnvelopeReceived(HashMap envelopes, Customer theAccount,
        double amount, BankDatabase theBankDatabase) {

        boolean acceptedStatus = addList(envelopes, theAccount, amount,
                theBankDatabase);
	      return acceptedStatus; // deposit envelope was received
    }

    //Admin's method
    public boolean addList(HashMap<Customer, Double> envelopes, Customer theAccount,
            double amount, BankDatabase theBankDatabase) {
        if(envelopes.containsKey(theAccount)) {
            return false;
        } else {
            theBankDatabase.setList(theAccount, amount);
            return true;
        }
    }
    
    public boolean deleteList(HashMap<Customer, Double> envelopes, Customer theCustomer,
            BankDatabase theBankDatabase){
        if(envelopes.containsKey(theCustomer)){
            double amount = (double)envelopes.get(theCustomer);
            double balance = theCustomer.getAvailableBalance();
            theCustomer.setAvailableBalance(balance+amount);
            theBankDatabase.updateList(theCustomer);
            return true;
        }else{
            return false;
        }
    }
}
