package com.example.firearmlog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity{
    Button buttonBack;
    EditText editTextRifleName, editTextWeight, editTextBarrelLength, editTextAccuracy;
    FirearmDataSource firearmDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_firearm);

        Bundle bundle = getIntent().getExtras();
        Firearm firearm = (Firearm)   bundle.getSerializable("Firearm");

        // link each editText variable to the xml layout
        editTextRifleName = (EditText) findViewById(R.id.editTextRifleName);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextBarrelLength = (EditText) findViewById(R.id.editTextBarrelLength);
        editTextAccuracy = (EditText) findViewById(R.id.editTextAccuracy);

        editTextRifleName.setText(firearm.getRifleName());
        editTextWeight.setText(firearm.getWeight());
        editTextBarrelLength.setText(firearm.getBarrelLength());
        editTextAccuracy.setText(firearm.getMOA());


        // set up the button listener
        buttonBack = (Button) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
}
