package com.example.elamoreslomasgrande.volley1.Navigation;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.MenuLateral.MenuLateral;
import com.example.elamoreslomasgrande.volley1.NavigationChildren.CentroAyuda;
import com.example.elamoreslomasgrande.volley1.NavigationChildren.CerrarSesion;
import com.example.elamoreslomasgrande.volley1.NavigationChildren.CuentaCandidato;
import com.example.elamoreslomasgrande.volley1.NavigationChildren.NotificacionesConf;
import com.example.elamoreslomasgrande.volley1.NavigationChildren.PoliticaPrivacidad;
import com.example.elamoreslomasgrande.volley1.R;

import java.lang.reflect.Field;

public class Navigation extends  FragmentActivity{
    HomeViewPagerAdapter homeTabsAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
    BottomNavigationView navigation;
    LockableViewPager vp;
    AppCompatActivity aca = new AppCompatActivity();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_A_INDEX, false);
                    bye();
                    navigation.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_push);
                    return true;
                case R.id.navigation_dashboard:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_B_INDEX, false);
                    bye();
                    navigation.getMenu().findItem(R.id.navigation_dashboard).setIcon(R.drawable.ic_mis_candidaturas_push);
                    return true;
                case R.id.navigation_notifications:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_C_INDEX, false);
                    bye();
                    navigation.getMenu().findItem(R.id.navigation_notifications).setIcon(R.drawable.ic_notificaciones_push);
                    return true;
                case R.id.navigation_portfolio:
                    vp.setCurrentItem(HomeViewPagerAdapter.FRAGMENT_D_INDEX, false);
                    bye();
                    navigation.getMenu().findItem(R.id.navigation_portfolio).setIcon(R.drawable.ic_perfil_push);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        vp = (LockableViewPager) findViewById(R.id.vpager);
        vp.setAdapter(homeTabsAdapter);
        vp.setSwipeable(false);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView)findViewById(R.id.navview);

        //Menu lateral
    /*    Toolbar appbar = (Toolbar) findViewById(R.id.toolbar5);
        ActionBar mActionBar = getActionBar();
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_configuraciones);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            //aca.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_configuraciones);
           // aca.getSupportActionBar().setDisplayHomeAsUpEnabled(true); */



        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;

                        switch (menuItem.getItemId()) {
                            case android.R.id.home:
                                drawerLayout.openDrawer(GravityCompat.START);
                                return true;
                            case R.id.nav_cuenta:
                                Intent intentCuenta = new Intent(Navigation.this,CuentaCandidato.class);
                                startActivity(intentCuenta);
                                break;
                            case R.id.nav_notificaciones:
                                Intent intentNotificaciones = new Intent(Navigation.this,NotificacionesConf.class);
                                startActivity(intentNotificaciones);
                                break;
                            case R.id.nav_preguntanos:

                                break;
                            case R.id.nav_centro_de_ayuda:
                                Intent intentAyuda = new Intent(Navigation.this,CentroAyuda.class);
                                startActivity(intentAyuda);
                                break;
                            case R.id.nav_política_de_privacidad:
                                Intent intentPrivacidad = new Intent(Navigation.this,PoliticaPrivacidad.class);
                                startActivity(intentPrivacidad);
                                break;
                            case R.id.nav_cerrar_sesion:
                                Intent intentCerrarSesion = new Intent(Navigation.this,CerrarSesion.class);
                                startActivity(intentCerrarSesion);
                                break;
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }

    });}

    private void bye(){
        navigation.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_home);
        navigation.getMenu().findItem(R.id.navigation_dashboard).setIcon(R.drawable.ic_mis_candidaturas);
        navigation.getMenu().findItem(R.id.navigation_notifications).setIcon(R.drawable.ic_notificaciones);
        navigation.getMenu().findItem(R.id.navigation_portfolio).setIcon(R.drawable.ic_perfil);
    };

}

