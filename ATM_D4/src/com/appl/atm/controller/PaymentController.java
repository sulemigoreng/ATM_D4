package com.appl.atm.controller;

import com.appl.atm.model.Transaction;
import com.appl.atm.model.Invoice;
import com.appl.atm.model.Payment;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

public class PaymentController extends TransactionController {
    private static final int PAID_OFF    = 1;
    private static final int INSTALLMENT = 2;
    private static final int CANCEL      = 0;

    private Payment transaction;
    
    public PaymentController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        transaction = (Payment)theTransaction;
    }
    
    @Override
    public int run(){
        Keypad keypad = getKeypad();
        Screen screen = getScreen();

        screen.displayMessageLine("\nInvoice List: ");
        screen.displayMessageLine("ID\t|Issuer\t|Amount\t\t|Desc");
        for (Invoice invoice : transaction.getInvoiceList()) {
            screen.displayMessage(invoice.getID() + "\t|" + invoice.getApplicantAccNum() + "\t|");
            screen.displayDollarAmount(invoice.getBillNominal());
            screen.displayMessageLine("\t\t|" + invoice.getBillInformation());
        }
        screen.displayMessage("\n");

        int invoiceid;
        do {
            screen.displayMessage("Please enter the invoice id (or 0 to cancel): ");
            invoiceid = keypad.getInput();
            if (invoiceid  == 0) {
                return 0;
            } else if (invoiceid > 0) {
                break;
            }
        } while (true);
        transaction.setinvoiceID(invoiceid);

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
                case CANCEL:
                    break selection_loop;
                case INSTALLMENT:       
                    screen.displayMessage("\nEnter Nominal: ");
                    transaction.setAmount(keypad.getInput());
                case PAID_OFF:
                    screen.displayMessage("\n");
                    switch (transaction.execute()) {
                        case 0:
                            break;
                        case 1:
                            screen.displayMessageLine("Couldn't find the invoice with id " + invoiceid + ".\n");
                            break;
                        case 2:
                            screen.displayMessageLine("Insufficient amount of available balance.\n");
                            break;
                        case 3:
                            screen.displayMessageLine("Unable to process your transaction, try again.");
                            break;
                        default:
                            // unimplemented
                            break;
                    }
                    break selection_loop;
                default:
                    continue selection_loop;
            }
            // Should never been reached
        } while (true);
        
        return 0;
    }
}
