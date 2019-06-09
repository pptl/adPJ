package com.example.Room1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;

@Entity(tableName = "Event_table")
public class Event {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "DateTime")
    private String DateTime;

    @ColumnInfo(name = "Task")
    private String Task;

    @ColumnInfo(name = "Date")
    private String Date;

    @ColumnInfo(name = "Time")
    private String Time;

    @ColumnInfo(name = "isRemind")
    private boolean Remind;

    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "Limit")
    private String Limit;

    /*public Event(Event event){
        this.mTask = event.getmTask();
        this.mDate = event.getmDate();
        this.mTime = event.getmTime();
        this.mRemind = event.getmRemind();
        this.mType = event.getmType();
        this.mLimit = event.getmLimit();
        this.mDateTime = event.getmDateTime();
    }*/

    public Event(@NonNull String Task, @NonNull String Date, @NonNull String Time,@NonNull boolean Remind,@NonNull String Type,String Limit) {
        this.Task = Task;
        this.Date = Date;
        this.Time = Time;
        this.Remind = Remind;
        this.Type = Type;
        this.Limit = Limit;
        this.DateTime = Date + " " + Time;
    }
        @NonNull
        public String getDateTime() {return DateTime;}

        public void setDateTime(@NonNull String dateTime) { DateTime = dateTime;}

        public String getTask() {return Task; }

        public void setTask(String task) { Task = task; }

        public String getDate() {return Date; }

        public void setDate(String date) {Date = date; }

        public String getTime() {return Time; }

        public void setTime(String time) {Time = time; }

        public boolean isRemind() {return Remind; }

        public void setRemind(boolean remind) { Remind = remind; }

        public String getType() {return Type;}

        public void setType(String type) {Type = type; }

        public String getLimit() { return Limit; }

        public void setLimit(String limit) {Limit = limit;}
}
