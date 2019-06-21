/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Admin;
import com.appl.atm.model.BalanceInquiry;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.Payment;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Withdrawal;
import com.appl.atm.model.Transfer;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.GiveInvoice;
import com.appl.atm.controller.DateController;

/**
 *
 * @author Annazar
 */
public class ATM {
    private int userAuthenticated;
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot;
    private Deposit deposit;
    private BankDatabase bankDatabase; // account information database
    private DateController dateController;
//    private Admin theAdmin;


    public ATM() {
	userAuthenticated = 0;
	currentAccountNumber = 0;
	screen = new Screen();
	keypad = new Keypad();
	cashDispenser = new CashDispenser();
	depositSlot = new DepositSlot();
	bankDatabase = new BankDatabase();
        dateController = new DateController(bankDatabase, screen);
//        theAdmin = new Admin(0000, 0000);
    }

    // start ATM
    public void run() {
	// welcome and authenticate user; perform transactions
	while (true) {
	    // loop while user is not yet authenticated
	    screen.displayMessageLine("\nWelcome!\n");
	    while (userAuthenticated != 1) {
		authenticateUser(); // authenticate user
	    }

	    //if(currentAccountNumber == theAdmin.getAccountNumber()) {
        //        adminMode();
        //    } else {
        //        performTransactions(); // user is now authenticated
        //    }
            performTransactions(); // user is now authenticated

	    userAuthenticated = 0; // reset before next ATM session
	    currentAccountNumber = 0; // reset before next ATM session
	    screen.displayMessageLine("\nThank you! Goodbye!");
	}
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
	    screen.displayMessage("Please enter your account number\t: ");
	    int accountNumber = keypad.getInput(); // input account number

            if (!bankDatabase.isUserBlocked(accountNumber)){
                screen.displayMessage("Enter your PIN\t\t\t\t: "); // prompt for PIN
                int pin = keypad.getInput(); // input PIN

                //	if(theAdmin.isAdmin(accountNumber, pin)) {
                //            userAuthenticated = 1;
                //            currentAccountNumber = accountNumber;
                //        } else {
                            // set userAuthenticated to boolean value returned by database
                userAuthenticated
                            = bankDatabase.authenticateUser(accountNumber, pin);
                // check whether authentication succeeded
                if (userAuthenticated == 1) {
                    currentAccountNumber = accountNumber; // save user's account #
                } else {
                    screen.displayMessageLine(
                        "Invalid account number or PIN. Please try again.\n");
                }
            } else {
                screen.displayMessageLine("Your account has been blocked");
                screen.displayMessageLine("Please contact the admin to unblock\n");
            }
//        }
    }

