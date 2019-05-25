
package com.appl.atm.model;

public class Admin implements IAccount {
   private int accountNumber; // indicates account involved
   private int pin;
   
   public Admin(int userAccountNumber, int userPin) {

      accountNumber = userAccountNumber;
      pin = userPin;
   }
   
   public boolean isAdmin(int userAccountNumber, int userPin) {
       return (userAccountNumber == this.accountNumber && userPin == this.pin);
   }

    /**
     * @return the accountNumber
     */
    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the pin
     */
    @Override
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }
    
}
