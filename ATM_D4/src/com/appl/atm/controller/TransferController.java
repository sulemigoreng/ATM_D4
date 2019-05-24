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
/**
 *
 * @author dewan
 */

public class TransferController extends TransactionController{
    private Transfer transaction;
    
    
    public TransferController (Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        transaction = (Transfer) theTransaction;
    }

    @Override
    public int run() {
        TransferViewController screen = new TransferViewController();
        if(transaction.execute()!=IS_SISWA||transaction.execute()!=NOT_A_CUSTOMER){
            transaction.setAccountTransferred(screen.processInputRecipientV());
            if(transaction.execute()==ACCOUNT_NOT_FOUND){
                screen.processDisplayAccountNotFound();
                return 0;
            }
            if(transaction.execute()==SAME_ACCOUNT){
                screen.processDeclineTransferOwnAccount();
                return 0;
            }
            else{
                screen.processDisplayMaxOneTimeLimitV(transaction.getMaxT());
                transaction.setAmount(screen.processInputTheAmountV());
                switch(transaction.execute()){
                    case TRANSFER_CANCELED:
                        screen.processCanceled();
                        break;
                    case NOT_ENOUGH_SALDO:
                        screen.processDisplayNotEnoughSaldo();
                        break;
                    case TRANSFER_SUCCESS:
                        screen.processDisplayTransfered();
                        break;
                    case EXCEED_ONE_TIME_TRANSFER_BISNIS:
                        screen.processExceedsLimit();
                        break;
                    case EXCEED_ONE_TIME_TRANSFER_MASA_DEPAN:
                        screen.processExceedsLimit();
                        break;
                }
            }
        }
        else{
            if(transaction.execute()!=IS_SISWA){
                screen.processDeclineSiswa();
            }
            else{
                screen.processDisplayAccountNotCustomer();
            }
        }
        return 0;
    }
}
