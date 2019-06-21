package com.appl.atm.model;

import com.appl.atm.controller.BankStatementController;

import static com.appl.atm.model.Constants.*;

public class GiveInvoice extends Transaction {
    private int customerAccountNumber;
    private int invoiceId;
    private int applicantAccountNumber;
    private double invoiceAmount;
    private String invoiceDescription;
    
    public GiveInvoice(int userAccountNumber, BankDatabase atmBankDatabase) {
	super(userAccountNumber, atmBankDatabase);
    } 

    @Override
    public int execute() {
        Customer customer = getBankDatabase().getCustomer(customerAccountNumber);
        if(customer == null) {
            return CUSTOMER_NOT_EXIST;
        } 
        boolean isInvoiceAdded = customer.addInvoice(invoiceId, applicantAccountNumber,
                invoiceAmount, invoiceDescription);
        if(!isInvoiceAdded) {
            return ADD_INVOICE_FAILED;              
        }
        BankStatementController bankStatement = new BankStatementController(null, null, customer); // screen and keypad not used in addLog
        bankStatement.addLog("Bill    ", 0.0, 0.0, "[" + invoiceDescription + "] | Invoice Id : ["
                + invoiceId + "] | Applicant : [" + applicantAccountNumber + "] | amount : [$ " + invoiceAmount + "] | PaidOff : [" + false + "]");//menambahkan bankstatement ke akun customer berupa bill yang perlu dibayar

        return ADD_INVOICE_SUCCESS;   
    }
    
    public void setCustomerAccountNumber(int accountNumber) {
        customerAccountNumber = accountNumber;
    }
    
    public int getCustomerAccountNumber() {
	return customerAccountNumber;
    }
    
    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
    
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }
    
    public void setApplicantAccountNumber(int applicantAccountNumber) {
        this.applicantAccountNumber = applicantAccountNumber;
    }
}
