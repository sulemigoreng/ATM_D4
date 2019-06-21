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

        if (customer.payInvoice(invoice, amount) == false) {
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