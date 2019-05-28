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
    private static double MAX_WITHDRAWAL = 20;
    private static double MAX_TRANSFER = 200;
    private double maxWithdrawal;
    private double maxTransfer;
    
    public Siswa(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
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
    
    @Override
    public boolean isSiswa(){
        return true;
    }
    
    public double getmaxWithdrawal(){
        return maxWithdrawal;
    }
    
    public double getmaxTranser(){
        return maxTransfer;
    }
    
    public void setmaxTransfer(double maxTransfer){
        this.maxTransfer = maxTransfer;
    }
    
    public void setmaxWithdrawal(double maxWithdrawal){
        this.maxWithdrawal = maxWithdrawal;
    }
    
}
