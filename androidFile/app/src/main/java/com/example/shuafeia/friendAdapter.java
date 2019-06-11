package com.example.shuafeia;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


class friendAdapter extends RecyclerView.Adapter<friendAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<friend> mfriendData;
    private Context mContext;

    friendAdapter(Context context, ArrayList<friend> friendData) {
        this.mfriendData = friendData;
        this.mContext = context;
    }

    @Override
    public friendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.friend_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(friendAdapter.ViewHolder holder, int position) {
        // Get current sport.
        friend currentFriend = mfriendData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentFriend);
    }

    @Override
    public int getItemCount() {
        return mfriendData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mFriendNameText;

        ViewHolder(View itemView) {
            super(itemView);
            // Initialize the views.
            mFriendNameText = itemView.findViewById(R.id.friend_name_block);
        }

        void bindTo(friend currentFriend){
            // Populate the textviews with data.
            mFriendNameText.setText(currentFriend.getName());
        }
    }
}

