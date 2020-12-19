package models;

import java.util.ArrayList;

public class Album extends Item{
    private String artist;
    private String record_label;
    private String publication_date;
    private String genre;
    private ArrayList<AlbumTrack> track_list;


    public Album(int id, String title, int value, int price, int unit_sale, String category, String artist, String record_label, String publication_date, String genre) {
        super(id, title, value, price, unit_sale, category);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list = new ArrayList<>();
    }

    public Album(String title, int value, int price, int unit_sale, String category, String artist, String record_label, String publication_date, String genre) {
        super(title, value, price, unit_sale, category);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list = new ArrayList<>();
    }

    public Album(int id, String title, int value, int price, int unit_sale, String category, String artist, String record_label, String publication_date, String genre, ArrayList<AlbumTrack> track_list) {
        super(id, title, value, price, unit_sale, category);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list = track_list;
    }

    public Album(String title, int value, int price, int unit_sale, String category, String artist, String record_label, String publication_date, String genre, ArrayList<AlbumTrack> track_list) {
        super(title, value, price, unit_sale, category);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list = track_list;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecord_label() {
        return record_label;
    }

    public void setRecord_label(String record_label) {
        this.record_label = record_label;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<AlbumTrack> getTrack_list() {
        return track_list;
    }

    public void setTrack_list(ArrayList<AlbumTrack> track_list) {
        this.track_list = track_list;
    }

    public void addTrack(AlbumTrack track){
        this.track_list.add(track);
    }

    public void removeTrackList(){
        this.track_list = new ArrayList<>();
    }
}
