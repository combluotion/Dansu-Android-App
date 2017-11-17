package com.example.elamoreslomasgrande.volley1.Precarga;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.elamoreslomasgrande.volley1.Login.LoginPage;
import com.example.elamoreslomasgrande.volley1.Navigation.Navigation;
import com.example.elamoreslomasgrande.volley1.R;


public class SplashArt extends AppCompatActivity {
    private double id_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    id_login = new Preferences(getApplicationContext()).getPreferenceDouble("id");
                    sleep(1000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {
                    Intent i = null;
                    if (id_login > 0){
                      i = new Intent(SplashArt.this, Navigation.class);
                       // startActivity(i);

                    }else{
                        i = new Intent(SplashArt.this,  LoginPage.class);

                    }
                    if (i != null){

                        startActivity(i);
                    }
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
