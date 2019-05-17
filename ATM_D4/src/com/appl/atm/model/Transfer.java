/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
/**
 *
 * @author Fadhil
 */
public class Transfer extends Transaction {
    private Keypad keypad;
    private double tAmmount;
    private int tAccount;
    
    public Transfer(int userAccountNumber, BankDatabase atmBankDatabase, Keypad atmKeypad){
        super(userAccountNumber,atmBankDatabase);
        keypad = atmKeypad;
    }
    
    public int execute(){
        return 0;
    }
}
