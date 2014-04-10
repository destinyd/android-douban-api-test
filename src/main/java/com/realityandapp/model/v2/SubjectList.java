package com.realityandapp.model.v2;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 14-4-10.
 */
public class SubjectList implements Serializable {
    private static final long serialVersionUID = 21231235433891457L;

    String title;
    int count, start, total;
    List<Subject> subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
