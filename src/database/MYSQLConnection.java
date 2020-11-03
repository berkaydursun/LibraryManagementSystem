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
         public static MYSQLConnection getInstance(){
    if(instance==null){
    synchronized (MYSQLConnection.class) {
        if (instance==null) {
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
    
    
    //Adding data to database 
    public void add(String query){
        try {
            
            statement=con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Successfully added");
        } catch (SQLException ex) {
            Logger.getLogger(MYSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
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
    
    
    
    
    
    
}
