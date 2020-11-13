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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Member;

/**
 * FXML Controller class
 *
 * @author BERKAY
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Object Reference Created;
    Member member;
    //Singleton Object
    MYSQLConnection connection=MYSQLConnection.getInstance();
    boolean control=true;
    
    
    @FXML
    private TextField memberID;

    @FXML
    private TextField memberPassword;

    @FXML
    private TextField memberFName;

    @FXML
    private TextField memberLName;

    @FXML
    private DatePicker memberBirth;

    @FXML
    private TextField memberMail;

    @FXML
    private TextField memberTel;

    @FXML 
    private Label idControlLabel;
    @FXML
    private Label passwordControl;
    
    @FXML
    public void signUpButtonaction(ActionEvent event) throws IOException{
    //Object Created by using form infos
    member=new Member(memberID.getText(),memberPassword.getText(),memberFName.getText(),
    memberLName.getText(),memberBirth.getValue().toString(),memberMail.getText(),memberTel.getText(),0);
    if(control==true){
        JOptionPane.showMessageDialog(null,"Successfully Registered !");
    
    connection.SQLOperations("INSERT INTO member(id,password,fname,lname,birth,mail,tel,borrowBookNum) VALUES ("+"'"+member.getID()+"'"+","+"'"+
            member.getPassword()+"'"+","+"'"+member.getFirstName()+"'"+","+"'"+
            member.getLastName()+"'"+","+"'"+member.getBirth()+"'"+","+"'"+ 
            member.getMail()+"'"+","+"'"+
            member.getTel()+"'"+","+0+")");
    
        Parent root2=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2=new Scene(root2);
    
    
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    
    
    
    
    }
    else{
    JOptionPane.showMessageDialog(null, "Please fill the form correctly.");
    }
    
    
    
    }
    
  
    
    @FXML
    private void idControl(KeyEvent event){
       if(connection.controlID(memberID.getText())==true){
        idControlLabel.setText("ID has already been taken.Please try different ID.");
        idControlLabel.setStyle("-fx-text-fill:red");
        control=false;
        }
        
        else{
        idControlLabel.setText("");
        control=true;
        }
    
    
    }
    
    @FXML
    private void passwordControl(KeyEvent event){
      if(memberPassword.getText().length()>11){
        passwordControl.setText("Password should be max 12 character");
        passwordControl.setStyle("-fx-text-fill:red");
        control=false;
        }
        else if(memberPassword.getText().length()<=11){
            passwordControl.setText("");
        control=true;
        }
    
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
