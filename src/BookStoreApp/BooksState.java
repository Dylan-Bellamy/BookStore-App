package BookStoreApp;

import static BookStoreApp.BookStoreApp.bookStorage;
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
public class BooksState implements AppState {
    
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
        
        //Price Column
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setMinWidth(99);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        bookTable = new TableView<>();
        // Setting the Books as the item of the Book Table
        bookTable.setItems(getBooks()); 
        // Adding the Columns to the Book Table
        bookTable.getColumns().addAll(nameColumn, priceColumn);
        
        //Creates labels for Book names and prices 
        Label bookNameLabel = new Label("Enter Name:");
        Label bookPriceLabel = new Label("Enter Price:");
        Label failLabel = new Label("");
        
        //Creates textfields for username and password
        TextField bookNameField = new TextField();
        TextField bookPriceField = new TextField();
        
        //Creates Buttons
        Button addBookButton = new Button("Add");
        Button deleteBookButton = new Button("Delete");
        Button backBookButton = new Button("Back");
        
        // Creates an Action for a new book being added
        addBookButton.setOnAction(e -> {
            // Gets the username and password entered
            String getBookName = bookNameField.getText();
            String getBookPrice = bookPriceField.getText();
            
            boolean overlap = false;
            for(Book book : bookStorage){
                if(getBookName.equals(book.getName())==true){
                    overlap = true;
                }
            }
            if(overlap == false && Integer.parseInt(getBookPrice) >= 0){
                bookStorage.add(new Book(getBookName, Double.parseDouble(getBookPrice)));
                books.add(new Book(getBookName, Double.parseDouble(getBookPrice)));
            }
            else{
                failLabel.setText("Invalid Input");
            }
        });
        
        deleteBookButton.setOnAction(e -> {
            int index = 0;
            boolean selected = false;
            for(Book book : bookStorage){
                if(book == bookTable.getSelectionModel().getSelectedItem()){
                    index = bookStorage.indexOf(book);
                    selected = true;
                }
            }
            if(selected == true){
                bookStorage.remove(index);
            }
            bookTable.getItems().removeAll(
            bookTable.getSelectionModel().getSelectedItems());
        });
        
        backBookButton.setOnAction(e -> {
            context.setState(new OwnerStartState());
            context.applyState();
        });
        
        //Putting them in Hboxs
        HBox hboxBook1 = new HBox(24);
        hboxBook1.getChildren().addAll(bookNameLabel, bookNameField);
        
        HBox hboxBook2 = new HBox(30);
        hboxBook2.getChildren().addAll(bookPriceLabel, bookPriceField);
        
        //Centering the Location 
        StackPane stackButton5 = new StackPane(addBookButton);
        StackPane stackButton6 = new StackPane(deleteBookButton);
        StackPane stackButton7 = new StackPane(backBookButton);
        
        // Putting them in Vboxs
        VBox vboxBook = new VBox(10);
        vboxBook.getChildren().addAll(bookTable, hboxBook1, hboxBook2, 
                stackButton5, stackButton6, stackButton7, failLabel);
 
        Scene booksState = new Scene(vboxBook, 300, 300);
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