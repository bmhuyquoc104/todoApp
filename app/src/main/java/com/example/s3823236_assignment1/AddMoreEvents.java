package com.example.s3823236_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;
import java.util.List;

public class AddMoreEvents extends AppCompatActivity {
    // Initialize variable
    List<Event> eventList;
    MyApplication myApplication = (MyApplication) this.getApplication();
    int eventExistId;
    ImageView dateButton;
    ImageView timeButton;
    int day, month, year;
    int hour, minute;
    EditText dateText;
    EditText timeText;
    ImageView backArrow;
    TextView addOrEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_events);
        eventList = myApplication.getEventList();
        // Create new id of new event
        int id = eventList.size() + 1;

        EditText addTitle = findViewById(R.id.addTitle);
        EditText addLocation = findViewById(R.id.addLocation);
        EditText addDescription = findViewById(R.id.addDescription);

        Button save = findViewById(R.id.saveButton);
        addOrEdit = findViewById(R.id.addOrEdit);
        dateButton = findViewById(R.id.dateButton);
        dateText = findViewById(R.id.date);
        backArrow = findViewById(R.id.backArrow);
        // Sending data of removeWelcome in the intent to the second activity
        backArrow.setOnClickListener(view -> {
            Intent intent = new Intent(AddMoreEvents.this, AppFunctionOption.class);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra("removeWelcome", "");
            // Clear all stacks before sending this intent
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(AddMoreEvents.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
            }
        });

        // Ask user to pick the date by date picker
        dateButton.setOnClickListener(view -> {
            // Create instance of calendar
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DATE);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            // Create dialog when user choose pick date
            DatePickerDialog datePicker = new DatePickerDialog(AddMoreEvents.this, android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DATE, date);
                    // Set format of date
                    CharSequence charSequence = DateFormat.format("E, dd-MM-yyyy", calendar1);
                    dateText.setText(charSequence);
                }
            }, year, month, day);
            // Get the latest date, ignore the previous date
            datePicker.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePicker.show();
        });


        timeButton = findViewById(R.id.timeButton);
        timeText = findViewById(R.id.timeText);
        timeButton.setOnClickListener(view -> {
            // Create instance of calendar
            Calendar cal = Calendar.getInstance();
            hour = cal.get(Calendar.HOUR);
            minute = cal.get(Calendar.MINUTE);
            // Create dialog when user choose pick date
            TimePickerDialog timepicker = new TimePickerDialog(AddMoreEvents.this, android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    Calendar cal2 = Calendar.getInstance();
                    cal2.set(Calendar.HOUR, hour);
                    cal2.set(Calendar.MINUTE, minute);
                    // Set format of date
                    CharSequence charSequence = DateFormat.format("hh:mm a", cal2);
                    timeText.setText(charSequence);
                }
            }, hour, minute, true);
            timepicker.show();
        });

        // Getting intent from other activities
        Intent intent = getIntent();
        if (intent != null) {
            // Handle intent if the key = "text"
            if (intent.hasExtra("text")) {
                String text = intent.getStringExtra("text");
                // Set the name of textview to "edit"
                addOrEdit.setText(text);
            } else {
                String text = "Add New Event";
                // Set the name of textview to "add"
                addOrEdit.setText(text);
            }
        }

        // Handle intent if the key = "eventId"
        eventExistId = intent.getIntExtra("eventId",-1);
        Event event = null;
        if (eventExistId > 0) {
            // Find the event in eventList with the exact id
            for (Event e : eventList) {
                if (e.getId() == eventExistId ) {
                    event = e;
                }
            }
            //Retrieve information of that event
            addTitle.setText(event.getTitle());
            addLocation.setText(event.getLocation());
            dateText.setText(event.getDate());
            timeText.setText(event.getTime());
            addDescription.setText(event.getDescription());

        }

        // Add new event or edit base all eventExistId
        save.setOnClickListener(view -> {
            // If received the eventExistId intent (default key = -1)
            if (eventExistId > 0) {
                // Update the information of this instance by eventID
                Event updateEvent = new Event(eventExistId, dateText.getText().toString(), addTitle.getText().toString(), addLocation.getText().toString(), timeText.getText().toString(), addDescription.getText().toString());
                eventList.set(eventExistId - 1, updateEvent);
            } else {
                //Add new event to the event list
                Event addEvent = new Event(id, dateText.getText().toString(), addTitle.getText().toString(), addLocation.getText().toString(), timeText.getText().toString(), addDescription.getText().toString());
                eventList.add(addEvent);
            }

            // Sending the data of removeWelcome key in intent to Time table list activity
            Intent intent2 = new Intent(AddMoreEvents.this, TimetableList.class);
            Toast.makeText(AddMoreEvents.this, "Your change has been saved, please check it in timetable", Toast.LENGTH_SHORT).show();
            intent2.setAction(Intent.ACTION_SEND);
            intent2.setType("plain/text");
            intent2.putExtra("removeWelcome", "");
            // Clear all stacks before sending this intent
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
                startActivity(intent2);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(AddMoreEvents.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
            }

        });

    }
}