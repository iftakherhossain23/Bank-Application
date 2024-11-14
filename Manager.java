/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.*;

/**
 *
 * @author epicg
 */

/** Overview: Manager is immutable and
 * represents the "manager" of the bank,
 * with the primary functionality of
 * adding and deleting Customers.
 * 
 * Abstraction Function:
 * AF(c) = an abstract Manager M where M.username = c.username and M.password = c.password
 * 
 * Representation Invariant:
 * c.username == "admin" &amp;&amp; c.password == "admin"
*/
public class Manager extends User {
    
    /** 
    * EFFECTS: Creates a Manager object with both username and password as "admin"
    */
    public Manager(){
        username = "admin";
        password = "admin";
    }
    
    /** 
    * REQUIRES: usernameInput and passwordInput are valid String objects and not null
    * EFFECTS: Returns true if usernameInput and passwordInput are both "admin", otherwise false
    */
    public boolean login(String usernameInput,String passwordInput){
        return (usernameInput.equals("admin") && passwordInput.equals("admin"));
    }
    
    /** 
    * REQUIRES: username and password are valid String objects and not null
    * MODIFIES: The file "username.txt" of the customer
    * EFFECTS: Creates a new file named "username.txt" if a file with
    * the given username does not exist in src/CustomerFiles, with the password 
    * and balance, and returns true; Otherwise returns false 
    * if customer already exists.
    */    
    public boolean addCustomer(String username,String password) throws IOException {
        File file = new File("src/Customers/" + username + ".txt");
        if (file.exists())  return false;
         else {
          file.createNewFile();
          FileWriter w = new FileWriter(file);
          w.write(password + "\n" + "100.0"); 
          w.close();
          return true;
         }
    }
    
    /** 
    * REQUIRES: username is valid String object and not null
    * MODIFIES: The file "username.txt" of the customer
    * EFFECTS: Deletes the file named "username.txt" if a file with
    * the given username exists in src/CustomerFiles, and returns true. 
    * Otherwise returns false if customer does not exist.
    */ 
    public boolean deleteCustomer(String username){
        File file = new File("src/Customers/" + username + ".txt");
        if (file.delete()) return true;
        else return false; 
    }
    
    /** 
    * EFFECTS: Returns string concatenation of login credentials
    * to confirm validity of abstraction function
    */ 
    public String toString(){
        return("Username: " + username + " | Password: " + password);
    }
    
    /** 
    * EFFECTS: Returns a boolean to ensure correctness of login
    * credentials as per representation invariant
    */ 
    public boolean repOK(){
        if(username.equals("admin") && password.equals("admin")) return true;
        else return false;
    }
}