    // display the main menu and perform transactions
    private void performTransactions() {
	// local variable to store transaction currently being processed
	Transaction currentTransaction = null;
	TransactionController currentTransactionController = null;

	boolean userExited = false; // user has not chosen to exit
    	int mainMenuSelection;
	// loop while user has not chosen option to exit system
	while (!userExited) {
	    // show main menu and get user selection
            if (bankDatabase.getAccount(currentAccountNumber).isAdmin()) {
                mainMenuSelection = displayAdminMainMenu();
                switch (mainMenuSelection) {
                    case CONFIRM_DEPOSIT:
                        currentTransactionController = 
                            new ConfirmDepositController(depositSlot, 
                                bankDatabase, keypad, screen);
                        currentTransactionController.run();
                        break;

                    case GIVE_INVOICE :
                        currentTransaction = createAdminTransaction(mainMenuSelection);
                        currentTransactionController = new GiveInvoiceController(currentTransaction, keypad, screen);
                        currentTransactionController.run();
                        break;

                    case ADD_ACCOUNT :{
                        AdminController controller = null;
                        controller = new AdminController(deposit, depositSlot,
                            bankDatabase);

                        int accountType = displayAddAccountMenu();
                        controller.addAccount(accountType);
                        break;
                    }

                    case BLOCK_ACCOUNT : {
                         AdminController controller = null;
                        controller = new AdminController(deposit, depositSlot,
                            bankDatabase);
                        int blockedAccountNumber = reqAccountNumber();
                        controller.blockAccount(blockedAccountNumber);
                        screen.displayMessageLine("Account has been blocked");
                        break;
                    }

                    case UNBLOCK_ACCOUNT : {
                        AdminController controller = null;
                        controller = new AdminController(deposit, depositSlot,
                            bankDatabase);

                        int unblockAccountNumber = reqAccountNumber();
                        controller.unblockAccount(unblockAccountNumber);
                        screen.displayMessageLine("Account unblocked");
                        break;
                    }

                    case ADD_DISPENSER:
                        currentTransactionController = new AddDispenserController(
                            cashDispenser, keypad, screen);
                        currentTransactionController.run();
                        break;

                    case CHECK_DISPENSER:
                        currentTransactionController = new CheckCashDispenserController(
                            cashDispenser, keypad, screen);
                        currentTransactionController.run();
                        break;

                    case DATE_CHANGED :
                        dateController.changeDate();
                        break;
                    case EXIT_ADMIN :
                        screen.displayMessageLine("\nExiting the system...");
                        userExited = true; // this ATM session should end
                        break;

                    default: //
                        screen.displayMessageLine(
                           "\nYou did not enter a valid selection. Try again.");
                        break;
                }
            } else {
                mainMenuSelection = displayMainMenu();

                // decide how to proceed based on user's menu selection
                switch (mainMenuSelection) {
                    // user chose to perform one of three transaction types
                    case BALANCE_INQUIRY:

                        // initialize as new object of chosen type
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransactionController
                                = new BalanceInquiryController(currentTransaction, keypad, screen);
                        currentTransactionController.run(); // execute transaction
                        break;

                    case WITHDRAWAL:
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransactionController
                                = new WithdrawalController(currentTransaction, keypad, screen);
                        currentTransactionController.run(); // execute transaction
                        break;

                    case DEPOSIT:
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransactionController
                                = new DepositController(currentTransaction, keypad, screen);
                        currentTransactionController.run(); // execute transaction
                        break;
                    case TRANSFER:
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransactionController
                                = new TransferController(currentTransaction, keypad, screen);
                        currentTransactionController.run(); // execute transaction
                        break;
                    case PAYMENT:
                            currentTransaction
                                    = createTransaction(mainMenuSelection);
                            currentTransactionController
                                    = new PaymentController(currentTransaction, keypad, screen);
                            currentTransactionController.run(); // execute transaction
                            break;
                    case BANKSTATEMENT:
                            currentTransactionController
                                   = new BankStatementController(keypad, screen, bankDatabase.getCustomer(currentAccountNumber));
                            currentTransactionController.run(); // execute transaction
                            break;
                    case EXIT: // user chose to terminate session
                        screen.displayMessageLine("\nExiting the system...");
                        userExited = true; // this ATM session should end
                        break;

                    default: //
                        screen.displayMessageLine(
                                "\nYou did not enter a valid selection. Try again.");
                        break;
                }
            }
	}
    }
/*
    private void adminMode() {
        boolean adminExited = false;
        AdminController transactionController = null;

        while(!adminExited) {
            int selection = adminMenu();

            switch(selection) {
                case CONFIRM_DEPOSIT:
                    //method confirm deposit
                    screen.displayMessageLine("Confirm Deposit\n");
                    transactionController = new AdminController(deposit,
                            depositSlot, bankDatabase);
                    transactionController.confirmDeposit();
                    break;

                case EXIT_ADMIN:
                    screen.displayMessageLine("\nExiting the system...");
		    adminExited = true; // this ATM session should end
		    break;

		case WITHDRAWAL:
		    currentTransaction
			    = createTransaction(mainMenuSelection);
		    currentTransactionController
			    = new WithdrawalController(currentTransaction, keypad, screen);
		    currentTransactionController.run(); // execute transaction
		    break;

		case DEPOSIT:
		    currentTransaction
			    = createTransaction(mainMenuSelection);
		    currentTransactionController
			    = new DepositController(currentTransaction, keypad, screen);
		    currentTransactionController.run(); // execute transaction
		    break;
                case BANKSTATEMENT:
                    currentTransaction
			    = createTransaction(mainMenuSelection);
		   // currentTransactionController
			//    = new //DepositController(currentTransaction, keypad, screen);
		    //currentTransactionController.run(); // execute transaction
		    break;

		case EXIT: // user chose to terminate session
		    screen.displayMessageLine("\nExiting the system...");
		    userExited = true; // this ATM session should end
		    break;

		default: //

                default: //
		    screen.displayMessageLine(
			    "\nYou did not enter a valid selection. Try again.");
		    break;
            }
        }
    }
*/
    // display the main menu and return an input selection
    private int displayMainMenu() {
	     screen.displayMessageLine("\nMain menu:");
	     screen.displayMessageLine("1 - View my balance");
	     screen.displayMessageLine("2 - Withdraw cash");
       screen.displayMessageLine("3 - Deposit funds");
       screen.displayMessageLine("4 - Transfer");
	     screen.displayMessageLine("5 - Payment");
       screen.displayMessageLine("6 - Log Transaction");
	     screen.displayMessageLine("7 - Exit\n");
	     screen.displayMessage("Enter a choice: ");
	     return keypad.getInput(); // return user's selection
    }

