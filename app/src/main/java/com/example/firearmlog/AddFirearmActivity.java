package com.example.firearmlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFirearmActivity extends AppCompatActivity {
    Button buttonSave;
    EditText editTextFirearm, editTextCaliber, editTextRoundCount;
    FirearmDataSource firearmDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_firearm);

        firearmDataSource = new FirearmDataSource(this);
        firearmDataSource.open();

        // link each editText variable to the xml layout
        editTextFirearm = (EditText) findViewById(R.id.editTextFirearm);
        editTextCaliber = (EditText) findViewById(R.id.editTextCaliber);
        editTextRoundCount = (EditText) findViewById(R.id.editTextRoundCount);


        // set up the button listener
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add the Firearm to the database
                String typeOfFirearm = editTextFirearm.getText().toString();
                String caliber = editTextCaliber.getText().toString();
                String roundCount = editTextRoundCount.getText().toString();
                firearmDataSource.createFirearm(typeOfFirearm, caliber, roundCount);
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
    @Override
    protected void onResume() {
        firearmDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        firearmDataSource.close();
        super.onPause();
    }
}

