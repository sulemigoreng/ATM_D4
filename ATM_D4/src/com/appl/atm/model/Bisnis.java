package com.appl.atm.model;

public class Bisnis extends Customer {
    private static final double DAILY_WITHDRAWAL_LIMIT = 10000;
    private static final double DAILY_TRANSFER_LIMIT =  10000;

    public Bisnis(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public double getDailyWithdrawalLimit() {
        return DAILY_WITHDRAWAL_LIMIT;
    }

    @Override
    public double getDailyTransferLimit() {
        return DAILY_TRANSFER_LIMIT;
    }
    
    @Override
    public boolean isBisnis(){
        return true;
    }
}