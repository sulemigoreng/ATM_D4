/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.controller.TransferViewController;

/**
 *
 * @author Fadhil
 */
public class Transfer extends Transaction{
    private double amount;
    private int accountTransfered;

    public Transfer(int userAccountNumber, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmBankDatabase);
    }

    @Override
    public int execute() {
        Customer account = getBankDatabase().getCustomer(getAccountNumber());
        TransferViewController screen = new TransferViewController();
        
        if(account.isCustomer()){
            amount = screen.processInputTheAmountV();
            if(account.getAvailableBalance() < amount){
                screen.processDisplayNotEnoughSaldo();
            }
            if(amount == 0){
                screen.processCanceled();
                return 0;
            }
            else{
                if(account.getMaxTransfer() > amount){
                    accountTransfered = screen.processInputRecipientV();
                    
                    if(getBankDatabase().getAccount(accountTransfered)!=null && accountTransfered != getAccountNumber()){
                        Customer accountTransfer = getBankDatabase().getCustomer(accountTransfered);
                        
                        account.debit(amount);
                        accountTransfer.credit(amount);
                        screen.processDisplayTransfered();
                    }
                    else{
                        screen.processDisplayAccountNotFound();
                    }
                }
                else{
                    screen.precessDisplayMaxOneDayLimitV(account.getMaxTransfer()); //ini bener ga?
                }
            }
        }
        else{
            screen.processDisplayAccountNotCustomer();
        }
        return 0;
    }
    
    /*
       ATMBankDatabase bankDatabase = getBankDatabase();
        ATMScreen screen = getScreen();
        ATMDatabaseControl databaseControl = getControl();
        //ATMBankDatabase bankDatabase = getBankDatabase();
        //ATMScreen screen = getScreen();
        
        tAmount = screen.transferDisplay();
        if(bankDatabase.getAccount(getAccountNumber()).getAvailableBalance() < tAmount){
            screen.AmountNotEnough();
        }
        else{
            if(tAmount > 100){
                screen.MaxTransfer();
            }
            else{
                tAccount = screen.InputAccountNumber();
                
                if(bankDatabase.getAccount(tAccount)!=null && tAccount != getAccountNumber()){
                    databaseControl.credit(bankDatabase.getAccount(getAccountNumber()), tAmount);
                    databaseControl.transfered(bankDatabase.getAccount(tAccount), tAmount);
                    screen.TransferSuccess(tAmount);
                }
                else{
                    if(tAccount == getAccountNumber()){
                        screen.SelfTransfer();
                    }
                    else{
                        screen.AccountNotFound();
                    }
                }
                
                //bankDatabase.debit(tAccount, tAmount);
            }
        }
        
    */
}
