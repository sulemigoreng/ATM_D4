package com.appl.atm.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {
    
    public PaymentTest() {
    }
    
    @Test
    public void CreatePayment() {
        Customer customer = new Bisnis(1,1, 2000, 2000);
        
        assertTrue(customer.addInvoice(1, 2, 500, "Desc 1"));
        assertFalse(customer.addInvoice(1, 2, 500, "Desc 2"));
        assertTrue(customer.addInvoice(2, 2, 500, "Desc 3"));
        assertTrue(customer.addInvoice(3, 2, 500, "Desc 4"));   
        
        assertEquals(3, customer.getInvoiceList().size());
    }
    
    @Test
    public void reducePayment() {
        Customer customer = new Bisnis(1,1, 2000, 2000);
        assertTrue(customer.addInvoice(5, 2, 500, "Desc 1"));
        assertNull(customer.getInvoce(1));
        assertNotNull(customer.getInvoce(5));
        
        customer.getInvoce(5).pay(20);
        assertEquals(480, customer.getInvoce(5).getAmount(), 0.0);
        assertEquals(2, customer.getInvoce(5).getApplicantAccountNumber());
        assertEquals("Desc 1", customer.getInvoce(5).getAmount());   
        
        customer.getInvoiceList().remove(customer.getInvoce(5));
        assertNull(customer.getInvoce(5));
    }
}
