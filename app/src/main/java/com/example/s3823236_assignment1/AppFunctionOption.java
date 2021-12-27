package com.example.s3823236_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AppFunctionOption extends AppCompatActivity {
    ImageView backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_function_option);
        Button checkTimeTable = findViewById(R.id.checkEvent);
        Button addEvent = findViewById(R.id.addEvent);
        TextView welcomeUser = findViewById(R.id.welcomeUser);

        // Check intent and set visibility for textview
        Intent intent2 = getIntent();
        if (intent2 != null) {
            if (intent2.hasExtra("removeWelcome")) {
                welcomeUser.setVisibility(View.GONE);
            } else {
                welcomeUser.setVisibility(View.VISIBLE);
            }
        }

        backToHome = findViewById(R.id.home);
        // Sending intent to the first activity
        backToHome.setOnClickListener(view -> {
            Intent intent = new Intent(AppFunctionOption.this, MainActivity.class);
            // Clear all stacks before sending this intent
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(AppFunctionOption.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
            }
        });

        // Create greet sentence with variable name
        String name = "Welcome" + " " + getIntent().getStringExtra("name") + "!" + " " +
                ". Thank you for using our app. Hope you enjoy your time with FSmart!!!";
        welcomeUser.setText(name);

        // Sending intent to Time table list activity
        checkTimeTable.setOnClickListener(view -> {
            Intent intent = new Intent(AppFunctionOption.this, TimetableList.class);
            // Clear all stacks before sending this intent
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(AppFunctionOption.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
            }
        });
        // Sending intent to Add or Edit events activity
        addEvent.setOnClickListener(view -> {
            Intent intent = new Intent(AppFunctionOption.this, AddMoreEvents.class);
            // Clear all stacks before sending this intent
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Handle activity not found exception
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(AppFunctionOption.this, "Oops!! Something wrong, Please try again!", Toast.LENGTH_LONG).show();
            }
        });


    }


}