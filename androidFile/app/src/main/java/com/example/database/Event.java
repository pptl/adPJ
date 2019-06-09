package com.example.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Event_table")
public class Event {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "DateTime")
    private String mDateTime;

    @ColumnInfo(name = "Title")
    @NonNull
    private String mTitle;

    @ColumnInfo(name = "Descr")
    @NonNull
    private String mDescr;

    @ColumnInfo(name = "Date")
    @NonNull
    private String mDate;

    @ColumnInfo(name = "Time")
    @NonNull
    private String mTime;

    @ColumnInfo(name = "isRemind")
    @NonNull
    private boolean mRemind;

    @ColumnInfo(name = "last_update")
    private int last_update;


    public Event(@NonNull String title, @NonNull String descr, @NonNull String date, @NonNull String time, @NonNull boolean remind) {
        this.mTitle = title;
        this.mDescr = descr;
        this.mDate = date;
        this.mTime = time;
        this.mRemind = remind;
        this.mDateTime = mDate+" "+mTime;
    }

    public String getTitle(){return this.mTitle;}

    public void setTitle(String title){this.mTitle = title;}

    public String getDescr(){return this.mDescr;}

    public void setDescr(String Descr){this.mDescr = Descr;}

    public String getDate() {return mDate; }

    public void setDate(String date) {this.mDate = date;}

    public String getTime() {return mTime;}

    public void setTime(String time){this.mTime = time;}

    public boolean isRemind() {return mRemind;}

    public void setRemind(boolean remind){this.mRemind = remind;}

    public String getDateTime() {return mDateTime;}

    public void setDateTime(String datetime){this.mDateTime = datetime;}

    public int getLast_update() {
        return last_update;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }
}
