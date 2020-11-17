/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Books;

/**
 * FXML Controller class
 *
 * @author BERKAY
 */
public class BorrowedBooksController implements Initializable {

 
     @FXML
    private ImageView bookImage;
    @FXML
    private Label bookName;
    @FXML
    private Label authorName;
    @FXML
    private Label publisherName;
    @FXML
    private Label pageNumber;
    @FXML
    private Label stok;
    @FXML
    private Label favCount;
    @FXML
    private TableView<Books> tableView;
    @FXML
    private TableColumn<Books, String> bookNameColumn;
    @FXML
    private TableColumn<Books, String> authorNameColumn;
    @FXML
    private TableColumn<Books, String> publisherNameColumn;
    @FXML
    private TableColumn<Books, Integer> pageNumberColumn;
    @FXML
    private TableColumn<Books, Integer> stockColumn;
    @FXML
    private TableColumn<Books, Integer> favColumn;
    @FXML
    private TableColumn<Books, String> bookIdColumn;
    
    @FXML 
    private ImageView searchBookButton;
    
    String id="";
    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    
    @FXML
    private void favAction(ActionEvent event){
        Books book =tableView.getSelectionModel().getSelectedItem();
        String bookID=book.getBookId();
        connection.SQLOperations("UPDATE books SET favCount = favCount + " + 1 +
        " WHERE bookId ="+bookID);
        book.setFavCount(book.getFavCount()+1);
        tableView.refresh();
       
       
       
    }
    @FXML
    private void clickedGoBack(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLHome.fxml")); // for going back to home page
        Scene scene=new Scene(root);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
       @FXML
    private void clickTableView(MouseEvent event) {
        Books book=tableView.getSelectionModel().getSelectedItem();
        if(book==null)
        {
            System.out.println("any product select");
        }
        else
        {
            bookImage.setImage(new Image("Images/"+book.getBookImage()));
            bookName.setText(book.getBookName());
            authorName.setText(book.getAuthorName());
            publisherName.setText(book.getPublisherName());
            pageNumber.setText(String.valueOf(book.getPageNumber()));
            favCount.setText(String.valueOf(book.getFavCount()));
            stok.setText(String.valueOf(book.getPageNumber()));
            System.out.println(book.getBookName()+" "+book.getAuthorName()+" "+book.getPublisherName()+" "+book.getPageNumber()+" "+book.getFavCount()+" "+book.getStock()+" "+book.getBookImage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        publisherNameColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        pageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        favColumn.setCellValueFactory(new PropertyValueFactory<>("favCount"));
     
        File file=new File("login.txt");
        try {
            Scanner scan=new Scanner(file);
            id=scan.next();
            scan.close();
            System.out.println("login borrow id = "+id);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableView.setItems(connection.getBookAccID(id));
    }    
    
}
