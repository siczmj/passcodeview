package com.nirigo.mobile.passcode.other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Sicz-Mesziár János on 2015.06.16..
 *
 * Simple adapter to ViewPager.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Class<? extends Fragment>> fragmentClasses;

    public ViewPagerAdapter(FragmentManager fm, List<Class<? extends Fragment>> fragmentClasses) {
        super(fm);
        this.fragmentClasses = fragmentClasses;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            Class<? extends Fragment> fragmentClass = fragmentClasses.get(position);
            return fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Class<? extends Fragment> fragmentClass = fragmentClasses.get(position);
        return fragmentClass.toString();
    }

    @Override
    public int getCount() {
        return fragmentClasses == null ? 0 : fragmentClasses.size();
    }

}

