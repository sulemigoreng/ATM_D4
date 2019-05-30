/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Bisnis;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.MasaDepan;
import com.appl.atm.model.Siswa;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.AdminViewController;
import com.appl.atm.view.DepositViewControler;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.view.View;
import java.util.HashMap;
/**
 *
 * @author Zara Veda
 */
public class AdminController {
    BankDatabase bankDatabase;
    
    public AdminController(Transaction theDeposit, DepositSlot theDepositSlot,
        BankDatabase theBankDatabase) {
        bankDatabase = theBankDatabase;
    }
    
    public void addAccount(int accountType) {
        AdminViewController view = new AdminViewController();
        
        int accountNumber = 0;
        boolean isUnique = false;
        
        while (!isUnique){
            boolean isExist;
            accountNumber = view.reqAccountNumber();
            isExist = bankDatabase.isUserExist(accountNumber);
            if (isExist){
                view.showMessageNotUnique();
            }else {
                isUnique = true;
            }
        }
        
        int pin = view.reqPinNumber();
        double balance = view.reqBalance(); 
        
        switch (accountType){
            case ADD_SISWA:{
                Siswa newSiswa = new Siswa(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newSiswa);
                break;
            }
            case ADD_BISNIS:{
                Bisnis newBisnis = new Bisnis(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newBisnis);
                break;
            }
            case ADD_MASADEPAN:{
                MasaDepan newMasaDepan = new MasaDepan(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newMasaDepan);
                break;
            }
        }
    }
    
    public void blockAccount(int accountNumber){
        Customer blockedCustomer = bankDatabase.getCustomer(accountNumber);
        if(blockedCustomer != null){
            bankDatabase.addBlockedAccount(blockedCustomer);
        } else {
            AdminViewController view = new AdminViewController();
            view.displayUserDoesntExist();
        }
    }
    
    public void unblockAccount(int accountNumber){
       int unblockIndex = bankDatabase.getBlockedAccountIndex(accountNumber);
        if(unblockIndex == -1){
            AdminViewController view = new AdminViewController();
            view.displayUserDoesntExist();
        }else{
            bankDatabase.removeBlockedAccount(unblockIndex);
        }
        
    }
}
