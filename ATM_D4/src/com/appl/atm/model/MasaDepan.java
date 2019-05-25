package com.appl.atm.model;

public class MasaDepan extends Customer {
    private static double MAX_WITHDRAWAL = 100;
    private double maxTransfer;

    public MasaDepan(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public double getMaxWithdrawal() {
        return MAX_WITHDRAWAL;
    }

    @Override
    public double getMaxTransfer() {
        return maxTransfer;
    }
    
    public void setMaxTransfer(int maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    @Override
    public boolean isMasaDepan(){
        return true;
    }
}