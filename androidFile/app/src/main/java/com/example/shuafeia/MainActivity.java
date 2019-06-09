package com.example.shuafeia;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.CalendarView;

import com.example.database.Event;
import com.example.database.EventListAdapter;
import com.example.database.EventRoomDatabase;
import com.example.database.EventViewModel;

import java.util.ArrayList;
import java.util.List;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EventViewModel mEventViewModel;

    static final int RESULE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new calender());

        mEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new calender();
                break;

            case R.id.navigation_profile:
                fragment = new profile();
                break;

            case R.id.navigation_friend_list:
                fragment = new friend_list();
                break;

            case R.id.navigation_setting:
                fragment = new setting();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void addTask(View view) {
        Intent intent = new Intent(this,add_task.class);
        startActivityForResult(intent,RESULE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULE_CODE) {
            if (resultCode == RESULT_OK) {
                Event event = new Event(data.getStringExtra("Title"), data.getStringExtra("Descr"), data.getStringExtra("Date"), data.getStringExtra("Time"), data.getBooleanExtra("Remind", false));
                //db.eventDao().insert(event);
                mEventViewModel.insert(event);
                Log.d(LOG_TAG,"Insert sussceful");
            }
        }
    }

}