/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Musibet
 */
public class FXMLEditBookPageController implements Initializable {

    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    @FXML
    private Label editBookIdLabel;
    @FXML
    private TextField editBookIdTxt;
    @FXML
    private Label editBookNameLabel;
    @FXML
    private TextField editBookNameTxt;
    @FXML
    private Label editAuthorNameLabel;
    @FXML
    private TextField editAutohorNameTxt;
    @FXML
    private Label editPubNameLabel;
    @FXML
    private TextField editPubNameTxt;
    @FXML
    private Label editPageNumLabel;
    @FXML
    private TextField editPageNumTxt;
    @FXML
    private Label editStockLabel;
    @FXML
    private TextField editStockTxt;
    @FXML
    private Label editBookImgLabel;
    @FXML
    private Button editChooseImgButton;
    
    private String bookId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private int pageNumber;
    private int stock;
    private String bookImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void oldValues(String bookId,String bookName,String authorName,String publisherName,int pageNumber,int stock,String bookImage) // for using old values
    {
        this.bookId=bookId;
        this.bookName=bookName;
        this.authorName=authorName;
        this.publisherName=publisherName;
        this.pageNumber=pageNumber;
        this.stock=stock;
        this.bookImage=bookImage;
        setTextOldValues();
    }
    public void setTextOldValues() // for writing textfield old values of book
    {
        editBookIdTxt.setText(bookId);
        editBookNameTxt.setText(bookName);
        editAutohorNameTxt.setText(authorName);
        editPubNameTxt.setText(publisherName);
        editPageNumTxt.setText(String.valueOf(pageNumber));
        editStockTxt.setText(String.valueOf(stock));
    }
    @FXML
    private void clickChooseImage(MouseEvent event) { // for defining new photo for book
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File f = fc.showOpenDialog(null);
        
        if(f!=null)
        {
            bookImage=f.getName();
            System.out.println("Selected File : "+f.getName());
            
            Path movefrom = FileSystems.getDefault().getPath(f.getAbsolutePath());  // for moving image to Images folder.
            Path target = FileSystems.getDefault().getPath("src/Images/"+f.getName());
            try{
                Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
                System.out.println("Image successfully moved to Images folder.");
            }catch (IOException e){
                System.out.println("Image dont moved to Images folder.");
            }
        }
    }
    @FXML
    private void clickEditBook(MouseEvent event) throws IOException {
        if(editBookIdTxt.getText().equals("") || editBookNameTxt.getText().equals("") || editAutohorNameTxt.getText().equals("") || editPubNameTxt.getText().equals("") || editPageNumTxt.getText().equals("") || editStockTxt.getText().equals(""))
        {
            errorMessage("Please fill all text fields!");
            return;
        }
        connection.SQLOperations("UPDATE books SET bookId="+"'"+editBookIdTxt.getText()+"'"+
                ", bookName="+"'"+editBookNameTxt.getText()+"'"+
                ", authorName="+"'"+editAutohorNameTxt.getText()+"'"+
                ", publisherName="+"'"+editPubNameTxt.getText()+"'"+
                ", pageNumber="+editPageNumTxt.getText()+
                ", bookImage="+"'"+bookImage+"'"+
                ", stock="+editStockTxt.getText()+
                " WHERE bookId="+"'"+bookId+"'");
        
        successMessage("Book Successfully Updated!");
        
        Parent root=FXMLLoader.load(getClass().getResource("FXMLBookOperations.fxml")); // for going back to book operations page
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
    private void successMessage(String successMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }
    
}
