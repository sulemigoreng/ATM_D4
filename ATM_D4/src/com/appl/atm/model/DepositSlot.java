/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import java.util.HashMap;

/**
 *
 * @author Annazar
 */
public class DepositSlot {
    public boolean isEnvelopeReceived(HashMap envelopes, IAccount theAccount,
        double amount) {
        
        boolean acceptedStatus = addList(envelopes, theAccount, amount);
        
	return acceptedStatus; // deposit envelope was received
    } 
    
    //Admin's method
    public boolean addList(HashMap envelopes, IAccount theAccount, double amount) {
        if(envelopes.containsKey(theAccount)) {
            return false;
        } else {
            envelopes.put(theAccount, amount);
            return true;
        }
    }
}