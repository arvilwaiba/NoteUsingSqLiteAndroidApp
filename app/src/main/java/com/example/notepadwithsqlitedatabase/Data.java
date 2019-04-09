package com.example.notepadwithsqlitedatabase;

import android.text.Editable;

public class Data {
    private String id;
    private String firstname;
    private String lastname;
    private String mark;

    public Data(String id, String firstname, String lastname, String mark) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
