/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    public void signUpButtonaction(ActionEvent event){
    //Object Created by using form infos
    member=new Member(memberID.getText(),memberPassword.getText(),memberFName.getText(),
    memberLName.getText(),memberBirth.getValue().toString(),memberMail.getText(),memberTel.getText());
    connection.add("INSERT INTO member(id,password,fname,lname,birth,mail,tel,borrowBookNum) VALUES ("+"'"+member.getID()+"'"+","+"'"+
            member.getPassword()+"'"+","+"'"+member.getFirstName()+"'"+","+"'"+
            member.getLastName()+"'"+","+"'"+member.getBirth()+"'"+","+"'"+ 
            member.getMail()+"'"+","+"'"+
            member.getTel()+"'"+","+0+")");
        
    
    
    
    }
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
