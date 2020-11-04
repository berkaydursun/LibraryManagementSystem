/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Books;
/**
 *
 * @author Musibet
 */
public class FXMLHomeController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox VBLayout;
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
    private Button favButton;
    @FXML
    private HBox HBLayout;
    @FXML
    private TableView<Books> tableView;
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
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    
   
    
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
            bookName.setText(book.getBookName());
            authorName.setText(book.getAuthorName());
            publisherName.setText(book.getPublisherName());
            pageNumber.setText(String.valueOf(book.getPageNumber()));
            favCount.setText(String.valueOf(book.getFavCount()));
            stok.setText(String.valueOf(book.getPageNumber()));
            System.out.println(book.getBookName()+" "+book.getAuthorName()+" "+book.getPublisherName()+" "+book.getPageNumber()+" "+book.getFavCount()+" "+book.getStock()+" "+book.getBookImage());
        }
    }
    
}
