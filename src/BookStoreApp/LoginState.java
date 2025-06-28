package BookStoreApp;

import static BookStoreApp.BookStoreApp.currentCustomer;
import static BookStoreApp.BookStoreApp.customerStorage;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginState implements AppState {
    
    @Override
    public void handle(BookStoreApp context) {
        
        //Creates labels for username and password textfields
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label failLabel = new Label("");
        
        //Creates textfields for username and password
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        
        //Creates login Button
        Button loginButton = new Button("Login");
        
        // Creates an Action for the valid username and password
        loginButton.setOnAction(e -> {
            
            boolean overlap = false;
            for(Customer customer : customerStorage){
                if((usernameField.getText().equals(customer.getName()) && 
                        passwordField.getText().equals(customer.getPassword()))){
                    overlap = true;
                    currentCustomer = customer;
                }
            }
            
            // Changes Screen to ownerStartState if the correct username 
            // and password has been entered
            if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                context.setState(new OwnerStartState());
                context.applyState();
            }
            
            // Changes Screen to CustomerStartState if the username and password belong to a Customer
            else if(overlap == true){
                context.setState(new CustomerStartState());
                context.applyState();
            } else {
                failLabel.setText("Incorrect Username or Password");
            }
        });

        // Login Screen Setup //
        
        // Create HBox Layout to arrange elements horz.
        HBox hbox1 = new HBox(26);
        hbox1.getChildren().addAll(usernameLabel, usernameField);
        
        HBox hbox2 = new HBox(30);
        hbox2.getChildren().addAll(passwordLabel, passwordField);
        
        // Create Button in Center
        StackPane stackButton1 = new StackPane(loginButton);
        
        StackPane stackLabel1 = new StackPane(failLabel);
        
        // Create VBox Layout to arrange elements vertically
        VBox vbox1 = new VBox(10);
        vbox1.getChildren().addAll(hbox1, hbox2, stackButton1, stackLabel1);
        
        // Creates Login Screen and Setting it to the window
        Scene loginState = new Scene(vbox1, 300, 140);
        
        
        // Set the new scene on the stage
        context.getStage().setScene(loginState);
    }
}