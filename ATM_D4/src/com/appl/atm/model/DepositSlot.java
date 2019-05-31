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
    
    public boolean deleteList(HashMap<Customer, Double> envelopes, Customer theCustomer,
            BankDatabase theBankDatabase){
        if(envelopes.containsKey(theCustomer)){
            addAvailableBalance(envelopes,theCustomer);
            theBankDatabase.updateList(theCustomer);
            return true;
        }else{
            return false;
        }
    }
    
    public void addAvailableBalance(HashMap<Customer, Double> envelopes, Customer theCustomer){
        theCustomer.setAvailableBalance(theCustomer.getAvailableBalance()+(double)envelopes.get(theCustomer));
    }
}
