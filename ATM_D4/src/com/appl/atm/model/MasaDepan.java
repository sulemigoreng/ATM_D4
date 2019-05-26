package com.appl.atm.model;

public class MasaDepan extends Customer {
    private double maxWithdrawal;
    private double maxTransfer;
    private double maxDailyWithdrawal;
    private double maxDailyTransfer;

    public MasaDepan(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    @Override
    public double getMaxWithdrawal() {
        return maxWithdrawal;
    }

    public void setMaxWithdrawal(double maxWithdrawal) {
        this.maxWithdrawal = maxWithdrawal;
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

    public double getMaxDailyWithdrawal() {
        return maxDailyWithdrawal;
    }

    public void setMaxDailyWithdrawal(double maxDailyWithdrawal) {
        this.maxDailyWithdrawal = maxDailyWithdrawal;
    }

    public double getMaxDailyTransfer() {
        return maxDailyTransfer;
    }

    public void setMaxDailyTransfer(double maxDailyTransfer) {
        this.maxDailyTransfer = maxDailyTransfer;
    }
}