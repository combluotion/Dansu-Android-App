package com.example.elamoreslomasgrande.volley1.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.R;

public class Bifurcacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bifurcacion);
    }

    public void RegistroEmpresa(View view) {

    }

    public void RegistroUsuario(View view) {
        startActivity(new Intent(this, RegisterPage.class));
    }
}
