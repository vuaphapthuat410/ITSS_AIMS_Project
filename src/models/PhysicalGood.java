package models;

public class PhysicalGood extends Item {

    private String barcode;
    private String description;
    private int quantity;
    private String date;
    private int dimension_x;
    private int dimension_y;
    private int dimension_z;
    private int weight;

    public PhysicalGood(int id, String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight) {
        super(id, title, value, price, unit_sale, category);
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.date = date;
        this.dimension_x = dimension_x;
        this.dimension_y = dimension_y;
        this.dimension_z = dimension_z;
        this.weight = weight;
    }

    public PhysicalGood(String title, int value, int price, int unit_sale, String category, String barcode, String description, int quantity, String date, int dimension_x, int dimension_y, int dimension_z, int weight) {
        super(title, value, price, unit_sale, category);
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.date = date;
        this.dimension_x = dimension_x;
        this.dimension_y = dimension_y;
        this.dimension_z = dimension_z;
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

    public int getDimension_x() {
        return dimension_x;
    }

    public void setDimension_x(int dimension_x) {
        this.dimension_x = dimension_x;
    }

    public int getDimension_y() {
        return dimension_y;
    }

    public void setDimension_y(int dimension_y) {
        this.dimension_y = dimension_y;
    }

    public int getDimension_z() {
        return dimension_z;
    }

    public void setDimension_z(int dimension_z) {
        this.dimension_z = dimension_z;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}