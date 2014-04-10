package com.realityandapp.model.v2;

import java.io.Serializable;

/**
 * Created by dd on 14-4-10.
 */
public class Rating implements Serializable {
    private static final long serialVersionUID = 4223389L;

    private int max;

    private Float average;

    private int stars;

    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
