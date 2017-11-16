package com.example.elamoreslomasgrande.volley1.Navigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by elamoreslomasgrande on 16/11/2017.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    public static final int FRAGMENT_A_INDEX = 0;
    public static final int FRAGMENT_B_INDEX = 1;
    public static final int FRAGMENT_C_INDEX = 2;

    public static final int FRAGMENTS_COUNT = 3;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_A_INDEX:
                return Home.newInstance("Home","home");
            case FRAGMENT_B_INDEX:
                return Dashboard.newInstance("Dashboard","dashboard");
            case FRAGMENT_C_INDEX:
                return Notificaciones.newInstance("Notificaciones","notificaciones");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }



}