package com.example.s3823236_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputName;
    CheckBox checkBox;

    @Overridedsadsadas
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInsasdasdastanceState);
        setContentView(R.layout.activity_main);
        inputName = findViewById(R.id.inputName);
        Button start = findViewById(R.id.explore);
        checkBox = findViewById(R.id.checkBox);
        // Initially disabled the start button and checkbox
        start.setEnabled(false);
        checkBox.setEnabled(false);

        // Disable the checkbox until the user typed their name
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            // If the name is not empty, enable the check box
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   String name = inputName.getText().toString();
                   checkBox.setEnabled(!(name.isEmpty()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // Disable the checkbox until the checkbox is checked
        checkBox.setOnClickListener(view -> {
                // Announce the message when checkbox is checked and enable the button
            if (checkBox.isChecked()) {
                Toast.makeText(MainActivity.this, "Thank for using our service", Toast.LENGTH_SHORT).show();
                start.setEnabled(true);
                // Announce the message when checkbox is unchecked and disable the button
            } else {
                Toast.makeText(MainActivity.this, "You must agree the policy to continue", Toast.LENGTH_SHORT).show();
                start.setEnabled(false);
            }
        });

        // Sending data of name in the intent to the second activity
        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AppFunctionOption.class);
            String name = inputName.getText().toString();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra("name", name);
            // Handle activity not found exception
            try {
                startActivity(intent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(MainActivity.this,"Oops!! Something wrong, Please try again!" ,Toast.LENGTH_LONG).show();
            }
        });

    }

}