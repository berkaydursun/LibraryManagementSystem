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
public class FXMLAddBookPageController implements Initializable {

    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    @FXML
    private TextField addBookIdTxt;
    @FXML
    private TextField addBookNameTxt;
    @FXML
    private TextField addAutohorNameTxt;
    @FXML
    private TextField addPubNameTxt;
    @FXML
    private TextField addPageNumTxt;
    @FXML
    private TextField addStockTxt;
    
    private String chooseImageName="";
    @FXML
    private Label addBookIdLabel;
    @FXML
    private Label addBookNameLabel;
    @FXML
    private Label addAuthorNameLabel;
    @FXML
    private Label addPubNameLabel;
    @FXML
    private Label addPageNumLabel;
    @FXML
    private Label addStockLabel;
    @FXML
    private Label addBookImgLabel;
    @FXML
    private Button chooseImgButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickAddBook(MouseEvent event) throws IOException {
        if(addBookIdTxt.getText().equals("") || addBookNameTxt.getText().equals("") || addAutohorNameTxt.getText().equals("") || addPubNameTxt.getText().equals("") || addPageNumTxt.getText().equals("") || addStockTxt.getText().equals("") || chooseImageName.equals(""))
        {
            errorMessage("Please fill all text fields!");
            return;
        }
        connection.SQLOperations("INSERT INTO books(bookId,bookName,authorName,publisherName,pageNumber,favCount,bookImage,stock) VALUES("+
                "'"+addBookIdTxt.getText()+"'"+","+"'"+addBookNameTxt.getText()+"'"+
                ","+"'"+addAutohorNameTxt.getText()+"'"+","+"'"+addPubNameTxt.getText()+"'"+
                ","+"'"+addPageNumTxt.getText()+"'"+","+0+","+"'"+chooseImageName+"'"+","+
                addStockTxt.getText()+");");
        
        successMessage("Book Successfully Added!");
        
        Parent root=FXMLLoader.load(getClass().getResource("FXMLBookOperations.fxml")); // for going back to book operations page
        Scene scene=new Scene(root);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void clickChooseImage(MouseEvent event) {   // for choosing image of book.
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File f = fc.showOpenDialog(null);
        
        if(f!=null)
        {
            chooseImageName=f.getName();
            System.out.println("Selected File : "+f.getName());
            
            Path movefrom = FileSystems.getDefault().getPath(f.getAbsolutePath());  // for moving image to Images folder.
            Path target = FileSystems.getDefault().getPath("Images/"+f.getName());
            try{
                Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
                System.out.println("Image successfully moved to Images folder.");
            }catch (IOException e){
                System.out.println("Image dont moved to Images folder.");
            }
        }
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
        alert.setTitle("success");
        alert.setHeaderText(null);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }
    
}
