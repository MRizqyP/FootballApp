package com.example.r2rfootball.ui.main;

import android.content.Context;

import com.example.r2rfootball.R;
import com.example.r2rfootball.ui.teamOverview.TeamOverviewFragment;
import com.example.r2rfootball.ui.teamOverview.TeamPlayerFragment;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterTeamOverview extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_team_1, R.string.tab_team_2};
    private final Context mContext;

    public SectionsPagerAdapterTeamOverview(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new TeamOverviewFragment();
                break;
            case 1 :
                fragment = new TeamPlayerFragment();
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
        return 2;
    }
}