/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author epicg
 */
public class GoldAccount extends AccountLevel {
    
    @Override
    public boolean purchase(double amount,Customer c){
        if((c.getBalance()-10) >= amount){
            c.setBalance(c.getBalance()-amount-10);
            changeLevel(c);
            return true;
        }
        else return false;
    }
    
    @Override
    public String toString(){
        return "Gold";
    }
}
