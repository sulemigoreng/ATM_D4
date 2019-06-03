/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;

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
        
        if(account.isSiswa()) {
            return IS_SISWA;
        }
        
        if(getBankDatabase().getCustomer(accountTransfered) == null){
            return ACCOUNT_NOT_FOUND;
        }
        
        if(accountTransfered == getAccountNumber()){
            return IS_MY_ACCOUNT;
        }
        
        if(account.isCustomer()) {
            if(account.getAvailableBalance() < amount){
                return INSUFFICIENT_BALANCE;
            }
            if(amount == 0){
                return TRANSFER_CANCELED;
            } else {
                if(account.insertTransferLog(getBankDatabase().getDate(), amount)){
                    Customer accountTransfer = getBankDatabase().getCustomer(accountTransfered);
                    accountTransfer.getTransaksiLog().add("[DATE] Transfer      From Account_Number : " + 
                            String.valueOf(getAccountNumber())+ "     with amount $ " + 
                            String.valueOf(amount));
                    account.debit(amount);
                    accountTransfer.setAvailableBalance(accountTransfer.getAvailableBalance() + amount);
                    accountTransfer.setTotalBalance(accountTransfer.getTotalBalance() + amount);
                    return TRANSFER_SUCCESS;
                }
                else{
                    if (account.isBisnis()) {
                        return EXCEED_TRANSFER_LIMIT_FOR_BISNIS;
                    }
                    if (account.isMasaDepan()) {
                        return EXCEED_TRANSFER_LIMIT_FOR_MASA_DEPAN;
                    }
                }
            }
        }
        else{
            return NOT_A_CUSTOMER;
        }
        return 0;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public void setAccountTransferred(int accountNumber){
        this.accountTransfered = accountNumber;
    }
    
    public double getTransferLimit(){
        return getBankDatabase().getCustomer(getAccountNumber()).getDailyTransferLimit();
    }
    
    public int getAccountTransferred(){
        return accountTransfered;
    }
    
    public double getAmount(){
        return amount;
    }
}
