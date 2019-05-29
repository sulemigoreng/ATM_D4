/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Calendar;
import javafx.util.Pair;
import java.util.Comparator;
import java.util.Iterator;

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
   private TreeSet<Invoice> invoiceList;
   private TreeSet<Pair<Calendar, Double>> withdrawalLog;
   private TreeSet<Pair<Calendar, Double>> transferLog;
   private ArrayList<String> transaksiLog;

	enum ETransactionKind {
		WITHDRAWAL,
		TRANSFER
	}

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
      setPin(thePIN);
      invoiceList = new TreeSet<Invoice>(new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getID() - o2.getID();
			}
	  });
      Comparator<Pair<Calendar, Double>> calendarComparator = new Comparator<Pair<Calendar, Double>>(){
          @Override
          public int compare(Pair<Calendar, Double> t, Pair<Calendar, Double> t1) {
              return t.getKey().compareTo(t1.getKey());
          }
          
      };
      withdrawalLog = new TreeSet<Pair<Calendar, Double>>(calendarComparator);
      transferLog = new TreeSet<Pair<Calendar, Double>>(calendarComparator);
   }
   
   public Customer (int theAccountNumber, double theBalance) {
      pinLog = new ArrayList<Integer>();
      transaksiLog = new ArrayList<String>();
      accountNumber = theAccountNumber;
      setPin(0);
      availableBalance = theBalance;
      totalBalance = theBalance;
      tryCount = 0;
      invoiceList = new TreeSet<Invoice>();
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
    
    public abstract double getDailyWithdrawalLimit();
    public abstract double getDailyTransferLimit();

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

	public boolean addInvoice(int id, int applicant, double amount, String description) {
    	return invoiceList.add(new Invoice(id, description, amount, applicant));
	}

    public void deleteInvoice(int id) {
      for (Invoice payment : invoiceList) {
         if (payment.getID() == id) {
            invoiceList.remove(payment);
         }
      }
    }

    public TreeSet<Invoice> getInvoiceList() {
       return invoiceList;
    }

    public Invoice getInvoce(int id) {
      for (Invoice invoice : invoiceList) {
         if (invoice.getID() == id) {
            return invoice;
         }
      }
      return null;
    }
    
    public boolean insertWithdrawalLog(Calendar calendar, double amount){
    	if (amount + getSameDayTransactionAmount(ETransactionKind.WITHDRAWAL, calendar) > getDailyWithdrawalLimit()) {
            return false;
        }
        withdrawalLog.add(new Pair(calendar, amount));       
        return true;
    }
    
    public boolean insertTransferLog(Calendar calendar, double amount){
    	if (amount + getSameDayTransactionAmount(ETransactionKind.TRANSFER, calendar) > getDailyTransferLimit()) {
            return false;
	}
	return transferLog.add(new Pair(calendar, amount));
//        return true;
    }

	public double getSameDayTransactionAmount(ETransactionKind transactionKind, Calendar findDate) {
            double amount = 0.0;

		int findYear = findDate.get(Calendar.YEAR);
		int findDayOfYear = findDate.get(Calendar.DAY_OF_YEAR);
		
		Pair<Calendar, Double> currentLog = null;
		
		Iterator<Pair<Calendar, Double>> itLog;
		switch (transactionKind) {
                    case WITHDRAWAL:
                        itLog = withdrawalLog.descendingIterator();		
                    break;
                    case TRANSFER:
                        itLog = transferLog.descendingIterator();
			break;
                    default:
                    // undefined behaviour
                    return 0.0;
		}
                
		while (itLog.hasNext()) {
                    currentLog = itLog.next(); 
                    if (currentLog.getKey().get(Calendar.YEAR) > findYear) {
                        continue;
                    } do {
                        if (currentLog.getKey().get(Calendar.DAY_OF_YEAR) < findDayOfYear) {
                            break;
                        }
                        amount += currentLog.getValue();
                        if (itLog.hasNext()) {
                            currentLog = itLog.next();                            
                            continue;
                        }
                        break;
                    } while (true);
                    break;
		}
		
		return amount;
	}
}
