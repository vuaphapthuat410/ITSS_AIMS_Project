package models;

public class DVD extends PhysicalGood{
    private String type;
    private String director;
    private int runtime;
    private String studio;
    private String language;
    private String subtitle;
    private String publication_date;
    private String genre;

    public DVD(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension, int weight, String type, String director, int runtime, String studio, String language, String subtitle, String publication_date, String genre) {
        super(id, title, value, price, unit_sale, category, barcode, description, quantity, date, dimension, weight);
        this.type = type;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
        this.subtitle = subtitle;
        this.publication_date = publication_date;
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
