package BookStoreApp;

import static BookStoreApp.BookStoreApp.bookStorage;
import static BookStoreApp.BookStoreApp.customerStorage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUINew extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        String filename1 = "books.txt";
        String filename2 = "customers.txt";
        File file1 = new File(filename1);
        File file2 = new File(filename2);
        
        //Updates the Catalogue when [X] Button is Clicked
        primaryStage.setOnCloseRequest(event -> {
            try {
                String s = "";
                for(Book book : bookStorage){
                    s += book.getName() + '|';
                    s += book.getPrice() + "\n";
                }
                
                FileWriter writer1 = new FileWriter(file1);
                writer1.write(s);
                writer1.flush();
                
                s = "";
                for(Customer customer : customerStorage){
                    s += customer.getName() + '|';
                    s += customer.getPassword() + '|';
                    s += customer.getPoints() + "|";
                    s += customer.getStatus() + "\n";
                }
                FileWriter writer2 = new FileWriter(file2);
                writer2.write(s);
                writer2.flush();
                
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        });
        
        // Initialize the state context with the initial LoginState
        BookStoreApp context = new BookStoreApp(new LoginState(), primaryStage);
        
        // Set the initial state (login screen)
        context.applyState(); 

        primaryStage.setTitle("Bookstore App");
        primaryStage.show();
    }

    public static void load(){
        String filename1 = "books.txt";
        String filename2 = "customers.txt";
        File file1 = new File(filename1);
        File file2 = new File(filename2);
        
        //Creates Catalogue if Undefined
        if (file1.exists() == false){
            
            try {
                String s = "";
                s += "Harry Potter|29.99\n";
                s += "The Alchemist|22.00\n";
                s += "The Complete Maus|30.95\n";
                s += "Wonder|24.00\n";
                s += "Dr.Seuss' ABC|14.99\n";
                s += "Revolving Door|0.00\n";
                s += "Necronomicon|9999.99\n";
                s += "The Complete Among Us Strategy Guide|69\n";
                
                FileWriter writer1 = new FileWriter(file1);
                writer1.write(s);
                writer1.flush();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
        if (file2.exists() == false){
            try {
                String s = "";
                s += "John Doe|password|123|Silver\n";
                s += "Jane Doe|password|1234|Gold\n";
                
                FileWriter writer2 = new FileWriter(file2);
                writer2.write(s);
                writer2.flush();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
        //Reads Through the Catalogue
        try {
            
            Scanner scan1 = new Scanner(file1);
            while (scan1.hasNextLine()){
                String line[] = scan1.nextLine().split("\\|");
                bookStorage.add(new Book(line[0], Double.parseDouble(line[1])));
            }
            
            Scanner scan2 = new Scanner(file2);
            while (scan2.hasNextLine()){
                String line[] = scan2.nextLine().split("\\|");
                customerStorage.add(new Customer(line[0], line[1], Integer.parseInt(line[2]), line[3]));
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        load();
        launch(args);
    }
}