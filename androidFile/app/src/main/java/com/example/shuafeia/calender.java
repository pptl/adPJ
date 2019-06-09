package com.example.shuafeia;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.Room1.Event;
import com.example.Room1.EventListAdapter;
import com.example.Room1.EventViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class calender extends Fragment {

    private static final String LOG_TAG = calender.class.getSimpleName();
    private RecyclerView recyclerView;
    private EventViewModel mEventViewModel;
    private CalendarView calendarView;
    private String Date;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_calender, container, false);

        Calendar calendar = Calendar.getInstance();
        Date = calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        Log.d(LOG_TAG,"Create:"+Date);

        final EventListAdapter adapter = new EventListAdapter(this.getContext());
        recyclerView = RootView.findViewById(R.id.calendar_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        mEventViewModel.getAllEvent().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable final List<Event> events) {
                // Update the cached copy of the words in the adapter.
                for (int i = 0; i < events.size(); i++) {
                    Log.d(LOG_TAG, "i:" + i);
                    Log.d(LOG_TAG, events.get(i).getTask()
                            + " " + events.get(i).getDate()
                            + " " + events.get(i).getTime()
                            + " " + events.get(i).isRemind()
                            + " " + events.get(i).getType()
                            + " " + events.get(i).getLimit());
                }
                adapter.setEvent(events);
            }
        });
        recyclerView.setAdapter(adapter);

        calendarView = RootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date = year + "/" + (month + 1) + "/" + dayOfMonth;
                mEventViewModel.findEventByDate(Date).observe(calender.this, new Observer<List<Event>>() {
                    @Override
                    public void onChanged(@Nullable final List<Event> events) {
                        // Update the cached copy of the words in the adapter.
                        List<Event> temp = new ArrayList<>();
                        for (int i = 0; i < events.size(); i++) {
                            Log.d(LOG_TAG, "i:" + i);
                            Log.d(LOG_TAG, events.get(i).getTask()
                                                    + " " + events.get(i).getDate()
                                                    + " " + events.get(i).getTime()
                                                    + " " + events.get(i).isRemind()
                                                    + " " + events.get(i).getType()
                                                    + " " + events.get(i).getLimit());
                        }
                        adapter.setEvent(events);
                        recyclerView.setAdapter(adapter);
                        Log.d(LOG_TAG, "User click Date:"+Date);
                    }
                });
            }
        });

        return inflater.inflate(R.layout.fragment_calender, null);
    }
}