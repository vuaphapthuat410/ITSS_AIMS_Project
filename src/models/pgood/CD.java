/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.pgood;

import java.util.ArrayList;
import java.util.Date;
import models.PGood;
import models.other.Track;

/**
 *
 * @author vuaphapthuat410
 */
public class CD extends PGood {
    private ArrayList<String> artirts;
    private String recordLabel; // Need to refactor for future use. Use Dependency injection
    private ArrayList<Track> tracks;
    private String genre; // Need to refactor for future use. Use Dependency injection
    private Date pubDate;  

    public CD(ArrayList<String> artirts, String recordLabel, ArrayList<Track> tracks, String genre, Date pubDate, String barCode, String desc, Integer quantity, Date date, Integer dimX, Integer dimY, Integer weight, String itemId, String title, Integer value, Integer price, Integer unitSale) {
        super(barCode, desc, quantity, date, dimX, dimY, weight, itemId, title, value, price, unitSale);
        this.artirts = artirts;
        this.recordLabel = recordLabel;
        this.tracks = tracks;
        this.genre = genre;
        this.pubDate = pubDate;
    }

    public ArrayList<String> getArtirts() {
        return artirts;
    }

    public void setArtirts(ArrayList<String> artirts) {
        this.artirts = artirts;
    }
    
    public boolean addArtist(String artist) {
        for (int i = 0; i < artirts.size(); i++) 
            if(artirts.get(i).equals(artist)) {
                return false;
            }
        artirts.add(artist);
        return true;
    }
    
    public boolean removeArtist(String artist) {
        for (int i = 0; i< artirts.size(); i++)
            if(artirts.get(i).equals(artist)) {
                artirts.remove(i);
                return true;
            }
        return false;
    }
    
    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
    
    public boolean addTrack(Track track) {
        for (int i = 0; i < tracks.size(); i++) 
            if(tracks.get(i).equals(track)) {
                return false;
            }
        tracks.add(track);
        return true;
    }
    
    public boolean removeTrack(Track track) {
        for (int i = 0; i< tracks.size(); i++)
            if(tracks.get(i).equals(track)) {
                tracks.remove(i);
                return true;
            }
        return false;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
    
    
}
