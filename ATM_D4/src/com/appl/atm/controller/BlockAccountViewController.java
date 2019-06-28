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
 * @author mufqi
 */
public class BlockAccountViewController {
    private Screen screen;
    private Keypad keypad;

    public BlockAccountViewController() {
        this.screen = new Screen();
        this.keypad = new Keypad();
    }
    public void displayUserDoesntExist() {
        screen.displayMessageLine("\nUser doesn't exist!");
        screen.displayMessageLine("Please enter a correct account number!");
    }
    
    public int requestAccountNumber() {
        screen.displayMessage("\nEnter the account number to block: ");
        return keypad.getInput();
    }
}
