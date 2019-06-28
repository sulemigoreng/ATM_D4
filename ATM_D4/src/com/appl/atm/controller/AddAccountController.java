/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Bisnis;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.MasaDepan;
import com.appl.atm.model.Siswa;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author mufqi
 */
public class AddAccountController extends TransactionController {
    BankDatabase bankDatabase;
    AddAccountViewController viewController;

    public AddAccountController(Keypad theKeypad, Screen theScreen, BankDatabase theBankDatabase) {
        super(theKeypad, theScreen);
        bankDatabase = theBankDatabase;
    }
    
    
    @Override
    public int run() {
        viewController = new AddAccountViewController();
        int accountType = getAccountType();
        int accountNumber = 0;
        boolean isUnique = false;
        
        while (!isUnique){
            boolean isExist;
            accountNumber = viewController.requestAccountNumber();
            isExist = bankDatabase.isUserExist(accountNumber);
            if (isExist) {
                viewController.displayMessageNotUnique();
            } else {
                isUnique = true;
            }
        }
        
        int pin = viewController.requestPinNumber();
        double balance = viewController.requestBalance(); 
        
        switch (accountType){
            case ADD_SISWA: {
                Siswa newSiswa = new Siswa(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newSiswa);
                break;
            }
            case ADD_BISNIS: {
                Bisnis newBisnis = new Bisnis(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newBisnis);
                break;
            }
            case ADD_MASADEPAN: {
                MasaDepan newMasaDepan = new MasaDepan(accountNumber, pin, balance, balance);
                bankDatabase.addAccount(newMasaDepan);
                break;
            }
        }
        return 0;
    }
    
    public int getAccountType(){
        return viewController.displayAddAccountMenu();
    }
}
