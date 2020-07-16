package com.example.r2rfootball.ui.main;

import android.content.Context;



import com.example.r2rfootball.R;
import com.example.r2rfootball.favorit.MatchFavorit;
import com.example.r2rfootball.favorit.TeamFavorit;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterFavorites extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_favorites_2, R.string.tab_favorites_1};
    private final Context mContext;

    public SectionsPagerAdapterFavorites(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new TeamFavorit();
                break;
            case 1 :
                fragment = new MatchFavorit();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}