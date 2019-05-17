/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;
import java.util.HashMap;
import com.appl.atm.model.Customer;

/**
 *
 * @author Annazar
 */
public class DepositSlot {
    public boolean isEnvelopeReceived() {
	return true; // deposit envelope was received
    } 
    public boolean deleteList(HashMap envelopes, Customer theCustomer, double amount){
        if(envelopes.containsKey(theCustomer)){
            double balance = theCustomer.getAvailableBalance();
            theCustomer.setAvailableBalance(balance+amount);
            envelopes.remove(theCustomer);
            return true;
        }else{
            return false;
        }   
    }
}