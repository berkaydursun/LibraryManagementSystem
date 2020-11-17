/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Captcha;

/**
 *
 * @author BERKAY
 */
public class FXMLDocumentController implements Initializable {
    
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    @FXML
    AnchorPane imagePane;
    
    @FXML
    Button signup;
    
    @FXML
    TextField id;
    @FXML
    TextField password;
    @FXML
    TextField captchaText;
    
    
    @FXML
    Label captchaLabel;
    
    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
    
        Parent root2=FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene2=new Scene(root2);
    
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

        
    }
    
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException{
     
        
        if(connection.controlLogin(id.getText(), password.getText())==true){
        JOptionPane.showMessageDialog(null, "WELCOME ! ");
            File file=new File("login.txt");
            FileWriter fw;
            try{
            fw=new FileWriter(file);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(id.getText());
          
           
            bw.close();
            fw.close();
          

            }
            catch(Exception ex){
            ex.printStackTrace();

            }        
        
            Parent root3=FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene scene3=new Scene(root3);


            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene3);
            window.show();
         
        }
        else{
        JOptionPane.showMessageDialog(null, "ID OR PASSWORD WRONG. TRY AGAIN ! ");

        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String Cpt = new Captcha().getCaptcha();
        captchaLabel.setText(Cpt);
        
        
        
        
        
        
        
        
    }    
    
}
