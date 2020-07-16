package com.example.r2rfootball.ui.main;

import android.content.Context;

import com.example.r2rfootball.ui.matchOverview.MatchCardsFragment;
import com.example.r2rfootball.ui.matchOverview.MatchGoalsFragment;
import com.example.r2rfootball.ui.matchOverview.MatchLineupFragment;
import com.example.r2rfootball.R;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterMatchOverview extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_match_1, R.string.tab_match_2,R.string.tab_match_3 };
    private final Context mContext;

    public SectionsPagerAdapterMatchOverview(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new MatchGoalsFragment();
                break;
            case 1 :
                fragment = new MatchLineupFragment();
                break;
            case 2 :
                fragment = new MatchCardsFragment();
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
        return 3;
    }
}