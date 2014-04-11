package com.realityandapp.model;

import com.realityandapp.model.v2.Images;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dd on 14-4-11.
 */
public class History implements Serializable {
    public static enum Type{ SUBJECT, CELEBRITY};

    private static final long serialVersionUID = 847554389L;

    private Type type;
    private String id;
    private String name;
    private Images images;
    private Date view_at;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Date getView_at() {
        return view_at;
    }

    public void setView_at(Date view_at) {
        this.view_at = view_at;
    }
}
