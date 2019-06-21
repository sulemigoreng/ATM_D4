package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;

import com.appl.atm.model.Transaction;
import com.appl.atm.model.Invoice;
import com.appl.atm.model.Payment;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

public class PaymentController extends TransactionController {
    private final Payment transaction;

    private final Keypad keypad;
    private final Screen screen;
    
    public PaymentController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);

        transaction = (Payment)theTransaction;
        
        keypad = getKeypad();
        screen = getScreen();
    }

    private int getInvoiceId() {
        int invoiceId;

        do {
            screen.displayMessage("Please enter the invoice id (or 0 to cancel): ");
            invoiceId = keypad.getInput();
        } while (invoiceId < 0);
        
        return invoiceId;
    }
    
    private int getAmount() {
        int amount;

        do {
            screen.displayMessage("\nEnter amount: ");
            amount = keypad.getInput();
        } while (amount < 0);
        
        return amount;
    }
    
    private void showInvoiceList() {
        screen.displayMessageLine("\nInvoice List: ");
        screen.displayMessageLine("ID\t|Issuer\t|Amount\t\t\t|Description");
        for (Invoice invoice : transaction.getInvoiceList()) {
            screen.displayMessage(invoice.getId() + "\t|" + invoice.getApplicantAccountNumber()+ "\t|");
            screen.displayDollarAmount(invoice.getAmount());
            screen.displayMessageLine("\t\t|" + invoice.getDescription());
        }
        screen.displayMessage("\n");        
    }
    
    @Override
    public int run(){
        showInvoiceList();
        
        int invoiceId = getInvoiceId();
        if (invoiceId == 0) {
            return 0;
        }
        transaction.setinvoiceId(invoiceId);

        screen.displayMessageLine("\nPayment Menu: ");
        screen.displayMessageLine("1 - Paid off");
        screen.displayMessageLine("2 - Installment");
        screen.displayMessageLine("0 - Cancel\n");

        int selection;
        selection_loop:
        do {
            screen.displayMessage("Enter a choice: ");        
            selection = keypad.getInput();
    
            switch (selection) {
                case INSTALLMENT:
                    transaction.setAmount(getAmount());
                case PAID_OFF:
                    screen.displayMessage("\n");
                case CANCEL:
                    break selection_loop;
            }
        } while (true);
        
        switch (transaction.execute()) {
            case PAYMENT_SUCCESS:
                break;
            case PAYMENT_INVOICE_NOTFOUND:
                screen.displayMessageLine("Couldn't find the invoice with id " + invoiceId + ".\n");
                break;
            case PAYMENT_INSUFICIENT_AMOUNT:
                screen.displayMessageLine("Insufficient amount of available balance.\n");
                break;
            case PAYMENT_INVALID_AMOUNT:
                screen.displayMessageLine("Unable to process your transaction, try again.");
                break;
            default:
                // not implemented
                break;
        }
        
        return 0;
    }
}
