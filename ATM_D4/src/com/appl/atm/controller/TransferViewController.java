/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;
import com.appl.atm.view.View;
/**
 *
 * @author IchaCahyaWulan
 */
public class TransferViewController {
    private View view = new View();
    
    /***Mengambil method-method dari kelas View yang khusus untuk proses transfer***/
    
    public int processInputRecipientAccountNumber(){
        return view.requestRecipientAccountNumber();
    }
    
    public double processInputTransferAmount(){
        return view.requestTransferAmount();
    }
    
    public void processDisplayMaxOneTimeLimitV(double limit){
        view.displayMaxOneTimeLimit(limit);
    }
    
    public void processDisplayMaxOneDayLimitV(double limit){
        view.displayMaxOneDayLimit(limit);
    }
    
    public void processInsufficientFunds(){
        view.displayInsufficientFunds();
    }
    
     public void processAccountNotFound(){
        view.displayAccountNotFound();
    }
     
     public void processNotCustomer(){
        view.displayNotCustomer();
    }
     
     public void processCancelTransfer(){
        view.displayCancelTransferMessage();
    }
    
    public void processTransferSuccess(){
        view.displayTransferSuccess();
    }
    
    public void processDeclineSiswa(){
        view.displaySiswaNotAllowed();
    }
    
    public void processDeclineTransferOwnAccount(){
        view.displayCantTransferToOwnAccount();
    }
    
    public void processExceedsLimit(){
        view.displayExceedsOneTimeTransfer();
    }
    
    
     public double processTransferV(int recipientAccountNumber, double Amount){
        String answer;
        boolean IsAnswerRight = false;
        view.displayReciverAccountNumber(recipientAccountNumber);
        view.displayAmount(Amount);
        
        while(!IsAnswerRight) {
           answer = view.displayWarningMessage();
            if(answer.equals("Y")) {
               view.displayTransferSuccess();
                return 1;
            } else { 
                if(answer.equals("N")){
                   view.displayCancelTransferMessage();
                    return 2;
                } else {
                   view.displayEnterChoice();
                }
            }
        }
        
        return 0;
    }
   
}
