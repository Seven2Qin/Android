package com.example.slidingtab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class RemindAdapter extends FragmentStatePagerAdapter {

    private String[] titles = { "A", "B" };
    private Context ctx;

    public RemindAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return Fragment1.newInstance(0);
        } else {
            return Fragment2.newInstance(1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
