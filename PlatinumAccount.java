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
public class PlatinumAccount extends AccountLevel {
    
    @Override
    public boolean purchase(double amount,Customer c){
        if(c.getBalance() >= amount){
            c.setBalance(c.getBalance()-amount);
            changeLevel(c);
            return true;
        }
        else return false;
    }
    
    @Override
    public String toString(){
        return "Platinum";
    }
    
}
