package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;

import java.util.Iterator;
import java.util.TreeSet;

public class Payment extends Transaction {
    private Customer customer;
    private double amount;
    private int invoiceID;

    public Payment(int userAccountNumber, BankDatabase atmBankDatabase) {
        // initialize superclass variables
        super(userAccountNumber, atmBankDatabase);
        customer = getBankDatabase().getCustomer(getAccountNumber());

        amount = 0;
    }

    @Override
    public int execute() {
        Invoice invoice = customer.getInvoce(getinvoiceID());
        if (invoice == null) {
            return 1;
        }

        if (getAmount() > invoice.getBillNominal()) {
            return 3;
        }

        if (getAmount() <= 0) {
            setAmount(invoice.getBillNominal());
        }

        if (customer.getAvailableBalance() >= getAmount()) {
            customer.debit(getAmount());
            invoice.reduceNominal(getAmount());
            boolean paidOff = (invoice.getBillNominal() == 0);
            customer.getTransaksiLog().add("[DATE] Payment "+String.valueOf(invoice.getBillInformation())+" | Invoice Id : "
                    +String.valueOf(invoice.getID())+"   to Applicant : "+String.valueOf(invoice.getApplicantAccNum())
                    +"   with amount $ "+String.valueOf(getAmount())+" of "+String.valueOf(invoice.getBillNominal()+getAmount())+"   Paid off : "+String.valueOf(paidOff));
            
            if(invoice.getBillNominal() == 0) {
                customer.getInvoiceList().remove(invoice);
            }
        } else {
            return 2;
        }

        return 0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getinvoiceID() {
        return invoiceID;
    }

    
    public TreeSet<Invoice> getInvoiceList() {
        return new TreeSet<Invoice>(customer.getInvoiceList());
    }
    
    public void setinvoiceID(int id) {
        this.invoiceID = id;
    }
}
