/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.other;

/**
 *
 * @author vuaphapthuat410
 */
public class Track {
    private String title;
    private Integer duration;

    public Track(String title, Integer duration) {
        this.title = title;
        this.duration = duration;
    }
    
    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
