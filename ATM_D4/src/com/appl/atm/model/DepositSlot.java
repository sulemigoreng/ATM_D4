package com.appl.atm.model;
import com.appl.atm.model.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Annazar
 */
public class DepositSlot {
    public boolean isEnvelopeReceived(HashMap envelopes, Customer theCustomer,
        double amountCashDeposited, BankDatabase theBankDatabase) {
        boolean acceptedStatus = addList(envelopes, theCustomer, amountCashDeposited, 
                theBankDatabase);
	return acceptedStatus; // deposit envelope was received
    }

    //Admin's method
    //the deposited money will be added by the admin
    public boolean addList(HashMap<Customer, Double> envelopes, Customer theCustomer, 
            double amountCashDeposited, BankDatabase theBankDatabase) {
        if(envelopes.containsKey(theCustomer)) {
            return false;
        } else {
            theBankDatabase.setList(theCustomer, amountCashDeposited);
            return true;
        }
    }
    
    //Mendelete sebuah envelope dari list lalu menambahkan banyaknya uang pada envelope tersebut ke available balance customer tersebut
    //mengembalikan true apabila menemukan envelope customer
    public boolean deleteList(HashMap<Customer, Double> envelopes, Customer theCustomer,
            BankDatabase theBankDatabase){
        if(envelopes.containsKey(theCustomer)){//mencari customer di list
            addAvailableBalance(envelopes,theCustomer);
            deleteEntry(theBankDatabase, theCustomer);
            return true;//envelope berhasil di-delete dari list
        }else{
            return false;//tidak dapat menemukan envelope yang dimaksud
        }
    }
    
    //Menambahkan available balance seorang customer sesuai dengan banyakanya uang di envelopenya
    //state yang berubah adalah available balance seorang customer
    public void addAvailableBalance(HashMap<Customer, Double> envelopes, Customer theCustomer){
        theCustomer.setAvailableBalance(theCustomer.getAvailableBalance()+(double)envelopes.get(theCustomer));
    }
    
    public void deleteEntry(BankDatabase theBankDatabase, Customer theCustomer){
        theBankDatabase.updateList(theCustomer);
    }
}
