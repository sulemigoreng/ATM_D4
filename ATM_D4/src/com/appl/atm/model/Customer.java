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
	  invoiceList = new TreeSet<>((Invoice o1, Invoice o2) -> 
                  o1.getId() - o2.getId());
          
	  Comparator<Pair<Calendar, Double>> calendarComparator = 
                  (Pair<Calendar, Double> t, Pair<Calendar, Double> t1) -> 
                          t.getKey().compareTo(t1.getKey());
	  
          withdrawalLog = new TreeSet<>(calendarComparator);
	  transferLog = new TreeSet<>(calendarComparator);
   }
   
   public Customer (int theAccountNumber, double theBalance) {
	  pinLog = new ArrayList<Integer>();
	  transaksiLog = new ArrayList<String>();
	  accountNumber = theAccountNumber;
	  setPin(0);
	  availableBalance = theBalance;
	  totalBalance = theBalance;
	  tryCount = 0;
	  invoiceList = new TreeSet<>();
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
        
    public boolean addInvoice(int id, int applicantAccountNumber, double amount, String description) {
        return invoiceList.add(new Invoice(id, applicantAccountNumber, amount, description));
    }

    public void deleteInvoice(int id) {
        for (Invoice payment : invoiceList) {
            if (payment.getId() == id) {
                invoiceList.remove(payment);
                break;
            }
        }
    }

    public TreeSet<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public Invoice getInvoce(int id) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getId() == id) {
                return invoice;
            }
        }
        return null;
    }
		
    public boolean insertWithdrawalLog(Calendar calendar, double amount){
        if (isDailyWithdrawalLimitReached(calendar, amount)) {
            return false;
        }
	return withdrawalLog.add(new Pair(calendar, amount));       
    }

    public boolean insertTransferLog(Calendar calendar, double amount){
        if (isDailyTransferLimitReached(calendar, amount)) {
            return false;
        }
        return transferLog.add(new Pair(calendar, amount));
    }

    public boolean isDailyWithdrawalLimitReached(Calendar calendar, double amount) {
        return isDailyTransactionLimitReached(withdrawalLog, calendar, amount);
    }

    public boolean isDailyTransferLimitReached(Calendar calendar, double amount) {
        return isDailyTransactionLimitReached(transferLog, calendar, amount);
    }
    
    private boolean isDailyTransactionLimitReached(TreeSet<Pair<Calendar, Double>> log, Calendar calendar, double amount) {
        return ((amount + getSameDayTransactionAmount(log, calendar)) > getDailyWithdrawalLimit());
    }

    public double getSameDayTransactionAmount(TreeSet<Pair<Calendar, Double>> log, Calendar findDate) {
	double amount = 0.0;
		
	int findYear = findDate.get(Calendar.YEAR);
	int findDayOfYear = findDate.get(Calendar.DAY_OF_YEAR);

	Pair<Calendar, Double> currentLog;
	Iterator<Pair<Calendar, Double>> logIt = log.descendingIterator();
        while (logIt.hasNext()) {
            currentLog = logIt.next();
            if (findYear < currentLog.getKey().get(Calendar.YEAR)) {
                continue;
            } do {
                if (findDayOfYear > currentLog.getKey().get(Calendar.DAY_OF_YEAR)) {
                    break;
		}		
		amount += currentLog.getValue();
		if (logIt.hasNext()) {
                    currentLog = logIt.next();                            
                    continue;
                }
		break;
            } while (true);
            break;
	}
        
        return amount;
    }
    
    public boolean payInvoice (Invoice invoice, double amount) {
        double thisAmount = invoice.getAmount();
        if (amount <= thisAmount && amount <= availableBalance) {
            debit(amount);
            thisAmount -= amount;
            invoice.setAmount(thisAmount);
            boolean paidOff = (invoice.getAmount() == 0);
            //menambahkan bankstatement kepada customer setelah melakukan payment
            transaksiLog.add("[DATE]\tPayment  \t$ " + String.valueOf(amount) + "\t$ 0.0\t\t$ " 
                    + availableBalance + "\t\t $" + totalBalance + 
                    "\t\t[" + String.valueOf(invoice.getDescription()) +"] | Invoice Id : [" + String.valueOf(invoice.getId())
                    + "] | Applicant : [" + String.valueOf(invoice.getApplicantAccountNumber()) + "] | Amount : [$ " 
                    + String.valueOf(invoice.getAmount()) + "] | Paid Off : [" + paidOff + "]");

            if(invoice.getAmount() == 0) {
                invoiceList.remove(invoice);
            }
            return true;
        }
        return false;
    }
}
