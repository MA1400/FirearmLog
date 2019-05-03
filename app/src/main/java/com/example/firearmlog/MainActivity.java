package com.example.firearmlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonAddFirearm, buttonFirearmDetails, buttonDelete;  // two button widgets
    ListView listViewFirearm;  // listview to display all the firearms in the database
    FirearmDataSource firearmDataSource; // provides interaction to the SQLite fish table
    ArrayAdapter<Firearm> firearmAdapter;
    List<Firearm> firearmList;
    int positionSelected;
    Firearm firearmSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firearmDataSource = new FirearmDataSource(this);// set up the firearm data source
        firearmDataSource.open(); // open up this data source--close before we leave

        // Set up the listView to display all the fish using a custom adapter
        listViewFirearm = (ListView) findViewById(R.id.ListViewFirearm);
        firearmList = firearmDataSource.getAllFirearm();              // Get the list of fish from the database
        // Instantiate a custom adapter for displaying each fish
        firearmAdapter = new FirearmAdapter(this, android.R.layout.simple_list_item_single_choice, firearmList);
        // Apply the adapter to the list
        listViewFirearm.setAdapter(firearmAdapter);
        listViewFirearm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Firearm selected at position " + positionSelected);
            }
        });

        // Set up the button to add a new fish using a seperate activity
        buttonAddFirearm = (Button) findViewById(R.id.buttonAddFirearm);
        buttonAddFirearm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the add fish activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), AddFirearmActivity.class);
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one fish using a seperate activity
        buttonFirearmDetails = (Button) findViewById(R.id.buttonFirearmDetails);
        buttonFirearmDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(view.getContext(), DetailActivity.class);
                detailActIntent.putExtra("Firearm", firearmList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one firearm using a seperate activity
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                firearmDataSource.deleteFirearm(firearmList.get(positionSelected));
                firearmAdapter.remove( firearmList.get(positionSelected) );
                firearmAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void onResume() {
        // re-open the database after a resume
        firearmDataSource.open();
        Log.d("MAIN", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // close the database when the app goes into the background incase it doesn't come back
        firearmDataSource.close();
        Log.d("MAIN", "onPause");
        super.onPause();
    }
}
