package com.appl.atm.model;

public class Invoice implements Comparable<Invoice> {
    private final int id;
    private final int applicantAccountNumber;
    private double amount;
    private final String description;

    public Invoice(int id, int applicantAccountNumber, double amount, String description) {
        this.id = id;
        this.applicantAccountNumber = applicantAccountNumber;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public int getApplicantAccountNumber() {
        return applicantAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean pay(double amount) {
        if (amount <= this.amount) {
            this.setAmount(this.amount - amount);
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(Invoice t) {
        return id - t.id;
    }       

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
