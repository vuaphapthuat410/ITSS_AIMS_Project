package connectors;

import models.Log;
import models.Promo;
import models.PromoItem;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;

public class PromoDbUtil {
    //lay tat ca chuong trinh khuyen mai
    public static ArrayList<Promo> getAllPromo () {
        String query = "SELECT * FROM `promo`";
        ArrayList<Promo> promo_list = new ArrayList<>();

        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String start_time = rs.getString(4);
                String end_time = rs.getString(5);

                Promo b = new Promo(
                        id,
                        name,
                        description,
                        start_time,
                        end_time
                );

                promo_list.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant execute query");
            e.printStackTrace();
        }

        return promo_list;
    }


    // lay tat ca cac item co trong chuong trinh khuyen mai (con thoi han)
    public static ArrayList<PromoItem> getAllPromoItem(){
        String query = "SELECT `promo_item`.*, `item`.* FROM `promo_item` LEFT JOIN `item` ON `promo_item`.`item_id` = `item`.`id`";
        ArrayList<PromoItem> promo_list = new ArrayList<>();
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int item_id = rs.getInt(2);
                String title = rs.getString(7);
                int value = rs.getInt(8);
                int price = rs.getInt(9);
                int rate = rs.getInt(3);

                String end_time = rs.getString(5);

                PromoItem b = new PromoItem(
                        item_id,
                        title,
                        value,
                        price,
                        rate
                );

                // check if promo valid
                Date date_type_end_time = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(end_time);
                Date now = new Date();
                if (now.before(date_type_end_time)) {
                    promo_list.add(b);
                }

            }
        } catch (Exception e) {
            System.out.print("Cant execute query");
            e.printStackTrace();
        }
        return promo_list;
    }

    // lay tat ca cac item co trong chuong trinh khuyen mai dua theo id cua promo (ke ca het thoi han)
    public static ArrayList<PromoItem> getAllPromoItemById(int id){
        String query = "SELECT `promo_item`.*, `item`.* FROM `promo_item` LEFT JOIN `item` ON `promo_item`.`item_id` = `item`.`id` WHERE promo_item.promo_id = ?";
        ArrayList<PromoItem> promo_list = new ArrayList<>();
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int item_id = rs.getInt(2);
                String title = rs.getString(7);
                int value = rs.getInt(8);
                int price = rs.getInt(9);
                int rate = rs.getInt(3);

                PromoItem b = new PromoItem(
                        item_id,
                        title,
                        value,
                        price,
                        rate
                );

                promo_list.add(b);
            }
        } catch (Exception e) {
            System.out.print("Cant execute query");
            e.printStackTrace();
        }
        return promo_list;
    }



    // them chuong trinh khuyen mai
    public static boolean addPromo(Promo promo){
        String query = "INSERT INTO `promo` (`id`, `name`, `description`, `start_time`, `end_time`) VALUES (NULL, ?, ?, ?, ?);";
        int id;
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, promo.getName());
            statement.setString(2, promo.getDescription());
            statement.setString(3, promo.getStart_time());
            statement.setString(4, promo.getEnd_time());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                    promo.setId(id);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


        } catch (Exception e) {
            System.out.print("Cant connect");
            e.printStackTrace();
        }

        return true;
    }

    // them san pham khuyen mai
    public static boolean addPromoItem(Promo promo, PromoItem promoItem) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `promo_item` (`promo_id`, `item_id`, `rate`, `start_time`, `end_time`) VALUES (?, ?, ?, ?, ?);";
        int id;
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, Integer.toString(promo.getId()));
            statement.setString(2, Integer.toString(promoItem.getItem_id()));
            statement.setString(3, Integer.toString(promoItem.getRate()));
            statement.setString(4, promo.getStart_time());
            statement.setString(5, promo.getEnd_time());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.print("Cant execute query");
            e.printStackTrace();
        }

        return false;
    }

    //xoa promo
    public static void deletePromo (int id) throws SQLException, ClassNotFoundException {
        String itemQuery = "DELETE FROM `promo` WHERE `promo`.`id` = ?";
        try {

            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(itemQuery);

            statement.setString(1, Integer.toString(id));
            int rowsDeleted = statement.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
