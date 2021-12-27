package com.example.s3823236_assignment1;

import androidx.annotation.NonNull;

public class Event {
    // Initialize private variable
    private int id;
    private String date;
    private String title;
    private String location;
    private String time;
    private String description;

    // Constructor
    public Event(int id, String date, String title, String location, String time, String description) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.location = location;
        this.time = time;
        this.description = description;
    }

    //Simple get set method
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
