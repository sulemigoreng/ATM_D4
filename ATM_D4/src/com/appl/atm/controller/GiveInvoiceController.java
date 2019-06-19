package com.appl.atm.controller;

import com.appl.atm.model.Transaction;
import com.appl.atm.model.GiveInvoice;
import com.appl.atm.model.Customer;
import com.appl.atm.model.Payment;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Kelompok 1
 */
public class GiveInvoiceController extends TransactionController {
    
    Payment payment;
    Customer customer;
    private GiveInvoice Transaction;
    
    public GiveInvoiceController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        Transaction = (GiveInvoice) theTransaction;
    }

    @Override
    public int run() {
        int accountNumber;
        int id;
        int applicant;
        double amount;
        String description;
        Keypad keypad = getKeypad();
        Screen screen = getScreen();
        do {
            screen.displayMessage("Please enter Customer Account Number : ");
            accountNumber = keypad.getInput();
            if (accountNumber <= 0) screen.displayMessageLine("Customer Account Number invalid, please try again");
        } while (accountNumber <= 0);
        customer = Transaction.getCustomer(accountNumber);
        if (customer != null) {
            do {
                screen.displayMessage("Please enter Invoice ID Number : ");
                id = keypad.getInput();
                if (id <= 0) screen.displayMessageLine("Invoice ID invalid, please try again");
            } while (id <= 0);
            do {
                screen.displayMessage("Please enter Applicant Account Number : ");
                applicant = keypad.getInput();
                if (applicant < 0) screen.displayMessageLine("Applicant Account Number invalid, please try again");
            } while (applicant < 0);
            do {
                screen.displayMessage("Please enter Invoice Amount : ");
                amount = keypad.getInput();
                if (amount <= 0) screen.displayMessageLine("Invoice Amount invalid, please try again");
            } while (amount <= 0);
            screen.displayMessage("Please enter Invoice Description : ");
            keypad.getStr();
            description = keypad.getStr();
            customer.addInvoice(id, applicant, amount, description);
            
            BankStatementController bankStatement = new BankStatementController(keypad, screen, customer);
            bankStatement.addLog("Bill    ", 0.0, 0.0, "[" + description + "] | Invoice Id : [" 
                    + id + "] | Applicant : [" + applicant + "] | amount : [$ " + amount + "] | PaidOff : [" + false + "]");//menambahkan bankstatement ke akun customer berupa bill yang perlu dibayar

            screen.displayMessageLine("Invoice Added Succesfully to This Customer");
        } else {
            screen.displayMessageLine("That Customer Account Number is not available");
        }
     return 0;   
    }
}