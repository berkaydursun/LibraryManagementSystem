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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Books;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Musibet
 */
public class FXMLMemberOperationsController implements Initializable {

    @FXML
    private ImageView deleteMemberButton;
    @FXML
    private ImageView editMemberButton;
    @FXML
    private ImageView goBackMemberButton;
    @FXML
    private TableView<Member> tableMemberOperations;
    @FXML
    private TableColumn<Member, String> memberIdColumn;
    @FXML
    private TableColumn<Member, String> memberNameColumn;
    @FXML
    private TableColumn<Member, String> memberSurnameColumn;
    @FXML
    private TableColumn<Member, String> memberBirthDateColumn;
    @FXML
    private TableColumn<Member, String> memberEmailColumn;
    @FXML
    private TableColumn<Member, String> memberPhoneColumn;
    @FXML
    private TableColumn<Member, Integer> memberBorrowColumn;
    
    MYSQLConnection connection=MYSQLConnection.getInstance();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        ID,firstName,lastName,birth,mail,tel;
        */
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        memberSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        memberBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        memberEmailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        memberPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        memberBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("borrowingNumber"));
        
        tableMemberOperations.setItems(connection.getMember());
    }    

    @FXML
    private void clickDeleteMember(MouseEvent event) {
        ObservableList<Member> memberSelected, allMember;
        allMember = tableMemberOperations.getItems();
        memberSelected = tableMemberOperations.getSelectionModel().getSelectedItems();
        Member selectedMemberId = tableMemberOperations.getSelectionModel().getSelectedItem();
        
        if(selectedMemberId==null)
        {
            errorMessage("You have to choose some member to deleting.");
            return;
        }
        
        int reply = JOptionPane.showConfirmDialog(null, 
                "Are you sure want to delete the member?",
                "LIBRARY SYSTEM", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            connection.SQLOperations("DELETE FROM member WHERE id='"+selectedMemberId.getID()+"';");
            memberSelected.forEach(allMember::remove);   // for removing selected book from table.
            JOptionPane.showMessageDialog(null, "Book deleted successfully.");
        } else {
            return;
        }
    }

    @FXML
    private void clickEditMember(MouseEvent event) {
        Member member=tableMemberOperations.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditMemberPage.fxml"));
        try {   
            if(member==null)
            {
                errorMessage("You have to choose some member to updating information.");
                return;
            }
            Parent root=loader.load();
            Scene scene=new Scene(root);
            FXMLEditMemberPageController editPageCont = loader.getController(); // for sending old values to edit book page
            editPageCont.oldValues(member.getID(), member.getFirstName(), member.getLastName(), member.getBirth(), member.getMail(), member.getTel(), member.getBorrowingNumber());      

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            
        } catch (Exception e) {
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

    @FXML
    private void clickGoBackMember(MouseEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLHome.fxml")); // for going back to home page
        Scene scene=new Scene(root);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
}
