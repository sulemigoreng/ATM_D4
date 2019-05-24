/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appl.atm.controller;
import com.appl.atm.view.View;

/**
 *
 * @author USER
 */
public class AdminViewController {
    private View view = new View();
     
    public int processUnblock(){
        String inputChar;
        boolean isCorrect = false;
        while(!isCorrect) {
            inputChar = view.displayConfirmUnblock();
            if(inputChar.equals("Y")) {
                view.displayMessageHasBeenUnblock();
                return 0;
            }
            else {
                if(inputChar.equals("N")) {
                    view.displayMessageCancelUnblock();
                    return 1;
                }
                else {
                    view.displayMessageCorrectLetter();
                }
            }
        }
        return 2;
    }
    public int inputAccountNumberToUnblock(){
        int accountNumber;
        accountNumber = view.displayInputAccountNumberToUnblock();
        return accountNumber;
    }
    
     public int enterAccountNumber(){
        int accountNumber;
        accountNumber = view.displayEnterAccountNumber();
        return accountNumber;
    }
    
    public int enterPin(){
        int pin;
        pin = view.displayEnterPin();
        return pin;
    }
    
    public double enterAvailableBalance(){
        double availableBalance;
        availableBalance = view.displayEnterAvailableBalance();
        return availableBalance;
    }
    
    public double enterTotalBalance(){
        double totalBalance;
        totalBalance = view.displayEnterTotalBalance();
        return totalBalance;
    }
 
}
