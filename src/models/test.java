/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import connectors.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh
 */
public class test {
    public static void doTest() throws SQLException, ClassNotFoundException {

        ArrayList<Promo> promoList = PromoDbUtil.getAllPromo();
        System.out.print("First promo name: " + promoList.get(0).getName() + "\n");
        ArrayList<PromoItem> promoItemList = PromoDbUtil.getAllPromoItem(1);
        System.out.print("First promo item title: " + promoItemList.get(1).getTitle() + "\n");
        System.out.print("First promo item rate: " + promoItemList.get(1).getRate() + "\n");
        Promo promo = new Promo("Noel 2020", "khuyen mai lon", "2020-12-09", "2020-12-17");
        PromoDbUtil.addPromo(promo);
        System.out.println("Created promo id: " + promo.getId() + "\n");


//        ArrayList<Book> bookList = BookDbUtil.getAllItem();
//        System.out.print("Book Title: " + bookList.get(0).getTitle() + "\n");
//
//        ArrayList<CD> cdList = CDDbUtil.getAllItem();
//        System.out.print("CD Title: " + cdList.get(0).getTitle() + "\n");
//
//        ArrayList<DVD> dvdList = DVDDbUtil.getAllItem();
//        System.out.print("DVD Title: " + dvdList.get(2).getTitle() + "\n");
//
//        ArrayList<LP> lpList = LPDbUtil.getAllItem();
//        System.out.print("LP Title: " + lpList.get(lpList.size()-1).getTitle() + "\n");
//        System.out.print("First track: " + lpList.get(lpList.size()-1).getTrack_list().get(0).getName() + "\n");
//        System.out.print("CD Title: " + cdList.get(cdList.size()-1).getTitle() + "\n");
//        System.out.print("First track: " + cdList.get(cdList.size()-1).getTrack_list().get(0).getName() + "\n");


//         add book
//        Book item = new Book("One Piece", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "JK Rowling", "Hard", "Kim Dong", "1990-10-01", 123, "English", "fiction");
//        boolean result = BookDbUtil.addItem(item);
//        item.setTitle("Bleach");
//        item.setAuthor("Mario Puzo");
//        result = BookDbUtil.updateItem(item);

////         add cd
//        CD item = new CD("test-cd", 120, 123, 0, "test", "xyz", "This is a test", 123,  "2020-02-22", 123,123, 123, 50, "Hiroyuki Sawano", "Springer", "2020-12-09", "test");
//        boolean result = CDDbUtil.addItem(item);
//        item.setArtist("Beethoven");
//        result = CDDbUtil.updateItem(item);

        //test add track
//        CD item = new CD("test-cd", 120, 123, 0, "test", "xyz", "This is a test", 123,  "2020-02-22", 123,123, 123, 50, "Hiroyuki Sawano", "Springer", "2020-12-09", "test");
//        LP item = new LP("test-lp-final", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "Mozard", "Springer", "2020-12-09", "classical");
//        Album item = new Album("haru", 120, 123, 0, "test", "Hiroyuki Sawano", "Springer", "2020-12-09", "test");
//        AlbumTrack track1 = new AlbumTrack("saikai", 123);
//        AlbumTrack track2 = new AlbumTrack("anti hero", 123);
//        item.addTrack(track1);
//        item.addTrack(track2);
//        AlbumDbUtil.addItem(item);
//        item.removeTrackList();
//        Track track3 = new Track("update1", 123);
//        Track track4 = new Track("update2", 123);
//        item.addTrack(track3);
//        item.addTrack(track4);
//        LPDbUtil.updateItem(item);



//        add dvd
//        DVD item = new DVD("test-dvd", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "anime", "me", 5000, "disney", "English", "English", "2020-12-09", "test");
//        boolean result = DVDDbUtil.addItem(item);
//        item.setLanguage("Vietnamese");
//        result = DVDDbUtil.updateItem(item);

//        add lp
//        LP item = new LP("test-lp", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 123, 123, 50, "Mozard", "Springer", "2020-12-09", "classical");
//        boolean result = LPDbUtil.addItem(item);
//        item.setArtist("Beethoven");
//        item.setTitle("LP title update");
//        item.setDimension_x(1000);
//        result = LPDbUtil.updateItem(item);
//          check result
        //test delete
//        BookDbUtil.deleteItem(55);
//        if (result){
//            System.out.println("Operation success");
//        } else {
//            System.out.println("Operation fail");
//        }
//        PhysicalGood item = new PhysicalGood("test1", 120, 123, 0, "test", "xyz", "This is a test", 123, "2020-02-22", 123, 50);
//        boolean result = BookDbUtil.addItem(item);

//        Item item = new Item("test1", 120, 123, 0, "test");
//        boolean result = BookDbUtil.addItem(item);



//         add Ebook
//        Ebook item = new Ebook("One Piece", 120, 123, 0, "test", "JK Rowling", "Hard", "Kim Dong", "1990-10-01", 123, "English", "fiction", "Lorem ipsum");
//        boolean result = EbookDbUtil.addItem(item);
//        item.setTitle("Bleach");
//        item.setAuthor("Mario Puzo");
//        item.setContent("Content updated");
//        result = EbookDbUtil.updateItem(item);


//        EbookDbUtil.deleteItem(70);

        //add Movie
//        List<String> actors = new ArrayList<>();
//        actors.add("Leo");
//        actors.add("Brad");
//        List<String> writers = new ArrayList<>();
//        writers.add("Vinh");
//        writers.add("Manh");
//        Movie item = new Movie("test-dvd", 120, 123, 0, "test", "anime", "me", 5000, "disney", "English", "English", "2020-12-09", "test", actors, writers);
//        MovieDbUtil.addItem(item);
//        item.setTitle("Godfather");
//        MovieDbUtil.updateItem(item);
//        MovieDbUtil.deleteItem(69);


//        List<Ebook> ebookList = EbookDbUtil.getAllItem();
//        System.out.print("Ebook Title: " + ebookList.get(3).getTitle() + "\n");
//        System.out.print("Ebook Content: " + ebookList.get(3).getContent() + "\n");
//        List<Album> albumList = AlbumDbUtil.getAllItem();
//        System.out.print("Ebook Title: " + albumList.get(0).getTitle() + "\n");
//        System.out.print("Ebook Content: " + albumList.get(0).getTrack_list().get(0).getName() + "\n");
//        List<Movie> movieList = MovieDbUtil.getAllItem();
//        System.out.print("Movie Title: " + movieList.get(2).getTitle() + "\n");
//        System.out.print("Movie first actor: " + movieList.get(2).getActors().get(0) + "\n");
//        System.out.print("Movie first writer: " + movieList.get(2).getWriters().get(0) + "\n");



    }
}
