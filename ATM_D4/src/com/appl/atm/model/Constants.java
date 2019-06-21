package com.appl.atm.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Annazar
 */
public final class Constants {
    public static final int BALANCE_INQUIRY = 1;
    public static final int WITHDRAWAL = 2;
    public static final int DEPOSIT = 3;
    public static final int TRANSFER = 4;
    public static final int PAYMENT = 5;
    public static final int BANKSTATEMENT = 6;
    public static final int EXIT = 7;

    //admin
    public static final int CONFIRM_DEPOSIT = 1;
    public static final int GIVE_INVOICE = 2;
    public static final int ADD_ACCOUNT = 3;
    public static final int BLOCK_ACCOUNT = 4;
    public static final int UNBLOCK_ACCOUNT = 5;
    public static final int ADD_DISPENSER = 6;
    public static final int CHECK_DISPENSER = 7;
    public static final int DATE_CHANGED = 8;
    public static final int EXIT_ADMIN = 9;

    //admin add account
    public static final int ADD_BISNIS = 1;
    public static final int ADD_SISWA = 2;
    public static final int ADD_MASADEPAN = 3;
    public static final int EXIT_ADDACOUNT = 4;

    // withdraw
    public static final int WITHDRAWAL_CANCELED = 7;
    public static final int WITHDRAW_SUCCESSFUL = 1;
    public static final int BALANCE_NOT_ENOUGH = 2;
    public static final int CASHDISPENSER_NOT_ENOUGH = 3;
    public static final int REACHED_MAX_WITHDRAWAL = 4;

    // deposit
    public static final int DEPOSIT_CANCELED = 0;
    public static final int DEPOSIT_SUCCESSFUL = 1;
    public static final int ENVELOPE_IS_NOT_RECEIVED = 2;

    //transfer
    public static final int TRANSFER_CANCELED = 0;
    public static final int NOT_ENOUGH_SALDO = 1;
    public static final int TRANSFER_SUCCESS = 2;
    public static final int ACCOUNT_NOT_FOUND = 3;
    public static final int EXCEED_ONE_TIME_TRANSFER_BISNIS = 10000;
    public static final int EXCEED_ONE_TIME_TRANSFER_MASA_DEPAN = 500;
    public static final int IS_SISWA = 5;
    public static final int NOT_A_CUSTOMER = 6;
    public static final int SAME_ACCOUNT = 7;
    
    //give invoice
    public static final int ADD_INVOICE_SUCCESS = 1;
    public static final int CUSTOMER_NOT_EXIST = 2;
    public static final int ADD_INVOICE_FAILED = 3;
    
    // payment
    public static final int PAYMENT_SUCCESS = 0;
    public static final int PAYMENT_INVOICE_NOTFOUND = 1;
    public static final int PAYMENT_INSUFICIENT_AMOUNT = 2;
    public static final int PAYMENT_INVALID_AMOUNT = 3;
    
    public static final int PAID_OFF    = 1;
    public static final int INSTALLMENT = 2;
    public static final int CANCEL      = 0;

}
