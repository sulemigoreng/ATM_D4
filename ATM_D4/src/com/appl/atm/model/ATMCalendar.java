package com.appl.atm.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ATMCalendar {
    private final Calendar calendar;
    private long startMillisecond;
    
    public ATMCalendar() {
        calendar = new GregorianCalendar();
        startMillisecond = System.currentTimeMillis();
    }
    
    private void refresh() {
        try {
            Thread.sleep(1);
            
            // renew startMillisecond
            long endMillisecond = startMillisecond;
            startMillisecond = System.currentTimeMillis();

            // renew calendar date to latest machine date
            calendar.setTimeInMillis(calendar.getTimeInMillis() + (startMillisecond - endMillisecond));                    
        } catch(InterruptedException e) {
            // TODO
        }
    }
    
    public Calendar getCalendar() {
        refresh();
        return (Calendar)calendar.clone();
    }
    
    public void addDate(){
        refresh();
        calendar.add(Calendar.DATE, 1);
    }
}
