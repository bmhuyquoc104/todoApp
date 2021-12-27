package com.example.s3823236_assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.List;

public class TimetableList extends AppCompatActivity {
    MyApplication myApplication = (MyApplication) this.getApplication();
    List<Event> eventList ;

    // Use to create recyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter eventAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_list);
        eventList = myApplication.getEventList();

        // Set fixed size for recycler view
        recyclerView = findViewById(R.id.allEventsList);
        recyclerView.setHasFixedSize(true);

        //Use a linear layout manager
        layoutManager = new LinearLayoutManager(TimetableList.this);
        recyclerView.setLayoutManager(layoutManager);

        //Specify an adapter
        eventAdapter = new MyEventAdapter(eventList, TimetableList.this);
        recyclerView.setAdapter(eventAdapter);

        // Sending data of removeWelcome in the intent to the second activity
        backArrow = findViewById(R.id.backArrow2);
        backArrow.setOnClickListener(view ->{
           Intent intent = new Intent(TimetableList.this,AppFunctionOption.class);
           intent.setType("plain/text");
           intent.setAction(Intent.ACTION_SEND);
           intent.putExtra("removeWelcome","");
            // Clear all stacks before sending this intent
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
               startActivity(intent);
           }catch (ActivityNotFoundException e){
               Toast.makeText(TimetableList.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
           }

        });
    }


}