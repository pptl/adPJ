package com.example.shuafeia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new calender();
            case 1: return new profile();
            case 2: return new friend_list();
            case 3: return new setting();
            default: return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 0;
    }
}