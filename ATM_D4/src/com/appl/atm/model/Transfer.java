/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

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
        
        if(account.isCustomer()){
            amount = 100; //Nanti ganti pake modul dari view
            if(account.getAvailableBalance() < amount){
                //saldo ga cukup
            }
            else{
                if(account.getMaxTransfer() > amount){
                    accountTransfered = 1234; // ganti pake keypad input account
                    
                    if(getBankDatabase().getAccount(accountTransfered)!=null){
                        Customer accountTransfer = getBankDatabase().getCustomer(accountTransfered);
                        
                        account.credit(amount);
                        accountTransfer.debit(amount);
                        //display transfer success
                    }
                    else{
                        //account tidak ditemukan
                    }
                }
                else{
                    //melebihi batas maks transfer
                }
            }
        }
        else{
            //account bukan customer
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
