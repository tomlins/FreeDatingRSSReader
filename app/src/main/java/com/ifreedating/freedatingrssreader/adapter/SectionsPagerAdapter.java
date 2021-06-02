package com.ifreedating.freedatingrssreader.adapter;

/**
 * Created by jason on 10/06/14.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;

import com.ifreedating.freedatingrssreader.app.R;

import java.util.Locale;

import static java.lang.Class.forName;

/**
 * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = "SectionsPagerAdapter";

    private String[] tabs;
    private String[] sFragments;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        Resources res = context.getResources();
        tabs = res.getStringArray(R.array.tab_headers);
        sFragments = res.getStringArray(R.array.fragment_clazzez);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1);
        try {
            Log.i(TAG, "Returning new instance of " + sFragments[position]);
            return (Fragment) forName(sFragments[position]).newInstance();
        }
        catch (Exception x) {
            Log.e(TAG, "Something went horribly wrong");
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return tabs[0].toUpperCase(l);
            case 1:
                return tabs[1].toUpperCase(l);
            case 2:
                return tabs[2].toUpperCase(l);
            case 3:
                return tabs[3].toUpperCase(l);
        }
        return null;
    }
}
