package com.example.s3823236_assignment1;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static List<Event> eventList = new ArrayList<Event>();

    public MyApplication() {
        fillEventList();
    }

    // Create dummy data for testing
    private void fillEventList() {
        Event e1 = new Event(1, "Tue 12/11/2022", "Learn Android", "Home",
                "8:30pm", "Review Recycler and Adapter");
        Event e2 = new Event(2,  "Thu 15/11/2022","Learn Security", "Home",
                "12:30am", "Review One Time Pad");
        Event e3 = new Event(3,  "Mon 18/11/2022","Do Assignment2", "RMIT",
                "4:30pm", "Call group for help !!");
        Event e4 = new Event(4, "Tue 19/11/2022", "Play Soccer", "Thong Nhat Stadium",
                "8:30am", "Call Andrew for a drive");
        Event e5 = new Event(5,  "Sun 24/21/2022","Watch Esport", "Home",
                "7:30pm", "Buy snack and coke");
        Event e6 = new Event(6,  "Sat 2/1/2022","Final Exam", "RMIT",
                "2:30 pm", "Bring Id and laptop to the exam");
        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);
        eventList.add(e4);
        eventList.add(e5);
        eventList.add(e6);

    }

    // Simple get set method
    public static List<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(List<Event> eventList) {
        MyApplication.eventList = eventList;
    }
}
