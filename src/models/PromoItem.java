package models;

public class PromoItem {
    private int item_id;
    private String title;
    private int value;
    private int price;
    private int rate;
    private String start_time;
    private String end_time;
    private int limit_quantity;
    private int sold;

    public PromoItem(int item_id, int rate, int limit_quantity) {
        this.item_id = item_id;
        this.rate = rate;
        this.limit_quantity = limit_quantity;
    }

    public PromoItem(int item_id, String title, int value, int price, int rate, int limit_quantity, int sold) {
        this.item_id = item_id;
        this.title = title;
        this.value = value;
        this.price = price;
        this.rate = rate;
        this.limit_quantity = limit_quantity;
        this.sold = sold;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getLimit_quantity() {
        return limit_quantity;
    }

    public void setLimit_quantity(int limit_quantity) {
        this.limit_quantity = limit_quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
