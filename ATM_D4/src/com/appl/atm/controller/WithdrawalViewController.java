/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
/**
 *
 * @author Ananda Bayu
 */
public class WithdrawalViewController {
    private Keypad keypad;
    private Screen screen;
    
    public WithdrawalViewController(Screen theScreen, Keypad theKeypad){
        keypad = theKeypad;
        screen = theScreen;
    }
    
    public int displayMenuOfAmounts() {
	int userChoice = -1; // local variable to store return value

	// array of amounts to correspond to menu numbers
	int[] amounts = {0, 20, 40, 60, 100, 200};

	// loop while no valid choice has been made
	while (userChoice == -1) {
	    // display the withdrawal menu
	    screen.displayMessageLine("\nWithdrawal Menu:");
	    for (int i = 0; i < amounts.length - 1; i++) {
		screen.displayMessageLine((i + 1) + " - $" + amounts[i + 1]);
	    }
            screen.displayMessageLine(amounts.length + " - Other Option");
	    screen.displayMessageLine((amounts.length + 1) + " - Cancel transaction");
	    screen.displayMessage("\nChoose a withdrawal amount: ");

	    int input = keypad.getInput(); // get user input through keypad

	    // determine how to proceed based on the input value
	    switch (input) {
		case 1: // if the user chose a withdrawal amount 
		case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
		case 3: // corresponding amount from amounts array
		case 4:
		case 5:
		    userChoice = amounts[input]; // save user's choice
		    break;
                case 6:
                    do{
                        screen.displayMessageLine("Please insert amount that you want(in $20 pieces): ");
                        input = keypad.getInput(); // get user input through keypad
                        if (input % 20 == 0){
                            userChoice = input;
                        }
                        else{
                            screen.displayMessageLine("Sorry, you just can withdrawal in $20 pieces");
                        }
                    }
                    while(input % 20 != 0);
                    break;
		case WITHDRAWAL_CANCELED: // the user chose to cancel
		    userChoice = 0; // save user's choice
		    screen.displayMessageLine("Canceling transaction...");
		    break;
		default: // the user did not enter a value from 1-6
		    screen.displayMessageLine(
			    "Invalid selection. Try again.");
	    }
	}

	return userChoice; // return withdrawal amount or CANCELED
    }
    
    public void withdrawalSuccessful(){
        screen.displayMessageLine("Your cash has been dispensed. Please take your cash now.");
    }
    
    public void balanceNotEnough(){
        screen.displayMessageLine("Your balance isn't enough for this withdrawal.");
    }
    
    public void cashdispenserNotEnough(){
        screen.displayMessageLine("Cash dispenser doesn't have sufficient amount of cash.");
    }
    
    public void maxWithdrawal(double amount){
        screen.displayMessage("the nominal you choose exceeds the cash withdrawal limit of ");
        screen.displayDollarAmount(amount);
    }
}
