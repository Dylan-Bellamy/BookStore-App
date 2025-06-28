package BookStoreApp;

import static BookStoreApp.BookStoreApp.currentCustomer;
import static BookStoreApp.BookStoreApp.checkout;
import static BookStoreApp.BookStoreApp.usedPoints;
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
public class PurchaseState implements AppState {
    double tc = 0;
    
    @Override
    public void handle(BookStoreApp context) {
        
        //Obtains Total Cost
        for(Book book : checkout){
            tc += book.getPrice();
        }
        
        //Updates transaction results
        if(usedPoints == false){
            int newPoints = currentCustomer.getPoints() + (int)(tc*10);
            currentCustomer.setPoints(newPoints);
        }
        else{
            double difference = (((tc*100)-currentCustomer.getPoints())/100);
            if(difference < 0){
                currentCustomer.setPoints((int)(currentCustomer.getPoints()-(tc*100)));
                tc = 0;
            }
            else{
                currentCustomer.setPoints(0);
                tc = difference;
            }
        }
        
        if(currentCustomer.getPoints() >= 1000){
            currentCustomer.setStatus("Gold");
        }
        else{
            currentCustomer.setStatus("Silver");
        }
        
        //Creates Labels
        Label totalCostLabel = new Label("Total Cost: " + tc);
        Label updatedPointsLabel = new Label("Points: " + currentCustomer.getPoints() + ", Status: " + currentCustomer.getStatus());
        
        //Creates Buttons
        Button logoutButton = new Button("Logout");
        
        // Centers the items
        StackPane stackButton2 = new StackPane(totalCostLabel);
        StackPane stackButton3 = new StackPane(updatedPointsLabel);
        StackPane stackButton4 = new StackPane(logoutButton);
        
        // Define transitions between states
        logoutButton.setOnAction(e -> {
            checkout.removeAll(checkout);
            context.setState(new LoginState());
            context.applyState();
        });
        
        // Add to Vbox layout
        VBox vbox2 = new VBox(10);
        vbox2.getChildren().addAll(stackButton2, stackButton3, stackButton4);
        
        Scene PurchaseState = new Scene(vbox2, 300, 110);

        // Set the new scene on the stage
        context.getStage().setScene(PurchaseState);
    }
    
}