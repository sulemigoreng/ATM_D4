/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
/**
 *
 * @author asus
 */
public class cashDispenserController{
    private final static int CANCELED = 0;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    public cashDispenserController(Screen atmScreen, Keypad atmKeypad,
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
    
      
    }

    cashDispenserController() {
       
    }

    
    public int run(){
       
        boolean end = false;
        
        while(!end){
            int amount = getAmount();
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
    public int getAmount(){
      int amount;
      System.out.println("masukan jumlah uang untu ditambahkan ke cash dispenser");
      keypad = new Keypad();
      amount = keypad.getInput();
        return amount;
    }
}
