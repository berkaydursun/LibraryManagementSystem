/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import database.MYSQLConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Musibet
 */
public class FXMLEditMemberPageController implements Initializable {

    MYSQLConnection connection=MYSQLConnection.getInstance();
    
    @FXML
    private Label editMemberIdLabel;
    @FXML
    private TextField editMemberIdTxt;
    @FXML
    private Label editMemberNameLabel;
    @FXML
    private TextField editMemberNameTxt;
    @FXML
    private Label editMemberSurnameLabel;
    @FXML
    private TextField editMemberSurnameTxt;
    @FXML
    private Label editMemberBirthLabel;
    @FXML
    private TextField editMemberBirthTxt;
    @FXML
    private Label editMemberMailLabel;
    @FXML
    private TextField editMemberMailTxt;
    @FXML
    private Label editMemberPhoneLabel;
    @FXML
    private TextField editMemberPhoneTxt;
    @FXML
    private Label editBorrowNumLabel;
    @FXML
    private TextField editBorrowNumTxt;
    
    private String memberId;
    private String memberName;
    private String memberSurname;
    private String memberBirth;
    private String memberMail;
    private String memberPhone;
    private int memberBorrowNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void oldValues(String memberId,String memberName,String memberSurname,String memberBirth,String memberMail,String memberPhone,int memberBorrowNumber) // for using old values
    {
        this.memberId=memberId;
        this.memberName=memberName;
        this.memberSurname=memberSurname;
        this.memberBirth=memberBirth;
        this.memberMail=memberMail;
        this.memberPhone=memberPhone;
        this.memberBorrowNumber=memberBorrowNumber;
        setTextOldValues();
    }
    public void setTextOldValues() // for writing textfield old values of book
    {
        editMemberIdTxt.setText(memberId);
        editMemberNameTxt.setText(memberName);
        editMemberSurnameTxt.setText(memberSurname);
        editMemberBirthTxt.setText(memberBirth);
        editMemberMailTxt.setText(memberMail);
        editMemberPhoneTxt.setText(memberPhone);
        editBorrowNumTxt.setText(String.valueOf(memberBorrowNumber));
    }
    
    @FXML
    private void clickEditMember(MouseEvent event) throws IOException{
        if(editMemberIdTxt.getText().equals("") || editMemberNameTxt.getText().equals("") || editMemberSurnameTxt.getText().equals("") || editMemberBirthTxt.getText().equals("") || editMemberMailTxt.getText().equals("") || editMemberPhoneTxt.getText().equals("") || editBorrowNumTxt.getText().equals(""))
        {
            errorMessage("Please fill all text fields!");
            return;
        }
        connection.SQLOperations("UPDATE member SET id="+"'"+editMemberIdTxt.getText()+"'"+
                ", fname="+"'"+editMemberNameTxt.getText()+"'"+
                ", lname="+"'"+editMemberSurnameTxt.getText()+"'"+
                ", birth="+"'"+editMemberBirthTxt.getText()+"'"+
                ", mail="+"'"+editMemberMailTxt.getText()+"'"+
                ", tel="+"'"+editMemberPhoneTxt.getText()+"'"+
                ", borrowBookNum="+editBorrowNumTxt.getText()+
                " WHERE id="+"'"+memberId+"'");
        
        successMessage("Member Successfully Updated!");
        
        Parent root=FXMLLoader.load(getClass().getResource("FXMLMemberOperations.fxml")); // for going back to book operations page
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
