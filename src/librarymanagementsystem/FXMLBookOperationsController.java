/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Books;

/**
 * FXML Controller class
 *
 * @author Musibet
 */
public class FXMLBookOperationsController implements Initializable {

    @FXML
    private TableView<Books> tableBookOperations;
    @FXML
    private TableColumn<Books, String> bookIdColumn;
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
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    
    @FXML
    private ImageView addBookButton;
    @FXML
    private ImageView deleteBookButton;
    @FXML
    private ImageView editBookButton;
    @FXML
    private ImageView goBackButton;

    /**
     * Initializes the controller class.
     */
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
        
        tableBookOperations.setItems(connection.getBook());
        
    }    

    @FXML
    private void clickedAddBookPage(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLAddBookPage.fxml"));
        Scene scene=new Scene(root);
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void clickedDeleteBook(MouseEvent event) {
        ObservableList<Books> bookSelected, allBook;
        allBook = tableBookOperations.getItems();
        bookSelected = tableBookOperations.getSelectionModel().getSelectedItems();
        Books selectedBookId = tableBookOperations.getSelectionModel().getSelectedItem();
        
        if(selectedBookId==null)
        {
            errorMessage("You have to choose some book to deleting.");
            return;
        }
        
        int reply = JOptionPane.showConfirmDialog(null, 
                "Are you sure want to delete the book?",
                "LIBRARY SYSTEM", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            connection.SQLOperations("DELETE FROM books WHERE bookId='"+selectedBookId.getBookId()+"';");
            bookSelected.forEach(allBook::remove);   // for removing selected book from table.
            JOptionPane.showMessageDialog(null, "Book deleted successfully.");
        } else {
            return;
        }
        
    }

    @FXML
    private void clickedEditBook(MouseEvent event) throws IOException {
        Books book=tableBookOperations.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditBookPage.fxml"));
        try {   
            if(book==null)
            {
                errorMessage("You have to choose some book to updating information.");
                return;
            }
            Parent root=loader.load();
            Scene scene=new Scene(root);
            FXMLEditBookPageController editPageCont = loader.getController(); // for sending old values to edit book page
            editPageCont.oldValues(book.getBookId(), book.getBookName(), book.getAuthorName(), book.getPublisherName(), book.getPageNumber(), book.getStock(), book.getBookImage());      

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            
        } catch (Exception e) {
        }
    }

    @FXML
    private void clickedGoBack(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLHome.fxml")); // for going back to home page
        Scene scene=new Scene(root);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    private void errorMessage(String errorMessage)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
    
}
