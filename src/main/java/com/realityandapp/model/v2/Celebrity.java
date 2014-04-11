package com.realityandapp.model.v2;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 14-4-10.
 */
public class Celebrity implements Serializable {
    private static final long serialVersionUID = 992536389L;

    private String id;
    private String name;
    private String alt;
    private Images avatars;

    private String name_en;
    private String gender;
    private String birthday;
    private String born_place;
    private List<Work> works;

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

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBorn_place() {
        return born_place;
    }

    public void setBorn_place(String born_place) {
        this.born_place = born_place;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
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
