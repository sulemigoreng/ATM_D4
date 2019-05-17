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
    
    public void displayMaxOneTimeLimitV(int limit){
        screen.displayMessage("\nThe maximum limit for 1x transfer is ");
        screen.displayDollarAmount(limit);
    }
    
  public void displayMaxOneDayLimitV(double limit){
        screen.displayMessage("\nThe maximum limit for one day transfer is ");
        screen.displayDollarAmount(1000);
        screen.displayMessage("\nThe amount of money you already transferred in one day : ");
        screen.displayDollarAmount(limit);
    }
    
    public int processTransferV(int recipientAccountNumber, double Amount){
        String answer;
        boolean IsAnswerRight = false;
        screen.displayMessageLine("\nThe recipient's account number : " + recipientAccountNumber);
        screen.displayMessage("The amount of money to be transferred in one day : ");
        screen.displayDollarAmount(Amount);
        while(!IsAnswerRight) {
            screen.displayMessageLine("\nAre these information correct? (Y/N)");
            answer = keypad.getStr();
            if(answer.equals("Y")){
                screen.displayMessage("The money has been transferred.");
                return 1;
            }
            else { 
                if(answer.equals("N")){
                    screen.displayMessage("Canceling Transfer...");
                    return 2;
                }
                else {
                    screen.displayMessageLine("Please enter Y/N.");
                }
            }
        }
        return 0;
    }
}
    

