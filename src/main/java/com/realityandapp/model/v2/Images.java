package com.realityandapp.model.v2;

import java.io.Serializable;

/**
 * Created by dd on 14-4-10.
 */
public class Images implements Serializable {
    private static final long serialVersionUID = 152531389L;

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
