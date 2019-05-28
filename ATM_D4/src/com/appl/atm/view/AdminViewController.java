/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

/**
 *
 * @author dewan
 */
public class AdminViewController {
    private Screen screen;
    private Keypad keypad;

    public AdminViewController() {
        this.screen = new Screen();
        this.keypad = new Keypad();
    }

    public int reqAccountNumber() {
        screen.displayMessage("\nPlease enter new account's account number: ");     
        return keypad.getInput();
    }

    public int reqPinNumber() {
        screen.displayMessage("\nPlease enter new account's pin number: ");     
        return keypad.getInput();
    }

    public double reqBalance() {
        screen.displayMessage("\nPlease enter new account's balance: ");     
        return keypad.getInput();
    }

    public void showMessageNotUnique() {
        screen.displayMessageLine("\nUser account number already exist!");
        screen.displayMessageLine("Please input a unique account number");
    }

    public void displayUserDoesntExist() {
        screen.displayMessageLine("\nUser doesn't exist!");
        screen.displayMessageLine("Please enter a correct account number!");
    }
    
    
}
