/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author Rayhan Azka
 */
public class Invoice implements Comparable<Invoice> {
    private int id;
    private String billInformation;
    private double billNominal;
    private int applicantAccNum;

    public Invoice(int id, String billInformation, double billNominal, int applicantAccNum) {
        this.id = id;
        this.billInformation = billInformation;
        this.billNominal = billNominal;
        this.applicantAccNum = applicantAccNum;
    }

    public int getID() {
        return id;
    }

    public String getBillInformation() {
        return billInformation;
    }

    public double getBillNominal() {
        return billNominal;
    }

    public int getApplicantAccNum() {
        return applicantAccNum;
    }

    @Override
    public int compareTo(Invoice t) {
        return id - t.id;
    }
    
    public void reduceNominal (double amount) {
        if (amount <= billNominal) {
            billNominal -= amount;
        }
    }        
}
