package com.appl.atm.model;

public class MasaDepan extends Customer {
    private double dailyWithdrawalLimit;
    private double dailyTransferLimit = 500;

    public MasaDepan(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public boolean isMasaDepan(){
        return true;
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(double limit) {
        this.dailyWithdrawalLimit = limit;
    }

    public double getDailyTransferLimit() {
        return dailyTransferLimit;
    }

    public void setDailyTransferLimit(double limit) {
        this.dailyTransferLimit = limit;
    }
}