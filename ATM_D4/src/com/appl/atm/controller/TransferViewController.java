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
    
    public int processInputRecipientV(){
        return view.inputRecipientAccountNumberV();
    }
    
    public double processInputTheAmountV(){
        return view.inputTheAmountV();
    }
    
    public void processDisplayMaxOneTimeLimitV(double limit){
        view.displayMaxOneTimeLimitV(limit);
    }
    
    public void processDisplayMaxOneDayLimitV(double limit){
        view.displayMaxOneDayLimitV(limit);
    }
    
    public void processDisplayNotEnoughSaldo(){
        view.displayNotEnoughSaldo();
    }
    
     public void processDisplayAccountNotFound(){
        view.displayAccountNotFound();
    }
     
     public void processDisplayAccountNotCustomer(){
        view.displayNotCustomer();
    }
     
     public void processCanceled(){
        view.displayCancelTransfer();
    }
    
    public void processDisplayTransfered(){
        view.displayTransferred();
    }
    
    public void processDeclineSiswa(){
        view.displaySiswaCantTransfer();
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
        view.displayAccountNumber(recipientAccountNumber);
        view.displayAmount(Amount);
        while(!IsAnswerRight) {
           answer = view.displayCorrect();
            if(answer.equals("Y")){
               view.displayTransferred();
                return 1;
            }
            else { 
                if(answer.equals("N")){
                   view.displayCancelTransfer();
                    return 2;
                }
                else {
                   view.displayEnter();
                }
            }
        }
        return 0;
    }
   
}
