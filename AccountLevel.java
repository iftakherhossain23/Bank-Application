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
public abstract class AccountLevel {
    
    protected abstract boolean purchase(double amount,Customer c);
    
    public static void changeLevel(Customer c){
        if(c.getBalance() < 10000){
            c.setLevel(new SilverAccount());
        }
        else if (c.getBalance() < 20000){
            c.setLevel(new GoldAccount());
        }
        else{
            c.setLevel(new PlatinumAccount());
        }
    }
    
    @Override
    public abstract String toString();
}
