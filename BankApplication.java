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

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import java.io.*;

public class BankApplication extends Application {
    private Manager mgr = new Manager();
    private Scene home;
    
    @Override
    public void start(Stage primaryStage){
        VBox loginScreen = new VBox(15);
        
        Label welcomeLabel = new Label("Welcome to Bank Application.");
        Label loginLabel = new Label("Please login below.");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();
        usernameTextField.setMaxWidth(200);
        passwordTextField.setMaxWidth(200);
        Button loginButton = new Button("Login");
        Label loginMessage = new Label();

        loginScreen.getChildren().addAll(welcomeLabel,loginLabel,usernameLabel, usernameTextField, passwordLabel, passwordTextField, loginButton, loginMessage);
        loginScreen.setAlignment(Pos.CENTER);

        loginButton.setOnAction(e -> {
            String usernameInput = usernameTextField.getText();
            String passwordInput = passwordTextField.getText();
            try{
                if (mgr.login(usernameInput,passwordInput))  showManagerScreen(primaryStage);
                else if(Customer.login(usernameInput,passwordInput)) showCustomerScreen(primaryStage,usernameInput,passwordInput);
                else loginMessage.setText("Incorrect login or account does not exist.");
            }
            catch(Exception ex){
                loginMessage.setText("An error occured.");
            }
        });
        
        home = new Scene(loginScreen, 500, 500);
        primaryStage.setScene(home);
        primaryStage.setTitle("Bank Application");
        primaryStage.show();
    }
    
    private void showCustomerScreen(Stage primaryStage, String username, String password) {
        Customer c = new Customer(username,password,new File("src/Customers/" + username + ".txt"));
        
        VBox customerScreen = new VBox(15);
        HBox customerAmountBox = new HBox(10);
        Label helloLabel = new Label("Hello " + username + ".");
        Label balanceLabel = new Label("Balance: $" + c.getBalance());
        Label levelLabel = new Label("Account Type: " + c.getLevel());
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button purchaseButton = new Button("Online Purchase");
        Button logoutButton = new Button("Logout");
        TextField amountTextField = new TextField();
        amountTextField.setMaxWidth(200);
        Label amountLabel = new Label("Enter amount:");
        Label transactionStatus = new Label();
        
        customerAmountBox.getChildren().addAll(amountLabel,amountTextField);
        customerAmountBox.setAlignment(Pos.CENTER);
        customerScreen.getChildren().addAll(helloLabel, balanceLabel,levelLabel,customerAmountBox, depositButton, withdrawButton, purchaseButton, logoutButton, transactionStatus);
        customerScreen.setAlignment(Pos.CENTER);

        depositButton.setOnAction(e -> {
            try{
                double amount = Double.valueOf(amountTextField.getText());
                if(!(c.deposit(amount))) transactionStatus.setText("Cannot deposit negative amount.");
                else{
                    transactionStatus.setText("$" + amount + " deposited succesfully.");
                    balanceLabel.setText("Balance: $" + c.getBalance());
                    levelLabel.setText("Account Type: " + c.getLevel());
                }
            }
            catch(Exception ex){
                transactionStatus.setText("An error occurred.");
            }
        });
        withdrawButton.setOnAction(e -> {
            try{
                double amount = Double.valueOf(amountTextField.getText());
                if(amount < 0) transactionStatus.setText("Cannot withdraw negative amount.");
                else if(!(c.withdraw(amount))) transactionStatus.setText("Insufficient balance for withdrawal.");
                else{
                    transactionStatus.setText("$" + amount + " withdrawn succesfully.");
                    balanceLabel.setText("Balance: $" + c.getBalance());
                    levelLabel.setText("Account Type: " + c.getLevel());
                }
            }
            catch(Exception ex){
                transactionStatus.setText("An error occurred.");
            }
        });
        purchaseButton.setOnAction(e -> {
            try{
                double amount = Double.valueOf(amountTextField.getText());
                if(amount < 50) transactionStatus.setText("Cannot purchase amount below $50.");
                else if(!(c.purchase(amount))) transactionStatus.setText("Insufficient balance for purchase.");
                else{
                    transactionStatus.setText("Purchase of $" + amount + " completed succesfully.");
                    balanceLabel.setText("Balance: $" + c.getBalance());
                    levelLabel.setText("Account Type: " + c.getLevel());
                }
            }
            catch(Exception ex){
                transactionStatus.setText("An error occurred.");
            }
        });
        
        logoutButton.setOnAction(e -> start(primaryStage));

        primaryStage.setScene(new Scene(customerScreen, 500, 500));
    }
    
    private void showManagerScreen(Stage primaryStage) {
        VBox managerScreen = new VBox(15);
        Label managerWelcome = new Label("Welcome, Mr. Manager.");
        Label managerUsernameLabel = new Label("Username:");
        Label managerPasswordLabel = new Label("Password:");
        TextField managerUsernameField = new TextField();
        PasswordField managerPasswordField = new PasswordField();
        managerUsernameField.setMaxWidth(200);
        managerPasswordField.setMaxWidth(200);
        Button addCustomerButton = new Button("Add Customer");
        Button deleteCustomerButton = new Button("Delete Customer");
        Button managerLogoutButton = new Button("Logout");
        Label managerTaskLabel = new Label();

        managerScreen.getChildren().addAll(managerWelcome,managerUsernameLabel, managerUsernameField, managerPasswordLabel, managerPasswordField, addCustomerButton, deleteCustomerButton, managerLogoutButton,managerTaskLabel);
        managerScreen.setAlignment(Pos.CENTER);

        addCustomerButton.setOnAction(e -> {
            try {
                if(mgr.addCustomer(managerUsernameField.getText(),managerPasswordField.getText())) managerTaskLabel.setText(managerUsernameField.getText() + " was added succesfully.");
                else managerTaskLabel.setText(managerUsernameField.getText() + " was unable to be added.");
            } catch (Exception ex) {
                System.out.println("An error occurred.");
            }
        });

        deleteCustomerButton.setOnAction(e -> {
            try {
                if(mgr.deleteCustomer(managerUsernameField.getText())) managerTaskLabel.setText(managerUsernameField.getText() + " was deleted succesfully.");
                else managerTaskLabel.setText(managerUsernameField.getText() + " was unable to be deleted.");
            } catch (Exception ex) {
                System.out.println("An error occurred.");
            }
        });

        managerLogoutButton.setOnAction(e -> start(primaryStage));

        primaryStage.setScene(new Scene(managerScreen, 500,500));
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
