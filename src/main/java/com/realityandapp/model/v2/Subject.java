package com.realityandapp.model.v2;

import android.text.TextUtils;
import com.realityandapp.model.History;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 14-4-10.
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = 433891457L;
    Rating rating;
    String title,
            original_title,
            subtype,
            alt,
            id;
    Integer
            year,
            collect_count;

    Images images;

    List<String> countries;

    List<Celebrity> casts;

    List<Celebrity> directors;

    public Subject() {
    }

    public Subject(History history) {
        id = history.getId();
        title = history.getName();
        images = history.getImages();
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(Integer collect_count) {
        this.collect_count = collect_count;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Celebrity> getCasts() {
        return casts;
    }

    public void setCasts(List<Celebrity> casts) {
        this.casts = casts;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<Celebrity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Celebrity> directors) {
        this.directors = directors;
    }

    public String getSmallUrl(){
        if(images != null)
            return images.getSmall();
        return "";
    }
    public String getMediumUrl(){
        if(images != null)
            return images.getMedium();
        return "";
    }
    public String getLargeUrl(){
        if(images != null)
            return images.getLarge();
        return "";
    }

    public String getCountriesString(){
        if(countries != null)
            return TextUtils.join(",",countries);
        return "";
    }
}
