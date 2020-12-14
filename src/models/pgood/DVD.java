/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.pgood;
import java.util.ArrayList;
import java.util.Date;
import models.PGood;

/**
 *
 * @author vuaphapthuat410
 */

class DVDType {
    private String type;

    public DVDType(String type) {
        switch(type) {
            case "Blu-ray":
                this.type = type;
                break;
            case "HD-DVD":
                this.type = type;
                break;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        switch(type) {
            case "Blu-ray":
                this.type = type;
                break;
            case "HD-DVD":
                this.type = type;
                break;
        }
    }
}

public class DVD extends PGood {
    private DVDType type;
    private ArrayList<String> directors;
    private Integer runtime;
    private String studio; // Need to refactor for future use. Use Dependency injection
    private String lang;
    private String sub; // Need to refactor for future use. Use Dependency injection
    private Date pubDate;  
    private String genre; // Need to refactor for future use. Use Dependency injection

    public DVD(DVDType type, ArrayList<String> directors, Integer runtime, String studio, String lang, String sub, Date pubDate, String genre, String barCode, String desc, Integer quantity, Date date, Integer dimX, Integer dimY, Integer weight, String itemId, String title, Integer value, Integer price, Integer unitSale) {
        super(barCode, desc, quantity, date, dimX, dimY, weight, itemId, title, value, price, unitSale);
        this.type = type;
        this.directors = directors;
        this.runtime = runtime;
        this.studio = studio;
        this.lang = lang;
        this.sub = sub;
        this.pubDate = pubDate;
        this.genre = genre;
    }

    public DVDType getType() {
        return type;
    }

    public void setType(DVDType type) {
        this.type = type;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
}
