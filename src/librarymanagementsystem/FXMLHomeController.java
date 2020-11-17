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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Books;
/**
 *
 * @author Musibet
 */
public class FXMLHomeController implements Initializable {
        
    
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
    
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox VBLayout;
    @FXML
    private Label bookName1;
    @FXML
    private Label authorName1;
    @FXML
    private Label publisherName1;
    @FXML
    private Label pageNumber1;
    @FXML
    private Label favCount11;
    @FXML
    private Label stok1;
    @FXML
    private Button borrowButton;
    @FXML
    private HBox HBLayout;
    String id="";
    
    @FXML
    private ImageView adminOperate1;

    @FXML
    private ImageView adminOperate2;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        publisherNameColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        pageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        favColumn.setCellValueFactory(new PropertyValueFactory<>("favCount"));
        
        tableView.setItems(connection.getBook());
        
        File file=new File("login.txt");
        try {
            Scanner scan=new Scanner(file);
            id=scan.next();
            scan.close();
            System.out.println("login id = "+id);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(id.equals("admin")==false){
            adminOperate1.setVisible(false);
            adminOperate2.setVisible(false);
            
        
        }
        
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
    
    @FXML
    private void borrowAction(ActionEvent event){
       /* Books book =tableView.getSelectionModel().getSelectedItem();
        String bookID=book.getBookId();
        connection.SQLOperations("UPDATE books SET favCount = favCount + " + 1 +
        " WHERE bookId ="+bookID);
        book.setFavCount(book.getFavCount()+1);
        tableView.refresh();*/
       
       Books book=tableView.getSelectionModel().getSelectedItem();
       connection.SQLOperations("UPDATE books SET stock = stock - " + 1 +
       " WHERE bookId ="+book.getBookId());
       connection.SQLOperations("INSERT INTO borrowbookstable(id,bookId,time) VALUES("+"'"+id+"'"+","+"'"+book.getBookId()+"'"+","+15+")");
       
       
    }

    @FXML
    private void clickBookOperations(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLBookOperations.fxml"));
        Scene scene=new Scene(root);
    
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void clickMemberOperations(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLMemberOperations.fxml"));
        Scene scene=new Scene(root);
    
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void searchButtonAction(MouseEvent event) throws IOException{
    
        Parent root5=FXMLLoader.load(getClass().getResource("FXMLSearch.fxml"));
        Scene scene5=new Scene(root5);
    
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
        
    }
    
     @FXML
    private void favButtonAction(MouseEvent event) throws IOException{
    Parent root6=FXMLLoader.load(getClass().getResource("FXMLFavouriteBooks.fxml"));
    Scene scene6=new Scene(root6);
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();
    
    
    
    
    }
    
     @FXML
    private void borrowerButtonAction(MouseEvent event) throws IOException{
    Parent root6=FXMLLoader.load(getClass().getResource("BorrowedBooks.fxml"));
    Scene scene6=new Scene(root6);
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();
    
    
    
    
    }
    
    
    
    
}
