package com.example.Room1;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Event.class}, version = 3, exportSchema = false)
public abstract class EventRoomDatabase extends RoomDatabase {

    public abstract EventDao eventDao();

    private static EventRoomDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1,2){
        @Override
        public void migrate(SupportSQLiteDatabase database){
            //從我們開始沒有改變桌子，這裡別無他法。
        }
    };

    public static EventRoomDatabase getDatabase(final Context context){
        synchronized (EventRoomDatabase.class){
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EventRoomDatabase.class,"database.db")
                        // Wipes and rebuilds instead of migrating
                        // if no Migration object.
                        // Migration is not part of this practical.
                        //.addCallback(sRoomDatabaseCallback)
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
        }
        return INSTANCE;
    }
}
