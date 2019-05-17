/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

/**
 *
 * @author IchaCahyaWulan
 */
public class View {
    Screen screen = new Screen();
    Keypad keypad = new Keypad();
    
    public String inputRecipientAccountNumberV(){
        String accountNumber;
        screen.displayMessage("\nPlease enter the recipient's account number : ");
        accountNumber = keypad.getStr();
        return accountNumber;
    }
    
    public double inputTheAmountV(){
        double Amount;
        screen.displayMessage("\nPlease enter the amount (0 to cancel): ");
        Amount = keypad.getInput();
        return Amount;
    }
    
    public void displayMaxOneTimeLimitV(int limit){
        screen.displayMessage("\nThe maximum limit for 1x transfer is ");
        screen.displayDollarAmount(limit);
    }
    
    
}
