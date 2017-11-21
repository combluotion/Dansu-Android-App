package com.example.elamoreslomasgrande.volley1.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.elamoreslomasgrande.volley1.MyAsyncTask;
import com.example.elamoreslomasgrande.volley1.R;

import java.lang.reflect.Array;

public class RegisterPage extends AppCompatActivity {
    String host_register ="http://46.105.28.25:3020";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }
    public void Registrar(View view){
        EditText email = (EditText) findViewById(R.id.EmailRegister);
        EditText nombre = (EditText) findViewById(R.id.NameUser);
        EditText password = (EditText) findViewById(R.id.Password);
        String peticion = host_register+"/users/crear";
        String registerMode = getIntent().getExtras().getString("modalidad");

        Log.d("llego1",""+registerMode);
        new MyAsyncTask(this).execute(peticion,"nombre",nombre.getText().toString(),"password",password.getText().toString(),"email",email.getText().toString());
        Log.d("llego2","traza2");

    }
}