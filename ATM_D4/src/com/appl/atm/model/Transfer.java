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
        
        //Jika account customer jenis siswa maka akan langsung return constant
        if(account.isSiswa()) {
            return IS_SISWA;
        }
        
        //Jika account tujuan tidak ada maka akan return constant
        if(getBankDatabase().getCustomer(accountTransfered) == null){
            return ACCOUNT_NOT_FOUND;
        }
        
        //Jika account tujuan sama dengan account pengirim maka akan return constant
        if(accountTransfered == getAccountNumber()){
            return SAME_ACCOUNT;
        }
        
        if(account.isCustomer()) {
            //Uang tidak mencukupi
            if(account.getAvailableBalance() < amount){
                return INSUFFICIENT_BALANCE;
            }
            if(amount == 0){
                return TRANSFER_CANCELED;
            }
            else{
                if(account.insertTransferLog(getBankDatabase().getCalendar(), amount)){
                    //accountTransfered = screen.processInputRecipientV();
                    
                        Customer accountTransfer = getBankDatabase().getCustomer(accountTransfered);
                        account.debit(amount);
                        accountTransfer.credit(amount);
                        accountTransfer.setAvailableBalance(amount+accountTransfer.getAvailableBalance());
                        accountTransfer.getTransaksiLog().add("[DATE]\tTransfer\t$ 0.0\t$ "+ amount + "\t\t$ " + 
                                accountTransfer.getAvailableBalance() + "\t\t$ " + accountTransfer.getTotalBalance() 
                                + "\t\tFrom : [" + account.getAccountNumber() + "]");//menambahkan bankstatement ke akun customer tujuan transfer 
                        //screen.processDisplayTransfered();
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
