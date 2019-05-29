/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
/**
 *
 * @author asus
 */
public class AddDispenserController extends TransactionController {
    private final static int CANCELED = 0;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    public AddDispenserController(CashDispenser cashDispenser, Keypad keypad,
            Screen screen) {
        super(keypad, screen);

        this.cashDispenser = cashDispenser;
    }

    public int run(){

        boolean end = false;
        keypad = getKeypad();
        while(!end){
           int amount;
            System.out.println("masukan jumlah uang untuk ditambahkan ke cash dispenser");

            amount = keypad.getInput();
            if(amount==CANCELED){ //if amount =0, cancelled

                end=true;
            } else {
                if(amount>0){
                    cashDispenser.AddCashDispenser(amount);
                    end=true;
                }
            }
        }
        return 0;
    }
}
