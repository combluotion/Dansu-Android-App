package com.example.elamoreslomasgrande.volley1.Login;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.R;

public class Bifurcacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bifurcacion);
    }

    public void RedirigirContratar(View v) {
        Log.d("Pageer", "hola");
       Intent b = new Intent(this,RegisterPage.class);

        b.putExtra("modalidad", "1");
        startActivity(b);
    }

    public void RedirigirTrabajar(View v) {
        Log.d("PageNumber", "hello");
        Intent b = new Intent(this, RegisterPage.class);
        b.putExtra("modalidad", "0");
        startActivity(b);
    }
}
