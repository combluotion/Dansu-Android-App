package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.Contador;
import com.example.elamoreslomasgrande.volley1.R;
import com.squareup.picasso.Picasso;

public class detallesOferta extends AppCompatActivity {
    private TextView titulo, descripcion, remunerado, jornada, inscritos, restTime;
    private Button estado, inscribirse;
    private ImageView imagenOferta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles__oferta);
        Oferta a = (Oferta) getIntent().getExtras().getSerializable("oferta");


        imagenOferta = findViewById(R.id.foto);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.lugar);
        remunerado = findViewById(R.id.remunerado);
        jornada = findViewById(R.id.jornada);
        inscritos = findViewById(R.id.inscritos);
        restTime = findViewById(R.id.restTime);
        estado = findViewById(R.id.ofertaEstadoButton);
        inscribirse = findViewById(R.id.Inscribirse);


        Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();

        titulo.setText(a.getTitulo());
        descripcion.setText(a.getDescripcion());
        remunerado.setText(a.getRenumerado());
        jornada.setText("Jornada "+a.getJornada());



        Picasso.with(this).load("http://46.105.28.25:3020/images/"+ a.getFotoportada()).into(imagenOferta);
        Contador.countDown(a.getFechalimite(),1000, restTime, estado);
        if (estado.getText().equals("Cerrado")){
            inscribirse.setText("Finalizado");
            inscribirse.setClickable(false);
        }


    }
}
