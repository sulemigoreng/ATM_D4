/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public class Siswa extends Customer {
    private static final double DAILY_WITHDRAWAL_LIMIT = 20;
    private static final double DAILY_TRANSFER_LIMIT = 0;
    
    public Siswa(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
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
    public boolean isSiswa(){
        return true;
    }
}
