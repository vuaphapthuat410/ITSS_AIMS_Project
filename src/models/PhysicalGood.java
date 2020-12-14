package models;

public class PhysicalGood extends Item {

    private String barcode;
    private String description;
    private int quantity;
    private String date;
    private int dimension;
    private int weight;

    public PhysicalGood(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension, int weight) {
        super(id, title, value, price, unit_sale, category);
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.date = date;
        this.dimension = dimension;
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}