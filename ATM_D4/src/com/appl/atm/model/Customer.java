/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import java.util.ArrayList;

/**
 *
 * @author Rayhan Azka  <rayhan.azka.tif418@polban.ac.id>
 */
public abstract class Customer implements IAccount, Comparable<Customer> {   
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   private int tryCount;
   private ArrayList<Integer> pinLog;
   private double dailyWithdrawal[];
   private ArrayList<String> transaksiLog;

   // Account constructor initializes attributes
   public Customer (int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance) {
      pinLog = new ArrayList<Integer>();
      pinLog.add(new Integer(thePIN));
      accountNumber = theAccountNumber;
      transaksiLog = new ArrayList<String>();

      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
      tryCount = 0;
      dailyWithdrawal = new double[31];
      setPin(thePIN);
   }

   public Customer (int theAccountNumber, double theBalance) {
      pinLog = new ArrayList<Integer>();
      transaksiLog = new ArrayList<String>();
      accountNumber = theAccountNumber;
      setPin(0);
      availableBalance = theBalance;
      totalBalance = theBalance;
      tryCount = 0;
      dailyWithdrawal = new double[31];
   }
   
   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   }
   
   @Override
   public int getAccountNumber() {
      return accountNumber;  
   }

   public ArrayList<Integer> getPinLog() {
      return pinLog;
   }
   
   public ArrayList<String> getTransaksiLog() {
      return transaksiLog;
   }
   
   public void setPin(int newPin) {
      this.pin = newPin;
   }

   @Override
   public int getPin() {
      return pin;
   }

   public boolean isResetRequired() {
      return (pin == 0);
   }

   public void setResetRequired() {
      pin = 0;
   }
   
   public void setTotalBalance(double totalBalance) {
      this.totalBalance = totalBalance;
   }
   
   public void setAvailableBalance(double availableBalance) {
      this.availableBalance = availableBalance;
   }
   
   public int getTryCount() {
      return tryCount;
   }
    
   public void setTryCount(int tryCount) {
      this.tryCount = tryCount;
   }
   
   public double[] getDailyWithdrawal() {
      return dailyWithdrawal;
   }

   public void setDailyWithdrawal(double[] dailyWithdrawal) {
      this.dailyWithdrawal = dailyWithdrawal;
   }
   
   public boolean isBlocked () {
       return (tryCount >= 3);
   }
   
   public void unblock() {
      tryCount = 0;
   }

    @Override
    public int compareTo(Customer t) {
        return accountNumber - t.accountNumber;
    }

    @Override
    public boolean isCustomer() {
       return true;
    }
    
    public abstract double getMaxWithdrawal();
    public abstract double getMaxTransfer();

    public void credit(double amount) {
      try{
         totalBalance += amount;
      } catch(Exception e){
            
      }
    }

    public void debit(double amount) {
      availableBalance -= amount;
      totalBalance -= amount;
    }
    
    public boolean isSiswa(){
        return false;
    }
    
    public boolean isBisnis(){
        return false;
    }
    
    public boolean isMasaDepan(){
        return false;
    }
}