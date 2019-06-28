/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author dewan
 */
public class AddAccountViewController {
    private Screen screen;
    private Keypad keypad;

    public AddAccountViewController() {
        this.screen = new Screen();
        this.keypad = new Keypad();
    }

    public int requestAccountNumber() {
        screen.displayMessage("\nPlease enter new account's account number: ");     
        return keypad.getInput();
    }

    public int requestPinNumber() {
        screen.displayMessage("\nPlease enter new account's pin number: ");     
        return keypad.getInput();
    }

    public double requestBalance() {
        screen.displayMessage("\nPlease enter new account's balance: ");     
        return keypad.getInput();
    }

    public void displayMessageNotUnique() {
        screen.displayMessageLine("\nAccount number already exist!");
        screen.displayMessageLine("Please input a unique account number");
    }
    
    public int displayAddAccountMenu() {
        screen.displayMessageLine("\nChoose account type:");
        screen.displayMessageLine("1 - Bisnis");
	screen.displayMessageLine("2 - Siswa");
        screen.displayMessageLine("3 - Masa Depan\n");
        screen.displayMessageLine("4 - Exit\n");
	screen.displayMessage("Enter a choice: ");
	return keypad.getInput(); // return admin's selection
    }

    
    
    
}
