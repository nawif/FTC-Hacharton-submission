package com.alsahirAndroid.merth;

import java.io.Serializable;

/**
 * Created by Fahd on 26/07/2017.
 */

public class Title implements Serializable {

    private String id = "";
    private String title = "";;
    private String rawi = "";
    private String body = "";
    private String source = "";

    static public String[] allColumn() {

        return new String[]{
                "id",
                "title",
                "rawi",
                "body",
                "source",
        };
    }
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

    public String getRawi() {
        return rawi;
    }

    public void setRawi(String rawi) {
        this.rawi = rawi;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
