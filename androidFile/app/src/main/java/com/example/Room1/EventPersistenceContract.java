package com.example.Room1;

import android.provider.BaseColumns;

public class EventPersistenceContract {
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "Events";
        public static final String COLUMN_DATETIME = "DateTime";
        public static final String COLUMN_TITLE = "Title";
        public static final String COLUMN_DESCRIPIION = "Description";
        public static final String COLUMN_DATE = "Date";
        public static final String COLUMN_TIME = "Time";
        public static final String COLUMN_REMIND = "Remind";
        public static final String COLUMN_TYPE = "Type";
    }
}
