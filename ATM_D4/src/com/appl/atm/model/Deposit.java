/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Annazar
 */
public class Deposit extends Transaction {

    private double amount; // amount to deposit
    private DepositSlot depositSlot; // reference to deposit slot
    private HashMap<IAccount, Integer> envelopeList;
    
    // Deposit constructor
    public Deposit(int userAccountNumber, BankDatabase atmBankDatabase,
	    DepositSlot atmDepositSlot) {

	// initialize superclass variables
	super(userAccountNumber, atmBankDatabase);
	depositSlot = atmDepositSlot;
        envelopeList = new HashMap<IAccount, Integer>();
    }

    @Override
    public int execute() {
	if (depositSlot.isEnvelopeReceived(envelopeList,
                getBankDatabase().getAccount(getAccountNumber()), amount)) {
	    Customer account = getBankDatabase().getCustomer(getAccountNumber());
	    account.credit(amount);
	    return DEPOSIT_SUCCESSFUL;
	} else {
	    return ENVELOPE_IS_NOT_RECEIVED;
	}
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
	this.amount = amount;
    }

    /**
     * @return the depositSlot
     */
    public DepositSlot getDepositSlot() {
	return depositSlot;
    }

    /**
     * @param depositSlot the depositSlot to set
     */
    public void setDepositSlot(DepositSlot depositSlot) {
	this.depositSlot = depositSlot;
    }
}