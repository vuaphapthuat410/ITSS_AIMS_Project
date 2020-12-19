package models;

public class Track {
    private int item_id;
    private String name;
    private int duration;

    public Track(int item_id, String name, int duration) {
        this.item_id = item_id;
        this.name = name;
        this.duration = duration;
    }

    public Track(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
