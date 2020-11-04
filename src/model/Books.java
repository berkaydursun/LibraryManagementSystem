/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Musibet
 */
public class Books {
    private String bookId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private int pageNumber;
    private int stock;
    private int favCount;
    private String bookImage;
    
    public Books()
    {
        this.bookId="";
        this.bookName="";
        this.authorName="";
        this.publisherName="";
        this.pageNumber=0;
        this.stock=0;
        this.favCount=0;
        this.bookImage="";
    }
    
    public Books(String bookId,String bookName,String authorName, String publisherName, int pageNumber, int stock, int favCount, String bookImage)
    {
        this.bookId=bookId;
        this.bookName=bookName;
        this.authorName=authorName;
        this.publisherName=publisherName;
        this.pageNumber=pageNumber;
        this.stock=stock;
        this.favCount=favCount;
        this.bookImage=bookImage;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    
    
}
