package com.example.elamoreslomasgrande.volley1.Navigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.elamoreslomasgrande.volley1.Navigation.Guardados.Dashboard;
import com.example.elamoreslomasgrande.volley1.Navigation.Home.Home;

/**
 * Created by elamoreslomasgrande on 16/11/2017.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    public static final int FRAGMENT_A_INDEX = 0;
    public static final int FRAGMENT_B_INDEX = 1;
    public static final int FRAGMENT_C_INDEX = 2;
    public static final int FRAGMENT_D_INDEX = 3;

    public static final int FRAGMENTS_COUNT = 4;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_A_INDEX:
                return Home.newInstance("home","home");
            case FRAGMENT_B_INDEX:
                return Dashboard.newInstance("Dashboard","dashboard");
            case FRAGMENT_C_INDEX:
                return Notificaciones.newInstance("notificaciones","notificaciones");
            case FRAGMENT_D_INDEX:
                return Portfolio.newInstance("notificaciones","notificaciones");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }



}