    // display the Admin main menu and return an input selection
    private int displayAdminMainMenu() {
        screen.displayMessageLine("\nAdmin Main menu:");
        screen.displayMessageLine("1 - Confirm deposit");
	screen.displayMessageLine("2 - Give Payment Invoice To Customer");
        screen.displayMessageLine("3 - Add Account");
        screen.displayMessageLine("4 - Block Account");
        screen.displayMessageLine("5 - Unblock Account");
        screen.displayMessageLine("6 - Add cash dispenser");
        screen.displayMessageLine("7 - Check cash dispenser");
        screen.displayMessageLine("8 - Change The Date");
        screen.displayMessageLine("9 - Exit\n");
	      screen.displayMessage("Enter a choice: ");
	      return keypad.getInput(); // return user's selection
    }

    private int displayAddAccountMenu() {
        screen.displayMessageLine("\nChoose account type:");
        screen.displayMessageLine("1 - Bisnis");
	      screen.displayMessageLine("2 - Siswa");
        screen.displayMessageLine("3 - Masa Depan\n");
        screen.displayMessageLine("4 - Exit\n");
	      screen.displayMessage("Enter a choice: ");
	      return keypad.getInput(); // return admin's selection
    }

    private Transaction createTransaction(int type) {
	Transaction temp = null;

	switch (type) {
	    case BALANCE_INQUIRY:
		temp = new BalanceInquiry(
			currentAccountNumber, bankDatabase);
		break;
	    case WITHDRAWAL:
		temp = new Withdrawal(
			currentAccountNumber, bankDatabase, cashDispenser);
		break;
	    case DEPOSIT:
		temp = new Deposit(
			currentAccountNumber, bankDatabase, depositSlot);
                deposit = (Deposit) temp;
		break;
            case TRANSFER:
		temp = new Transfer(
			currentAccountNumber, bankDatabase);
		break;
            case PAYMENT:
            temp = new Payment(
                currentAccountNumber, bankDatabase);
            break;
	}
	return temp;
    }

    private Transaction createAdminTransaction(int type) {
        Transaction temp = null;

        switch (type) {
            case GIVE_INVOICE:
            temp = new GiveInvoice(
                currentAccountNumber, bankDatabase);
            break;
        }

        return temp;
    }

    private int reqAccountNumber() {
        screen.displayMessage("\nEnter the account number : ");
        return keypad.getInput();
    }


}
