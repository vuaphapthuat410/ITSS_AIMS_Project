package models;

public class Ebook extends Item{
    private String author;
    private String cover;
    private String publisher;
    private String publication_date;
    private int page;
    private String language;
    private String genre;
    private String content;

    public Ebook(int id, String title, int value, int price, int unit_sale, String category, String author, String cover, String publisher, String publication_date, int page, String language, String genre, String content) {
        super(id, title, value, price, unit_sale, category);
        this.author = author;
        this.cover = cover;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page = page;
        this.language = language;
        this.genre = genre;
        this.content = content;
    }

    public Ebook(String title, int value, int price, int unit_sale, String category, String author, String cover, String publisher, String publication_date, int page, String language, String genre, String content) {
        super(title, value, price, unit_sale, category);
        this.author = author;
        this.cover = cover;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page = page;
        this.language = language;
        this.genre = genre;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
