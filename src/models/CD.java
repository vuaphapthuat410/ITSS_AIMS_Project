package models;

import java.util.ArrayList;

public class CD extends PhysicalGood{
    private String artist;
    private String record_label;
    private String publication_date;
    private String genre;
    private ArrayList<Track> track_list;


    //without track list
    public CD(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre) {
        super(id, title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list =  new ArrayList<>();
    }

    public CD(String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre) {
        super(title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list =  new ArrayList<>();

    }

    //with track list


    public CD(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre, ArrayList<Track> track_list) {
        super(id, title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list = track_list;
    }

    public CD(String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre, ArrayList<Track> track_list) {
        super(title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
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

    public ArrayList<Track> getTrack_list() {
        return track_list;
    }

    public void setTrack_list(ArrayList<Track> track_list) {
        this.track_list = track_list;
    }

    public void addTrack(Track track){
        this.track_list.add(track);
    }

    public void removeTrackList(){
        this.track_list = new ArrayList<>();
    }
}
