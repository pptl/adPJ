package com.example.Room1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.Room1.EventPersistenceContract.UserEntry.*;
public class EventsDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Event.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_DATETIME + "VARCHAT(10)" + "PRIMARY KEY NOT NULL,"+
                    COLUMN_TITLE + " VARCAHR(30) "+" NOT NULL," +
                    COLUMN_DESCRIPIION + "VARCHAR(30)"+"NOT NULL," +
                    COLUMN_DATE + "VARCHAR(10)" + "NOT NULL,"+
                    COLUMN_TIME + "VARCHAR(10)" + "NOT NULL,"+
                    COLUMN_REMIND + "BOOLEAN" + "NOT NULL,"+
                    COLUMN_TYPE + "VARCHAR(10)" + "NOT NULL," + ")";

    public EventsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
