package com.appl.atm.model;

public class MasaDepan extends Customer {
    private static double MAX_WITHDRAWAL = 100;
    private static double MAX_TRANSFER = 500;

    public MasaDepan(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public double getMaxWithdrawal() {
        return MAX_WITHDRAWAL;
    }

    public void setMaxWithdrawal(double maxWithdrawal) {
        this.MAX_WITHDRAWAL = maxWithdrawal;
    }

    @Override
    public double getMaxTransfer() {
        return MAX_TRANSFER;
    }

    @Override
    public boolean isMasaDepan(){
        return true;
    }
    
    public void setMaxTransfer(int maxTransfer) {
        this.MAX_TRANSFER = maxTransfer;
    }
}