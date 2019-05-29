/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Withdrawal;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Annazar
 */
public class WithdrawalController extends TransactionController {

    private Withdrawal transaction;
    private WithdrawalViewController WVC;
    private BankStatementController bankStatement;
    private Customer customer;

    public WithdrawalController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
	super(theKeypad, theScreen);
	transaction = (Withdrawal) theTransaction;
        WVC = new WithdrawalViewController(theScreen,theKeypad);
        customer=transaction.getBankDatabase().getCustomer(transaction.getAccountNumber());
        bankStatement=new BankStatementController(theKeypad, theScreen, customer);
    }


    @Override
    public int run() {
	int amount = WVC.displayMenuOfAmounts();

	if (amount != 0) {
	    transaction.setAmount(amount);
	    int res = transaction.execute();

	    if (res == WITHDRAW_SUCCESSFUL) {
		WVC.withdrawalSuccessful();
                bankStatement.addLog(String.valueOf(customer.getAccountNumber()), amount, "Withdrawal");
	    } else if (res == BALANCE_NOT_ENOUGH) {
		WVC.balanceNotEnough();
	    } else if (res == CASHDISPENSER_NOT_ENOUGH) {
		WVC.cashdispenserNotEnough();
	    } else if (res == REACHED_MAX_WITHDRAWAL) {
                WVC.maxWithdrawal(transaction.getBankDatabase().getCustomer(transaction.getAccountNumber()).getDailyWithdrawalLimit());
            }
	}

	return 0;
    }
}