package connectors;

import models.Promo;
import models.PromoItem;
import models.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static ArrayList<PromoItem> getAllPromoItem(int id){
        String query = "SELECT `promo_item`.*, `item`.* FROM `promo_item` LEFT JOIN `item` ON `promo_item`.`item_id` = `item`.`id` WHERE promo_item.promo_id = ?";
        ArrayList<PromoItem> promo_list = new ArrayList<>();
        try{
            Connection connection = ConnDB.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int item_id = rs.getInt(2);
                String title = rs.getString(5);
                int value = rs.getInt(6);
                int price = rs.getInt(7);
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
}
