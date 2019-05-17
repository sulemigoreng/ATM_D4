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
    
    
    
    
    
     public int processTransferV(int recipientAccountNumber, double Amount){
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
