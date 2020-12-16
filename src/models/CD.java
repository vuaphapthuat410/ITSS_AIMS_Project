package models;

public class CD extends PhysicalGood{
    private String artist;
    private String record_label;
    private String publication_date;
    private String genre;

    public CD(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre) {
        super(id, title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
    }

    public CD(String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String artist, String record_label, String publication_date, String genre) {
        super(title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.artist = artist;
        this.record_label = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
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
}
