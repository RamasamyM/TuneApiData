package com.example.admin.tuneapidata;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 3/4/2016.
 */
public class NetworkDatabase extends SQLiteOpenHelper {


    public NetworkDatabase(Context context) {
        super(context, "NetworkDatabase", null, 1);
    }

    public static final String RECORD_TABLE = "record_table";
    public static final String COLUMN_ID = "column_id";
    public static final String RECORD_NAME = "record_name";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "CREATE TABLE " + RECORD_TABLE + "( "
                + COLUMN_ID + " integer primary key autoincrement not null, "
                + RECORD_NAME + " VARCHAR);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE);
        onCreate(db);
    }

    public void insertrecord(String record) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Insert", " database");
        ContentValues values = new ContentValues();
        values.put("record_name", record);
        db.insert(RECORD_TABLE, null, values);
    }
}
