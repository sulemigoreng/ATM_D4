/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.DepositViewControler;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Annazar
 */
public class DepositController extends TransactionController {

    private Deposit transaction;
    private BankStatementController bankStatement;
    private Customer customer;
    private DepositViewControler depositviewcontroler;

    public DepositController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
	super(theKeypad, theScreen);
	transaction = (Deposit) theTransaction;
        customer = transaction.getBankDatabase().getCustomer(transaction.getAccountNumber());
        bankStatement = new BankStatementController(theKeypad,theScreen,customer);
        depositviewcontroler = new DepositViewControler();
    }

    @Override
    public int run() {
	double amount = promptForDepositAmount(); // get return from method promptForDepositAmount()

	if (amount == DEPOSIT_CANCELED) {
	    getScreen().displayMessageLine("Canceling transaction...");
	} else {
	    transaction.setAmount(amount);
	    int receive = transaction.execute();
	    depositviewcontroler.envelopeDeposit(amount);//print  the envelope deopsit to screen
            if(receive == DEPOSIT_SUCCESSFUL){
                depositviewcontroler.envelopeReceived();//print to screen when the enveloped received
                bankStatement.addLog("Deposit   ", 0.0, amount, "Verified : [" + false + "]");//menambahkan bankstatement ke akun customer setalah melakukan deposit 
            } else {
                depositviewcontroler.envelopednotReceiped();//print to screen when the enveloped not received
            }
	}
	return 0;
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
	Screen screen = getScreen(); // get reference to screen

	// display the prompt
	depositviewcontroler.inputAmount();
	int input = getKeypad().getInput(); // receive input of deposit amount

	// check whether the user canceled or entered a valid amount
	if (input == DEPOSIT_CANCELED) {
	    return DEPOSIT_CANCELED;
	} else {
	    return (double) input / 100; // return dollar amount
	}
    }

}
