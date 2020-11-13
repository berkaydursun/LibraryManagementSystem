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
import java.util.prefs.Preferences;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Books;

/**
 * FXML Controller class
 *
 * @author BERKAY
 */
public class FXMLSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Books> tableView;
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
    
    @FXML
    private TextField filterField;
    
    @FXML
    private Label bookName;

    @FXML
    private Label authorName;

    @FXML
    private Label publisherName;

    @FXML
    private Label pageNumber;

    @FXML
    private Label stockNumber;

    @FXML
    private Label favNumber;
    
   
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    Preferences userPreferences = Preferences.userRoot();
    
    @FXML
    private void borrowBookAction(ActionEvent event){
    Books book=tableView.getSelectionModel().getSelectedItem();
    connection.update("UPDATE books SET stock = stock - " + 1 +
    " WHERE bookId ="+book.getBookId());
   
    connection.add("INSERT INTO borrowbooks(id,bookId,time) VALUES("+"'"+1+"'"+","+"'"+book.getBookId()+"'"+","+0+")");
    
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
            favNumber.setText(String.valueOf(book.getFavCount()));
            stockNumber.setText(String.valueOf(book.getPageNumber()));
            System.out.println(book.getBookName()+" "+book.getAuthorName()+" "+book.getPublisherName()+" "+book.getPageNumber()+" "+book.getFavCount()+" "+book.getStock()+" "+book.getBookImage());
        }
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("girmiyoo");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        publisherNameColumn.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        pageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        favColumn.setCellValueFactory(new PropertyValueFactory<>("favCount"));
        
   
        
      
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Books> filteredData = new FilteredList<>(connection.getBook(), b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(book -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (book.getBookName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches book name.
				} else if (book.getPublisherName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches publisher name.
				}
				else if (book.getAuthorName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
				     return true;} // Filter matches author name.
				     else  {
				    	 return false;} // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Books> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
        
        
        
        
        
        
    }    
    
}
