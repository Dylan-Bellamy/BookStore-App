package BookStoreApp;

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

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OwnerStartState implements AppState {
    
    @Override
    public void handle(BookStoreApp context) {
        
        //Creates Buttons
        Button bookButton = new Button("Books");
        Button customerButton = new Button("Customers");
        Button logoutButton = new Button("Logout");
        
        // Centers the Button
        StackPane stackButton2 = new StackPane(bookButton);
        StackPane stackButton3 = new StackPane(customerButton);
        StackPane stackButton4 = new StackPane(logoutButton);

        // Define transitions between states
        bookButton.setOnAction(e -> {
            context.setState(new BooksState());
            context.applyState();
        });

        customerButton.setOnAction(e -> {
            context.setState(new CustomersState());
            context.applyState();
        });

        logoutButton.setOnAction(e -> {
            context.setState(new LoginState());
            context.applyState();
        });

        // Add to Vbox layout
        VBox vbox2 = new VBox(10);
        vbox2.getChildren().addAll(stackButton2, stackButton3, stackButton4);
        
        Scene ownerStartState = new Scene(vbox2, 300, 110);

        // Set the new scene on the stage
        context.getStage().setScene(ownerStartState);
    }
    
}