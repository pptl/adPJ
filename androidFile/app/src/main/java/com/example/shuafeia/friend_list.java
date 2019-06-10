package com.example.shuafeia;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class friend_list extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<friend> mFriendData;
    private friendAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mRecyclerView = view.findViewById(R.id.friend_list_recycleview);
       // setContentView(R.layout.fragment_friend_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        // Initialize the ArrayList that will contain the data.
        mFriendData = new ArrayList<>();


        //mAdapter = new RecyclerAdapter(getNames());

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new friendAdapter(this.getActivity(), mFriendData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
        return view;
    }
    private void initializeData() {
        // Get the resources from the XML file.
        String[] friendList = getResources().getStringArray(R.array.frindNameList);

        // Clear the existing data (to avoid duplication).
        mFriendData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<friendList.length;i++){
            mFriendData.add(new friend(friendList[i]));
        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
}
