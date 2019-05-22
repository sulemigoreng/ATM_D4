package com.appl.atm.model;

public class Bisnis extends Customer {
    private static double MAX_WITHDRAWAL = 2000;
    private static double MAX_TRANSFER   = 1000;

    public Bisnis(int theAccountNumber, int thePIN, int theAvailableBalance, int theTotalBalance) {
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
}