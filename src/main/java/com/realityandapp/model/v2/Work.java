package com.realityandapp.model.v2;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 14-4-10.
 */
public class Work implements Serializable {
    private static final long serialVersionUID = 99873389L;

    private List<String> roles;

    private Subject subject;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
