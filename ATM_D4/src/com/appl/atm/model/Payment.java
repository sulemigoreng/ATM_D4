package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;

import java.util.TreeSet;

public class Payment extends Transaction {
    private final Customer customer;
    private int invoiceId;
    private double amount;

    public Payment(int userAccountNumber, BankDatabase atmBankDatabase) {
        // initialize superclass variables
        super(userAccountNumber, atmBankDatabase);

        customer = getBankDatabase().getCustomer(getAccountNumber());
        amount = 0;
    }

    @Override
    public int execute() {
        Invoice invoice = customer.getInvoce(getinvoiceId());
        if (invoice == null) {
            return PAYMENT_INVOICE_NOTFOUND;
        }

        if (getAmount() > invoice.getAmount()) {
            return PAYMENT_INVALID_AMOUNT;
        }

        /* threat negative or zero amount as paid off */
        if (getAmount() <= 0) {
            setAmount(invoice.getAmount());
        }

        if (customer.getAvailableBalance() >= getAmount()) {
            customer.debit(getAmount());
            invoice.pay(getAmount());
            boolean paidOff = (invoice.getAmount() == 0);
            //menambahkan bankstatement kepada customer setelah melakukan payment
            customer.getTransaksiLog().add("[DATE]\tPayment  \t$ " + getAmount() + "\t$ 0.0\t\t$ " 
                    + customer.getAvailableBalance() + "\t\t $" + customer.getTotalBalance() + 
                    "\t\t[" + String.valueOf(invoice.getDescription()) +"] | Invoice Id : [" + String.valueOf(invoice.getId())
                    + "] | Applicant : [" + String.valueOf(invoice.getApplicantAccountNumber()) + "] | Amount : [$ " 
                    + String.valueOf(getAmount()) + "] | Paid Off : [" + paidOff + "]");
            
            if(invoice.getAmount() == 0) {
                customer.getInvoiceList().remove(invoice);
            }
        } else {
            return PAYMENT_INSUFICIENT_AMOUNT;
        }

        return PAYMENT_SUCCESS;
    }

    public int getinvoiceId() {
        return invoiceId;
    }

    public void setinvoiceId(int id) {
        this.invoiceId = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public TreeSet<Invoice> getInvoiceList() {
        return new TreeSet<Invoice>(customer.getInvoiceList());
    }
}