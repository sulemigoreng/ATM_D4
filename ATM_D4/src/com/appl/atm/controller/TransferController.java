/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transfer;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.model.Customer;
/**
 *
 * @author dewan
 */

public class TransferController extends TransactionController{
    private Transfer transaction;
    private BankStatementController bankStatement;
    private Customer customer;
    
    public TransferController (Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        transaction = (Transfer) theTransaction;
        customer = transaction.getBankDatabase().getCustomer(transaction.getAccountNumber());
        bankStatement = new BankStatementController(theKeypad, theScreen, customer);
    }

    @Override
    public int run() {
        TransferViewController screen = new TransferViewController();
        if (transaction.execute() != IS_SISWA && transaction.execute() != NOT_A_CUSTOMER) {
            transaction.setAccountTransferred(screen.processInputRecipientAccountNumber());
            
            //Transfer gagal jika akun target tidak ditemukan
            if (transaction.execute() == ACCOUNT_NOT_FOUND) {
                screen.processAccountNotFound();
                return 0;
            }
            
            //Transfer gagal jika memasukkan nomor akun sendiri
            if (transaction.execute() == SAME_ACCOUNT) {
                screen.processDeclineTransferOwnAccount();
                return 0;
            } else {
                screen.processDisplayMaxOneTimeLimitV(transaction.getTransferLimit());
                transaction.setAmount(screen.processInputTransferAmount());
                switch (transaction.execute()){
                    case TRANSFER_CANCELED: {
                        screen.processCancelTransfer();
                        break;
                    }
                    case INSUFFICIENT_BALANCE: {
                        screen.processInsufficientFunds();
                        break;
                    }
                    case TRANSFER_SUCCESS: {
                        bankStatement.addLog("Transfer",transaction.getAmount(),0.0,"to : ["+ transaction.getAccountTransferred()+"]");//menambahkan bankstatement ke akun customer setelah transfer sukses
                        screen.processTransferSuccess();
                        break;
                    }
                    case EXCEED_TRANSFER_LIMIT_FOR_BISNIS: {
                        screen.processExceedsLimit();
                        break;
                    }
                    case EXCEED_TRANSFER_LIMIT_FOR_MASA_DEPAN: {
                        screen.processExceedsLimit();
                        break;
                    }
                }
            }
        } else {
            //Jika customer bertipe siswa, maka akan ditolak
            if (transaction.execute() == IS_SISWA) {
                screen.processDeclineSiswa();
            } else {
                screen.processNotCustomer();
            }
        }
        return 0;
    }
}
