package BookStoreApp;

import static BookStoreApp.BookStoreApp.bookStorage;
import static BookStoreApp.BookStoreApp.currentCustomer;
import static BookStoreApp.BookStoreApp.checkout;
import static BookStoreApp.BookStoreApp.usedPoints;
import javafx.beans.binding.Bindings;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
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
public class CustomerStartState implements AppState {

    ObservableList<Book> books = FXCollections.observableArrayList();
    
    @Override
    public void handle(BookStoreApp context) {
        // Book Screen Setup //
        
        // Creating the Table //
        TableView<Book> bookTable;
        
        //Naming the Columns of the Book Table
        
        // Name Column
        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        // Price Column
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setMinWidth(99);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // Checkbox Column
        TableColumn<Book, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setMinWidth(75);
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().getChecked());
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
       
        
        bookTable = new TableView<>();
        // Setting the Books as the item of the Book Table
        bookTable.setItems(getBooks()); 
        // Adding the Columns to the Book Table
        bookTable.getColumns().addAll(nameColumn, priceColumn, selectColumn);
        
        // Allows the table to be edited during runtime
        bookTable.setEditable(true);
        
        
        
        //Creates Buttons
        Button buyButton1 = new Button("Buy");
        Button buyButton2 = new Button("Buy & Redeem With Points");
        Button logoutButton = new Button("Logout");
        
        // Define transitions between states
        buyButton1.setOnAction(e -> {
            for(Book book : bookStorage){
                if(book.getChecked().get() == true){
                    System.out.println(book.getName());
                    checkout.add(book);
                    usedPoints = false;
                }
            }
            context.setState(new PurchaseState());
            context.applyState();
        });

        buyButton2.setOnAction(e -> {
            for(Book book : bookStorage){
                if(book.getChecked().get() == true){
                    checkout.add(book);
                    usedPoints = true;
                }
            }
            context.setState(new PurchaseState());
            context.applyState();
        });

        logoutButton.setOnAction(e -> {
            context.setState(new LoginState());
            context.applyState();
        });
        
        
        // Centers the Button
        StackPane stackButton2 = new StackPane(buyButton1);
        StackPane stackButton3 = new StackPane(buyButton2);
        StackPane stackButton4 = new StackPane(logoutButton);
        
        //Creates label for customer
        String s = "";
        s+= "Welcome " + currentCustomer.getName();
        s+= ". You have " + currentCustomer.getPoints();
        s+= " points. Your status is " + currentCustomer.getStatus() + "\n";
        Label welcomeLabel = new Label(s);
        
        //Putting them in Hboxs
        HBox hboxBook1 = new HBox(10);
        hboxBook1.getChildren().addAll(welcomeLabel);
        
        // Putting them in Vboxs
        VBox vboxBook = new VBox(10);
        vboxBook.getChildren().addAll(hboxBook1, bookTable, 
                stackButton2, stackButton3, stackButton4);
 
        Scene booksState = new Scene(vboxBook, 400, 400);
        context.getStage().setScene(booksState);
    
    }
    // Gets all of the Books
    public ObservableList<Book> getBooks(){
        for (Book bookStorage1 : bookStorage) {
            books.add(bookStorage1);
        }
        return books;
    }
}