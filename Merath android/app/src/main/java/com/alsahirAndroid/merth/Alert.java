package com.alsahirAndroid.merth;

import java.io.Serializable;

/**
 * Created by Fahd on 31/07/2017.
 */

public class Alert implements Serializable {

    private String id = "";
    private String title = "";
    private String time = "";
    private String repeat = "";
    private String sound = "";
    private Boolean isActive = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
