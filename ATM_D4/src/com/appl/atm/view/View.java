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
    
    /*-----Method-method Transfer View-----*/
    public int requestRecipientAccountNumber(){
        int accountNumber;
        screen.displayMessage("\nPlease enter the recipient's account number : ");
        accountNumber = keypad.getInput();
        return accountNumber;
    }
    
    public double requestTransferAmount(){
        double Amount;
        screen.displayMessage("\nPlease enter the amount that will be trasferred (0 to cancel): ");
        Amount = keypad.getInput();
        return Amount;
    }
    
    public void displayMaxOneTimeLimit(double limit){
        screen.displayMessage("\nThe maximum limit for 1x transfer is ");
        screen.displayDollarAmount(limit);
    }
    
    public void displayInsufficientFunds(){
        screen.displayMessage("\nInsufficient balance.");
    }
    
    public void displayMaxOneDayLimit(double limit){
        screen.displayMessage("\nThe maximum limit for one day transfer is ");
        screen.displayDollarAmount(1000);
        screen.displayMessage("\nThe amount of money you already transferred in one day : ");
        screen.displayDollarAmount(limit);
    }

    public void displayReciverAccountNumber(int recipientAccountNumber){
        screen.displayMessageLine("\nThe recipient's account number : " + recipientAccountNumber);
    }

    public void displayAmount(double Amount){
        screen.displayMessage("The amount of money to be transferred in one day : ");
        screen.displayDollarAmount(Amount);
    }

    public String displayWarningMessage(){
        String answer;
        screen.displayMessageLine("\nAre these information correct? (Y/N)");
        answer = keypad.getStr();
        return answer;
    }

    public void displayTransferSuccess(){
        screen.displayMessage("The money has been transferred.");
    }

    public void displayCancelTransferMessage(){
        screen.displayMessage("Canceling Transfer...");
    }

    public void displayNotCustomer(){
        screen.displayMessage("Only Customer is allowed."); //perbaiki kalimatna hehe
    }

    public void displayAccountNotFound(){
        screen.displayMessage("Account not found."); // perbaiki
    }

    public void displayEnterChoice(){
        screen.displayMessageLine("Please enter Y/N.");
    }

    public void displaySiswaNotAllowed(){
        screen.displayMessageLine("Siswa cannot transfer.");
    }

    public void displayCantTransferToOwnAccount(){
        screen.displayMessageLine("You can't transfer to your own account.");
    }

    public void displayExceedsOneTimeTransfer(){
        screen.displayMessageLine("The Amount of money is exceeding one time transfer limit.");
    }
    /*-----Akhir dari Method-method Transfer View-----*/
}
    

