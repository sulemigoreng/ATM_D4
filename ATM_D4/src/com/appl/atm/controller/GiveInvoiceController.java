package com.appl.atm.controller;

import com.appl.atm.model.Transaction;
import com.appl.atm.model.GiveInvoice;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import static com.appl.atm.model.Constants.*;

public class GiveInvoiceController extends TransactionController {
    private final GiveInvoice transaction;

    private final Keypad keypad;
    private final Screen screen;
        
    public GiveInvoiceController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        
        transaction = (GiveInvoice) theTransaction;
        
        screen = getScreen();
        keypad = getKeypad();
    }

    @Override
    public int run() {
        transaction.setCustomerAccountNumber(getCustomerAccountNumber());
        transaction.setInvoiceId(getInvoiceId());
        transaction.setApplicantAccountNumber(getApplicantAccountNumber());
        transaction.setInvoiceAmount(getInvoiceAmount());
        transaction.setInvoiceDescription(getInvoiceDescription());
        switch(transaction.execute()) {
            case ADD_INVOICE_SUCCESS:
                screen.displayMessageLine("Invoice Succesfully Added to This Customer");
                break;
            case CUSTOMER_NOT_EXIST:
                screen.displayMessageLine("That Customer Account Number is not available");
                break;
            case ADD_INVOICE_FAILED:
                screen.displayMessageLine("Failed to Add Invoice to This Customer.");
                break;
        }       

        return 0;   
    }
    
    private int getCustomerAccountNumber() {
        int customerAccountNumber;
        
        // keep asking for account number until valid value received
        do {
            screen.displayMessage("Please enter Customer Account Number : ");
            customerAccountNumber = keypad.getInput();
            if (customerAccountNumber > 0) {
                break;
            }
            screen.displayMessageLine("Customer Account Number invalid, please try again");
        } while (true);
        
        return customerAccountNumber;
    }
    
    private int getInvoiceId() {
        int invoiceId;
        
        // keep asking for invoice id until valid value received
        do {
            screen.displayMessage("Please enter Invoice ID Number : ");
            invoiceId = keypad.getInput();
            if (invoiceId > 0) {
                break;
            }
            screen.displayMessageLine("Invoice ID invalid, please try again");
        } while (true);
        
        return invoiceId;
    }
    
    private int getApplicantAccountNumber() {
        int applicantAccountNumber;
        
        // keep asking for applicant number until valid value received
        do {
            screen.displayMessage("Please enter Applicant Account Number : ");
            applicantAccountNumber = keypad.getInput();
            if (applicantAccountNumber > 0) {
                break;
            }
            screen.displayMessageLine("Applicant Account Number invalid, please try again");
        } while (true);
        
        return applicantAccountNumber;
    }
    
    private double getInvoiceAmount() {
        double amount; 
        
        // keep asking for amount until valid value received
        do {
            screen.displayMessage("Please enter Invoice Amount : ");
            amount = keypad.getInput();
            if (amount > 0) {
                break;
            }
            screen.displayMessageLine("Applicant Account Number invalid, please try again");
        } while (true);
        
        return amount;
    }
    
    private String getInvoiceDescription() {
        String description;
        screen.displayMessage("Please enter Invoice Description : ");
        keypad.getStr();
        description = keypad.getStr();
        
        return description;
    }
}