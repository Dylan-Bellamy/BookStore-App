package BookStoreApp;

import java.util.ArrayList;
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

public class BookStoreApp {
    public static ArrayList<Book> bookStorage = new ArrayList<>();
    public static ArrayList<Customer> customerStorage = new ArrayList<>();
    public static Customer currentCustomer = null;
    public static ArrayList<Book> checkout = new ArrayList<>();
    public static boolean usedPoints = false;
    
    private AppState currentState;
    private Stage window;
    
    public BookStoreApp(AppState initialState, Stage stage) {
        this.currentState = initialState;
        this.window = stage; // Initialize with the main stage
    }

    public void setState(AppState state) {
        this.currentState = state;
    }

    public void applyState() {
        currentState.handle(this);
    }

    public Stage getStage() {
        return window;
    }
}