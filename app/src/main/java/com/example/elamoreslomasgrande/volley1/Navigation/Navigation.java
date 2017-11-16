package com.example.elamoreslomasgrande.volley1.Navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.R;

public class Navigation extends FragmentActivity {
    HomeViewPagerAdapter homeTabsAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
    private TextView mTextMessage;
    ViewPager vp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_B_INDEX, false);
                    return true;
                case R.id.navigation_dashboard:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_A_INDEX, false);
                    return true;
                case R.id.navigation_notifications:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_C_INDEX, false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        vp = (ViewPager) findViewById(R.id.vpager);
        vp.setAdapter(homeTabsAdapter);





        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
