package models;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Item{
    private String type;
    private String director;
    private int runtime;
    private String studio;
    private String language;
    private String subtitle;
    private String publication_date;
    private String genre;
    private List<String> actors;
    private List<String> writers;


    public Movie(int id, String title, int value, int price, int unit_sale, String category, String type, String director, int runtime, String studio, String language, String subtitle, String publication_date, String genre, List<String> actors, List<String> writers) {
        super(id, title, value, price, unit_sale, category);
        this.type = type;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
        this.subtitle = subtitle;
        this.publication_date = publication_date;
        this.genre = genre;
        this.actors = actors;
        this.writers = writers;
    }

    public Movie(String title, int value, int price, int unit_sale, String category, String type, String director, int runtime, String studio, String language, String subtitle, String publication_date, String genre, List<String> actors, List<String> writers) {
        super(title, value, price, unit_sale, category);
        this.type = type;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
        this.subtitle = subtitle;
        this.publication_date = publication_date;
        this.genre = genre;
        this.actors = actors;
        this.writers = writers;
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }
}
