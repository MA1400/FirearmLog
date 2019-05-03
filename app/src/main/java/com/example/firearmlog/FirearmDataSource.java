package com.example.firearmlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malik on 5/2/2019.
 *
 * Provide methods for CRUD functions on the SQLite databse for the Firearm object
 */

public class FirearmDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    /*
     * This method creates a new database helper that is a new MySQLHelper object with the parameter context.
     *
     * @param context This parameter is a handle to the system. Helps obtain access to databases,
     * preferences, and helps resolve resources.
     */
    public FirearmDataSource(Context context) {
        dbHelper = MySQLiteHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public Firearm createFirearm( String typeOfFirearm, String caliberOfFirearm, String roundCount) {           //Added Strings as a parameter
        ContentValues values = new ContentValues();                                                              // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_TYPE_OF_FIREARM, typeOfFirearm);                                                // Insert a species into the COLUMN_SPECIES field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_CALIBER, caliberOfFirearm);                                              // Insert roundCount into the COLUMN_ROUNDCOUNT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_ROUND_COUNT, roundCount);

        long insertId = database.insert(MySQLiteHelper.TABLE_FIREARM, null, values);         //  Instert the Firearm into the database using the parameters above
        Firearm newFirearm = new Firearm(insertId, typeOfFirearm, caliberOfFirearm, roundCount);
        return newFirearm;
    }
    public Firearm createFirearm( String typeOfFirearm, String caliber, String roundCount, String rifleName, String weight, String barrelLength, String MOA) {           //Added String rating as a parameter
        ContentValues values = new ContentValues();                         // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_TYPE_OF_FIREARM, typeOfFirearm);                 // Insert a species into the COLUMN_SPECIES field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_CALIBER, caliber);               // Insert weightInOz into the COLUMN_WEIGHT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_ROUND_COUNT, roundCount);
        values.put(MySQLiteHelper.COLUMN_RIFLE_NAME, rifleName);
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        values.put(MySQLiteHelper.COLUMN_BARREL_LENGTH, barrelLength);
        values.put(MySQLiteHelper.COLUMN_ACCURACY, MOA);

        long insertId = database.insert(MySQLiteHelper.TABLE_FIREARM, null, values);         //  Instert the fish into the database using the parameters above
        Firearm newFirearm = new Firearm(insertId, typeOfFirearm, caliber, roundCount, rifleName, weight, barrelLength, MOA);
        return newFirearm;
    }

    public void deleteFirearm(Firearm firearm) {
        long id = firearm.getId();
        System.out.println("Firearm deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FIREARM, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Firearm> getAllFirearm() {
        List<Firearm> firearmList = new ArrayList<Firearm>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FIREARM,       //Modified to return all database fields
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Firearm firearm = cursorToFirearm(cursor);
            firearmList.add(firearm);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        //cursor.close();
        return firearmList;
    }
    /**
     *  Converts the current row in the database cursor into a Firearm object
     * @param cursor points to the current row in the databsae cursor
     * @return a firearm object created from that row
     */

    private Firearm cursorToFirearm(Cursor cursor) {
        Firearm firearm = new Firearm();
        firearm.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        firearm.setTypeOfFirearm(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_TYPE_OF_FIREARM)));
        firearm.setCaliber(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_WEIGHT)));
        firearm.setRoundCount(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ROUND_COUNT)));
        firearm.setRifleName(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_RIFLE_NAME)));
        firearm.setWeight(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_WEIGHT)));
        firearm.setBarrelLength(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_BARREL_LENGTH)));
        firearm.setMOA(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ACCURACY)));
        return firearm;
    }


}
