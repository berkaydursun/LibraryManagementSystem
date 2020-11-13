/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Books;
import model.Member;

/**
 *
 * @author BERKAY
 */
public class MYSQLConnection {
    private String username="root";
    private String password="";

    private String dbName="library_management_system";
    private String host="localhost";
    private int port=3306;
    
    private Connection con=getConnection();
    
    private Statement statement=null;
    
    private static MYSQLConnection instance=null;

    private MYSQLConnection(){
    }
    
    public static MYSQLConnection getInstance()
    {
        if(instance==null)
        {
            synchronized (MYSQLConnection.class) 
            {
                if (instance==null) 
                {
                    instance=new MYSQLConnection();
                }
            }
        }
        return instance;
    }
         
    public Connection getConnection(){
      	 String url="jdbc:mysql://"+host+":"+port+"/"+dbName;
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("No Found Driver");
        }
        
        try {
            con=(Connection) DriverManager.getConnection(url,username,password);
            System.out.println("Connection Succesfull");
            
        } catch (SQLException ex) {
            System.out.println("Connection Unsuccessfull");
        }
    
        return con;
	
    }
    
    
    // ******** MYSQL OPERATIONS *********
    
    
    
    //Controlling login according to database member infos
    public boolean controlLogin(String id,String password){
    String query="Select id,password From member";
        try {
          statement=con.createStatement();           
          ResultSet rs=  statement.executeQuery(query);  
          while(rs.next()){
            String member_id=rs.getString("id");
            String member_password=rs.getString("password");
            if(member_id.equals(id)&&member_password.equals(password)){
               return true;
            }
          }  
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //writing books on table view from database.
    public ObservableList<Books> getBook()
    {
        ObservableList<Books> books = FXCollections.observableArrayList();
        String query="Select * From books";
        try {
          statement = con.createStatement();           
          ResultSet rs =  statement.executeQuery(query);  
          
          while(rs.next()){
              
              String bookId=rs.getString("bookId");
              String bookName=rs.getString("bookName");
              String authorName=rs.getString("authorName");
              String publisherName=rs.getString("publisherName");
              int pageNumber=Integer.parseInt(rs.getString("pageNumber"));
              int favCount=Integer.parseInt(rs.getString("favCount"));
              String bookImage=rs.getString("bookImage");
              int stock=Integer.parseInt(rs.getString("stock"));
              
              books.add(new Books(bookId,bookName,authorName,publisherName,pageNumber,stock,favCount,bookImage));//creating book object
              
          }  
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
    
    public boolean controlID(String id){
        String query="SELECT id FROM member";
        
        try {
                statement=con.createStatement();           
                ResultSet rs=  statement.executeQuery(query);  
                while(rs.next()){
                String ID=rs.getString("id");
                if(id.equals(ID)){
                    return true;
                }
                
                }
          
        }   catch (SQLException ex) {
                Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;

        }
    
    // all sql operation add update delete 
    public void SQLOperations(String query){
        try {   
            statement=con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Operations done successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Member> getMember()
    {
        ObservableList<Member> member = FXCollections.observableArrayList();
        String query="Select * From member";
        try {
          statement = con.createStatement();           
          ResultSet rs =  statement.executeQuery(query);  
          
          while(rs.next()){
              
              String memberId=rs.getString("id");
              String memberPassword=rs.getString("password");
              String memberName=rs.getString("fname");
              String memberSurname=rs.getString("lname");
              String memberBirthDate=rs.getString("birth");
              String memberEmail=rs.getString("mail");
              String memberPhone=rs.getString("tel");
              int memberBorrowingNumber=rs.getInt("borrowBookNum");
              
              member.add(new Member(memberId,memberPassword,memberName,memberSurname,memberBirthDate,memberEmail,memberPhone,memberBorrowingNumber));//creating member object
              
          }  
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }
    
    
    
    
    
}
