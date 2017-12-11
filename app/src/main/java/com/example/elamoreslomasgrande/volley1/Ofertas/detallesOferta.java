package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.Contador;
import com.example.elamoreslomasgrande.volley1.PabloAPI;
import com.example.elamoreslomasgrande.volley1.R;
import com.example.elamoreslomasgrande.volley1.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detallesOferta extends AppCompatActivity {
    private TextView titulo, descripcion, remunerado, jornada, inscritos, restTime;
    private Button estado, inscribirse;
    private ImageView imagenOferta;

    private List<Oferta> ofertas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles__oferta);
        final Oferta a = (Oferta) getIntent().getExtras().getSerializable("oferta");


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

        }else{
            inscribirse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitService retrofitService = RetrofitService.getInstance();
                    PabloAPI api = retrofitService.getApiProxyServer();
                    Call<ResponseBody> call;
                    call = api.altaOferta(1,a.getId());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(detallesOferta.this, "DONE", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(detallesOferta.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });

                    
                    
                    

                }
            });
        }

        

    
    }
}
