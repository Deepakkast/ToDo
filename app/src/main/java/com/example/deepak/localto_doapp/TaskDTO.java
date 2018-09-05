package com.example.deepak.localto_doapp;

public class TaskDTO
{
    String subject;
    String date;
    int task_id;
    String done;


    public TaskDTO(String subject, String date, int task_id, String done) {
        this.subject = subject;
        this.date = date;
        this.task_id = task_id;
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

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                ", task_id=" + task_id +
                ", done='" + done + '\'' +
                '}';
    }

    public int auto_increment_id() {
        return task_id;
    }
}
