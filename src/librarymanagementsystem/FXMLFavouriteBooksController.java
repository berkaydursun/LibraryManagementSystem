/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Books;

/**
 * FXML Controller class
 *
 * @author BERKAY
 */
public class FXMLFavouriteBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label kitapth;

    @FXML
    private Label kitapone;

    @FXML
    private Label kitaptwo;

    @FXML
    private ImageView kitapOneImage;
    
    @FXML
    private ImageView kitapTwoImage;
    
    @FXML
    private ImageView kitapThreeImage;
    
     @FXML
    private Label favOne;

    @FXML
    private Label favTwo;

    @FXML
    private Label favThree;
    
    /*
    bookImage.setImage(new Image("Images/"+book.getBookImage()));
    */
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    public ObservableList<Books> booksList=connection.getBooks();
    
    @FXML
    private void clickedGoBack(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLHome.fxml")); // for going back to home page
        Scene scene=new Scene(root);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    kitapone.setText(booksList.get(0).getBookName());
    kitapOneImage.setImage(new Image("Images/"+booksList.get(0).getBookImage()));
    favOne.setText(String.valueOf(booksList.get(0).getFavCount()));
    
    kitaptwo.setText(booksList.get(1).getBookName());
    kitapTwoImage.setImage(new Image("Images/"+booksList.get(1).getBookImage()));
    favTwo.setText(String.valueOf(booksList.get(1).getFavCount()));
    
    kitapth.setText(booksList.get(2).getBookName());
    kitapThreeImage.setImage(new Image("Images/"+booksList.get(2).getBookImage()));
    favThree.setText(String.valueOf(booksList.get(2).getFavCount()));
    
    }    
    
}
