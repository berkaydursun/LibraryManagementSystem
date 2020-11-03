/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BERKAY
 */
public class Member {
    
    private String ID;
    private String password;
    private String firstName;
    private String lastName;
    private String birth;
    private String mail;
    private String tel;
    private int borrowingNumber;

    public Member(String ID, String password, String firstName, String lastName, String birth, String mail, String tel) {
        this.ID = ID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.mail = mail;
        this.tel = tel;
    
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getBorrowingNumber() {
        return borrowingNumber;
    }

    public void setBorrowingNumber(int borrowingNumber) {
        this.borrowingNumber = borrowingNumber;
    }
    
    
    
    
    
    
}
