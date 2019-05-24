/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import static com.appl.atm.model.Constants.*;

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
  public void displayMessageEnterCorrectly(){
       screen.displayMessageLine("Please enter the correct choice");
    }
  public String displayConfirmUnblock(){
      
        screen.displayMessage("Are you sure you want to unblock?(Y/N)");
        String inputChar = keypad.getStr();
        return inputChar;
  }
  public void displayMessageHasBeenUnblock(){
      screen.displayMessageLine("The Account has been unblocked.");
  }
  public void displayMessageCancelUnblock(){
      screen.displayMessage("Canceling unblock...");
  }
  public void displayMessageCorrectLetter(){
          screen.displayMessageLine("Please enter the correct letter.");
  }
  public int displayMenuAdmin(){
        boolean IsRight = false;
        int input;
        screen.displayMessageLine("\nAdmin Mode Menu:");
        screen.displayMessageLine("1 - Check cash in dispenser");
        screen.displayMessageLine("2 - Add cash to dispenser");
        screen.displayMessageLine("3 - Unblock an account number");
        screen.displayMessageLine("4 - Add new account");
        screen.displayMessageLine("5 - Change Date");
        screen.displayMessageLine("6 - Enable or Disable limit");
        screen.displayMessageLine("7 - Call it a day. Return all one day transfer limit to zero");
        screen.displayMessageLine("8 - Exit");
        while(!IsRight){
            screen.displayMessage("\nEnter your choice : ");
            input = keypad.getInput();
            switch(input){
                case 1: return input;
                case 2: return input;
                case 3: return input;
                case 4: return input;
                case 5: return input;
                case 6: return input;
                case 7: return input;
                case CANCELED: return CANCELED;
                default: displayMessageEnterCorrectly();
            }
        }
        return 0;
    }
    public int displayInputAccountNumberToUnblock(){
        screen.displayMessage("Please enter the account number which you want to unblock : ");
        int accountNumber = keypad.getInput();
        return accountNumber;
    }
    public int displayEnterAccountNumber(){
        screen.displayMessage("Please enter the account number : ");
        int accountNumber = keypad.getInput();
        return accountNumber;
    }
    public int displayEnterPin(){
        screen.displayMessage("\nPlease enter the PIN number : ");
        int pin = keypad.getInput();
        return pin;
    }
    public double displayEnterAvailableBalance(){
        screen.displayMessage("\nPlease enter the Available balance : ");
        double availableBalance = keypad.getInput();
        return availableBalance;
    }
    public double displayEnterTotalBalance(){
        screen.displayMessage("\nPlease enter the Total balance : ");
        double totalBalance = keypad.getInput();
        return totalBalance;
    }
}
    

