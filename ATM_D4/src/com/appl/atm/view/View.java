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
    
    public int inputRecipientAccountNumberV(){
        int accountNumber;
        screen.displayMessage("\nPlease enter the recipient's account number : ");
        accountNumber = keypad.getInput();
        return accountNumber;
    }
    
    public double inputTheAmountV(){
        double Amount;
        screen.displayMessage("\nPlease enter the amount (0 to cancel): ");
        Amount = keypad.getInput();
        return Amount;
    }
    
    public void displayMaxOneTimeLimitV(double limit){
        screen.displayMessage("\nThe maximum limit for 1x transfer is ");
        screen.displayDollarAmount(limit);
    }
    
    public void displayNotEnoughSaldo(){
        screen.displayMessage("\nThe balance is not sufficient.");
    }
    
  public void displayMaxOneDayLimitV(double limit){
        screen.displayMessage("\nThe maximum limit for one day transfer is ");
        screen.displayDollarAmount(1000);
        screen.displayMessage("\nThe amount of money you already transferred in one day : ");
        screen.displayDollarAmount(limit);
    }
    
  public void displayAccountNumber(int recipientAccountNumber){
      screen.displayMessageLine("\nThe recipient's account number : " + recipientAccountNumber);
  }
  
  public void displayAmount(double Amount){
      screen.displayMessage("The amount of money to be transferred in one day : ");
      screen.displayDollarAmount(Amount);
  }
  
  public String displayCorrect(){
      String answer;
      screen.displayMessageLine("\nAre these information correct? (Y/N)");
      answer = keypad.getStr();
      return answer;
  }
  
  public void displayTransferred(){
      screen.displayMessage("The money has been transferred.");
  }
  
  public void displayCancelTransfer(){
      screen.displayMessage("Canceling Transfer...");
  }
  
  public void displayNotCustomer(){
      screen.displayMessage("Only Customer allowed."); //perbaiki kalimatna hehe
  }
  
  public void displayAccountNotFound(){
      screen.displayMessage("Account not found."); // perbaiki
  }
  
  public void displayEnter(){
      screen.displayMessageLine("Please enter Y/N.");
  }
  
  public void displaySiswaCantTransfer(){
      screen.displayMessageLine("Siswa cannot transfer.");
  }
  
  public void displayCantTransferToOwnAccount(){
      screen.displayMessageLine("You cant transfer to your own account.");
  }
  
  public void displayExceedsOneTimeTransfer(){
      screen.displayMessageLine("The Amount of money is exceeding one time transfer limit.");
  }
}
    

