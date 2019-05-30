/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.Screen;

public class DateController {
    BankDatabase bankDatabase;
    
    public DateController(BankDatabase theBankDatabase) {
        bankDatabase = theBankDatabase;
    }
    
    public void changeDate(){
        Screen screen = new Screen();
        bankDatabase.addDate();
        screen.displayMessage("The Date Has Change.");
    }
    
    public void showDate() {
       System.out.println("\nThe current date is : " + bankDatabase.showDate());
    }
}
