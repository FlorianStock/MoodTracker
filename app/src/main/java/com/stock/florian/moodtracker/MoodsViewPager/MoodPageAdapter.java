package com.stock.florian.moodtracker.MoodsViewPager;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MoodPageAdapter extends FragmentPagerAdapter {


    private int[] colors;


    public MoodPageAdapter(FragmentManager mgr, int[] colors)
    {
        super(mgr);
        this.colors = colors;
    }

    @Override
    public int getCount()
    {
        return(5); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position)
    {
        // 4 - Page to return

        return(MoodFragment.newInstance(position, this.colors[position]));
    }
}