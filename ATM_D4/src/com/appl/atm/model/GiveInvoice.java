package com.appl.atm.model;

public class GiveInvoice extends Transaction {
    // BalanceInquiry constructor
    public GiveInvoice(int userAccountNumber, BankDatabase atmBankDatabase) {

	super(userAccountNumber, atmBankDatabase);
    } 

    @Override
    public int execute() {
	return 0;
    }
    
    public Customer getCustomer(int AccountNumber) {
	return getBankDatabase().getCustomer(AccountNumber);
    }
}
