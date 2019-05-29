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

    public DepositController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
	super(theKeypad, theScreen);
	transaction = (Deposit) theTransaction;
        customer = transaction.getBankDatabase().getCustomer(transaction.getAccountNumber());
        bankStatement = new BankStatementController(theKeypad,theScreen,customer);
    }

    @Override
    public int run() {
	double amount = promptForDepositAmount();

	if (amount == DEPOSIT_CANCELED) {
	    getScreen().displayMessageLine("Canceling transaction...");
	} else {
	    transaction.setAmount(amount);
	    int receive = transaction.execute();
	    getScreen().displayMessage("Please insert a deposit envelope containing $");
	    getScreen().displayDollarAmount(amount);
	    getScreen().displayMessageLine("\n");
            if(receive == DEPOSIT_SUCCESSFUL){
                getScreen().displayMessageLine("Your envelope has been received.");
                getScreen().displayMessageLine("NOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");
                getScreen().displayMessageLine("check your balance to see the status of your previous deposit");
                bankStatement.addLogDeposit(String.valueOf(customer.getAccountNumber()), amount, "Deposit", false);
            } else {
                getScreen().displayMessageLine("Your envelope is not received");
            }
	}
	return 0;
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
	Screen screen = getScreen(); // get reference to screen

	// display the prompt
	screen.displayMessage("\nPlease enter a deposit amount in "
		+ "CENTS (or 0 to cancel): ");
	int input = getKeypad().getInput(); // receive input of deposit amount

	// check whether the user canceled or entered a valid amount
	if (input == DEPOSIT_CANCELED) {
	    return DEPOSIT_CANCELED;
	} else {
	    return (double) input / 100; // return dollar amount
	}
    }

}
