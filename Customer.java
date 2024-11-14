/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author epicg
 */
public class Customer extends User{
    private double balance;
    private AccountLevel level ;
    private File file;
            
    public Customer(String username,String password,File file) throws FileNotFoundException {
        this.username = username;
        File f = new File("src/Customers/" + username + ".txt"); 
        this.password = password;
        this.file = file;
        
        Scanner reader = new Scanner(f);
        reader.nextLine();
        balance = reader.nextDouble();
        level.changeLevel(this);
    }
    
    public static boolean login(String usernameInput, String passwordInput) throws FileNotFoundException {
        File file = new File("src/Customers/" + usernameInput + ".txt"); 
        if (file.exists()) { 
            Scanner reader = new Scanner(file); 
            if(reader.nextLine().equals(passwordInput)) return true;
            else return false; 
        }
        else return false;
        
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountLevel getLevel() {
        return level;
    }

    public void setLevel(AccountLevel level) {
        this.level = level;
    }
    
    public void updateFile() throws IOException {
          FileWriter w = new FileWriter(file); 
          w.write(password + "\n" + balance); 
          w.close();
    }
    
    public boolean deposit(double amount) throws IOException{
        if(amount < 0) return false; 
        
        else{
            balance += amount;
            level.changeLevel(this); 
            this.updateFile();
            return true;
        }
    }
    
    public boolean withdraw(double amount) throws IOException{
        if(amount < 0 || amount > balance)  return false; 
        
        else{
            balance -= amount;
            level.changeLevel(this); 
            this.updateFile();
            return true;
        }
    }
    
    public boolean purchase(double amount){
        return level.purchase(amount,this);
    }
}
