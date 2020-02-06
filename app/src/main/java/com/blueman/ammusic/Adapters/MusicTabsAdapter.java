package com.blueman.ammusic.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blueman.ammusic.Fragments.tab1;
import com.blueman.ammusic.Fragments.tab2;
import com.blueman.ammusic.Fragments.tab3;

public class MusicTabsAdapter extends FragmentPagerAdapter {
    private int noOfTabs;

    public MusicTabsAdapter(@NonNull FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new tab1();
            case 1:
                return new tab2();
            case 2:
                return new tab3();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
