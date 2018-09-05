package com.example.deepak.localto_doapp;

import android.text.BoringLayout;

public class ToDoRetro {


    private String subject;
    private String date;
    private int auto_increment_id;
    private Boolean done;

    public ToDoRetro(String subject, String date, int auto_increment_id, Boolean done) {
        this.subject = subject;
        this.date = date;
        this.auto_increment_id = auto_increment_id;
        this.done = done;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAuto_increment_id() {
        return auto_increment_id;
    }

    public void setAuto_increment_id(int auto_increment_id) {
        this.auto_increment_id = auto_increment_id;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ToDoRetro{" +
                "subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", auto_increment_id=" + auto_increment_id +
                ", done=" + done +
                '}';
    }
}


