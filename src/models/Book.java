package models;


public class Book extends PhysicalGood {
    private String author;
    private String cover;
    private String publisher;
    private String publication_date;
    private int page;
    private String language;
    private String genre;

    public Book(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String author, String cover, String publisher, String publication_date, int page, String language, String genre) {
        super(id, title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.author = author;
        this.cover = cover;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page = page;
        this.language = language;
        this.genre = genre;
    }

    public Book(String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight, String author, String cover, String publisher, String publication_date, int page, String language, String genre) {
        super(title, value, price, unit_sale, category, barcode, description, quantity, date, dimension_x, dimension_y, dimension_z, weight);
        this.author = author;
        this.cover = cover;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page = page;
        this.language = language;
        this.genre = genre;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}