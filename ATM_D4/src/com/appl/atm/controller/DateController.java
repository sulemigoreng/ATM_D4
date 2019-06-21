package com.appl.atm.controller;

import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.Screen;
import java.text.SimpleDateFormat;

public class DateController {
    private final BankDatabase bankDatabase;
    private final Screen screen;
    
    public DateController(BankDatabase theBankDatabase, Screen screen) {
        bankDatabase = theBankDatabase;
        this.screen = screen;
    }
    
    public void changeDate(){
        bankDatabase.addDate();
        screen.displayMessage("The Date Has Change.");
    }
    
    public void showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = simpleDateFormat.format(bankDatabase.getCalendar().getTime());
        
        screen.displayMessageLine("\nThe current date is : " + dateStr);
    }
}
