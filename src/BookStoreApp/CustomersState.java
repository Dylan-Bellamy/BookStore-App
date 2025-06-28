package BookStoreApp;

import static BookStoreApp.BookStoreApp.customerStorage;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author picku
 */
public class CustomersState implements AppState{
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    
    
    @Override
    public void handle(BookStoreApp context) {
       // Customers Screen Setup //
        
        // Creating the Table //
        TableView<Customer> customerTable;
        
        //Naming the Columns of the Customer Table
        
        // Name Column
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Customer Name");
        nameColumn.setMinWidth(125);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Password Column
        TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(119);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        //Points Column
        TableColumn<Customer, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setMinWidth(44);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        customerTable = new TableView<>();
        // Setting the Customer as the item of the Customer Table
        customerTable.setItems(getCustomers()); 
        // Adding the Columns to the Customer Table
        customerTable.getColumns().addAll(nameColumn, passwordColumn, pointsColumn);
        
        //Creates labels for Customer names and prices 
        Label nameLabel = new Label("Enter Name:");
        Label passwordLabel = new Label("Enter Password:");
        Label failLabel = new Label("");
        
        //Creates textfields for username and password
        TextField nameField = new TextField();
        TextField passwordField = new TextField();
        
        //Creates Buttons
        Button addCustomerButton = new Button("Add");
        Button deleteCustomerButton = new Button("Delete");
        Button backCustomerButton = new Button("Back");
        
        // Creates an Action for a new customer being added
        addCustomerButton.setOnAction(e -> {
            // Gets the username and password entered
            String getName = nameField.getText();
            String getPassword = passwordField.getText();
            
            boolean overlap = false;
            for(Customer customer : customerStorage){
                if(getName.equals(customer.getName())==true){
                    overlap = true;
                }
            }
            if(overlap == false){
                customerStorage.add(new Customer(getName, getPassword, 0, "Silver"));
                customers.add(new Customer(getName, getPassword, 0, "Silver"));
            }
            else{
                failLabel.setText("That Name Already Exists");
            }
        });
        
        deleteCustomerButton.setOnAction(e -> {
            int index = 0;
            boolean selected = false;
            for(Customer customer : customerStorage){
                if(customer == customerTable.getSelectionModel().getSelectedItem()){
                    index = customerStorage.indexOf(customer);
                    selected = true;
                }
            }
            if(selected == true){
                customerStorage.remove(index);
            }
            customerTable.getItems().removeAll(
            customerTable.getSelectionModel().getSelectedItems());
        });
        
        backCustomerButton.setOnAction(e -> {
            context.setState(new OwnerStartState());
            context.applyState();
        });
        
        //Putting them in Hboxs
        HBox hboxCust1 = new HBox(47);
        hboxCust1.getChildren().addAll(nameLabel, nameField);
        
        HBox hboxCust2 = new HBox(30);
        hboxCust2.getChildren().addAll(passwordLabel, passwordField);
        
        //Centering the Location 
        StackPane stackButton5 = new StackPane(addCustomerButton);
        StackPane stackButton6 = new StackPane(deleteCustomerButton);
        StackPane stackButton7 = new StackPane(backCustomerButton);
        
        // Putting them in Vboxs
        VBox vboxCust = new VBox(10);
        vboxCust.getChildren().addAll(customerTable, hboxCust1, hboxCust2, 
                stackButton5, stackButton6, stackButton7, failLabel);
 
        Scene customersState = new Scene(vboxCust, 300, 300);
        context.getStage().setScene(customersState);
    
    }
    // Gets all of the Customers
    public ObservableList<Customer> getCustomers(){
        for (Customer customer : customerStorage) {
            customers.add(customer);
        }
        return customers;
    }
}