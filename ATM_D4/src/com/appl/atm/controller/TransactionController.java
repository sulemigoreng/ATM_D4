/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Annazar
 */
public abstract class TransactionController {

    private Keypad keypad;
    private Screen screen;

    public TransactionController(Keypad theKeypad, Screen theScreen) {
	keypad = theKeypad;
	screen = theScreen;
    }

    public abstract int run();

    /**
     * @return the keypad
     */
    public Keypad getKeypad() {
	return keypad;
    }

    /**
     * @return the screen
     */
    public Screen getScreen() {
	return screen;
    }

}
