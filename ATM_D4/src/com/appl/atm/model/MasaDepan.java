package com.appl.atm.model;

public class MasaDepan extends Customer {
    private static double MAX_WITHDRAWAL = 100;
    private static double MAX_TRANSFER = 1000;

    public MasaDepan(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public double getMaxWithdrawal() {
        return MAX_WITHDRAWAL;
    }

    @Override
    public double getMaxTransfer() {
        return MAX_TRANSFER;
    }
    
    public void setMaxTransfer(int maxTransfer) {
        this.MAX_TRANSFER = maxTransfer;
    }

    @Override
    public boolean isMasaDepan(){
        return true;
    }
}