/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.pgood;

import java.lang.reflect.Array;
import java.util.ArrayList;
import models.PGood;
import java.util.Date;

/**
 *
 * @author vuaphapthuat410
 */

class BookCover {
    private String cover;

    public BookCover(String cover) {
        switch(cover) {
            case "hardcover":
                this.cover = cover;
                break;
            case "paperback":
                this.cover = cover;
                break;
        }
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        switch(cover) {
            case "hardcover":
                this.cover = cover;
                break;
            case "paperback":
                this.cover = cover;
                break;
        }
    }
}

public class Book extends PGood {
    private ArrayList<String> authors;
    private BookCover cover;
    private String publisher; // Need to refactor for future use. Use Dependency injection
    private Date pubDate;
    private Integer pages;
    private String lang;
    private String genre; // Need to refactor for future use. Use Dependency injection

    public Book(ArrayList<String> authors, BookCover cover, String publisher, Date pubDate, Integer pages, String lang, String genre, String barCode, String desc, Integer quantity, Date date, Integer dimX, Integer dimY, Integer weight, String itemId, String title, Integer value, Integer price, Integer unitSale) {
        super(barCode, desc, quantity, date, dimX, dimY, weight, itemId, title, value, price, unitSale); 
        this.authors = authors;
        this.cover = cover;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.pages = pages;
        this.lang = lang;
        this.genre = genre;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
    
    public boolean addAuthor(String author) {
        for (int i = 0; i < authors.size(); i++) 
            if(authors.get(i).equals(author)) {
                return false;
            }
        authors.add(author);
        return true;
    }
    
    public boolean removeAuthor(String author) {
        for (int i = 0; i< authors.size(); i++)
            if(authors.get(i).equals(author)) {
                authors.remove(i);
                return true;
            }
        return false;
    }

    public String getCover() {
        return cover.getCover();
    }

    public void setCover(String cover) {
        this.cover.setCover(cover);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
}
