package com.appl.atm.model;

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
        customer.getTransaksiLog().add("[DATE] Bill "+String.valueOf(invoiceDescription)
                + " | Invoice Id : " +String.valueOf(invoiceId) + "   From Applicant : "
                + String.valueOf(applicantAccountNumber) + "   with amount $ "
                + String.valueOf(invoiceAmount) + "   Paid off : false"); 

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
