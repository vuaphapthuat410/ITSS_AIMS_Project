package models;

public class Log {
    private int id;
    private int item_id;
    private String operation;
    private String time;

    public Log(int id, int item_id, String operation, String time) {
        this.id = id;
        this.item_id = item_id;
        this.operation = operation;
        this.time = time;
    }

    public Log(int item_id, String operation, String time) {
        this.item_id = item_id;
        this.operation = operation;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
