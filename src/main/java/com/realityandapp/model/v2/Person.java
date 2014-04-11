package com.realityandapp.model.v2;

import java.io.Serializable;

/**
 * Created by dd on 14-4-10.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 992536389L;

    private String id;
    private String name;
    private String alt;
    private Images avatars;

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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Images getAvatars() {
        return avatars;
    }

    public void setAvatars(Images avatars) {
        this.avatars = avatars;
    }

    public String getSmallUrl(){
        if(avatars != null)
            return avatars.getSmall();
        return "";
    }
    public String getMediumUrl(){
        if(avatars != null)
            return avatars.getMedium();
        return "";
    }
    public String getLargeUrl(){
        if(avatars != null)
            return avatars.getLarge();
        return "";
    }
}
