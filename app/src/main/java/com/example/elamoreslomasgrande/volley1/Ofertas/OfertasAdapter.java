package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.elamoreslomasgrande.volley1.Contador;

import com.example.elamoreslomasgrande.volley1.Navigation.Guardados.Guardadas.Guardadas;
import com.example.elamoreslomasgrande.volley1.PabloAPI;

import com.example.elamoreslomasgrande.volley1.R;
import com.example.elamoreslomasgrande.volley1.RetrofitService;
import com.squareup.picasso.Picasso;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.concurrent.ExecutionException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {
    private Context mContext;
    private List<Oferta> ofertaList;
    private List<Oferta> guardadas;



    public OfertasAdapter(Context mContext, List<Oferta> ofertaList) {
        this.mContext = mContext;
        this.ofertaList = ofertaList;
        this.guardadas = guardadas;

    }

    @Override
    public OfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.oferta_card, viewGroup, false);

        return new MyViewHolder(view);

    }

    public List<Oferta> getOfertas() {
        return ofertaList;
    }

    public List<Oferta> getGuardadas(){
        return guardadas;
    }


    @Override
    public void onBindViewHolder(final OfertasAdapter.MyViewHolder viewHolder, final int i) {

        viewHolder.title.setText(ofertaList.get(i).getTitulo());
        String vote = ofertaList.get(i).getDescripcion();
        String url = ofertaList.get(i).getFotoportada();
        String location = ofertaList.get(i).getDireccion();
        String time = ofertaList.get(i).getFechalimite();





        //FORMATO DE LA FECHA
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        Contador.countDown(time,1000,viewHolder.fecha,viewHolder.ofertaEstado);



        viewHolder.userrating.setText(vote);
        viewHolder.lugar.setText(location);

        Picasso.with(this.mContext).load("http://46.105.28.25:3020/images/"+url).into(viewHolder.castingimg);

        viewHolder.ofertaEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "You clicked on "+i);

                Toast a = Toast.makeText(viewHolder.userrating.getContext(),""+ofertaList.get(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT);
                a.show();

                Intent oferta = new Intent(view.getContext(),detallesOferta.class);
                Oferta listar_oferta;
                listar_oferta = ofertaList.get(viewHolder.getAdapterPosition());
                oferta.putExtra("oferta",listar_oferta);
                view.getContext().startActivity(oferta);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "You clicked on "+i);

                Toast a = Toast.makeText(viewHolder.userrating.getContext(),""+ofertaList.get(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT);
                a.show();

                Intent oferta = new Intent(view.getContext(),detallesOferta.class);
                Oferta listar_oferta;
                listar_oferta = ofertaList.get(viewHolder.getAdapterPosition());
                oferta.putExtra("oferta",listar_oferta);
                view.getContext().startActivity(oferta);}});



        final int id_oferta = ofertaList.get(i).getId();
        viewHolder.userrating.setText(vote);
        viewHolder.lugar.setText(location);


        Picasso.with(this.mContext).load("http://46.105.28.25:3020/images/" + url).into(viewHolder.castingimg);

        viewHolder.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitService retrofitService = RetrofitService.getInstance();
                PabloAPI api = retrofitService.getApiProxyServer();
                Call<ResponseBody> call;

                if(viewHolder.guardar.isChecked()) {call = api.postGuardar(1, id_oferta);}
                else {call = api.postDesguardar(1, id_oferta);}

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("traza", "por aqui");
                            Log.d("traza", response.body().toString());


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("traza", "por alla");
                            Log.d("traza", t.toString());
                        }
                    });
                }
        });

        // le enviamos el callback al metodo checkguardadas
        checkguardadas(1,new OnOfertasResponse(){

            @Override
            public void ofertas(ArrayList<Oferta> ofertas){
                // este metodo se ejecutara cuando onResponse se ejecute
                Iterator it = ofertas.iterator();
                int a = 0;
                while (it.hasNext()) {

                    if (ofertaList.get(i).getId() == ofertas.get(a).getId()) {
                        viewHolder.guardar.setChecked(true);
                    }
                    it.next();
                    a++;
                }

            }
        });

    }



    @Override
    public int getItemCount() {

        int n = ofertaList.size();
        return n;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userrating, lugar, fecha;
        public ImageView castingimg;
        public CheckBox guardar;
        public Button ofertaEstado;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            castingimg = (ImageView) view.findViewById(R.id.imagenCasting);
            lugar = view.findViewById(R.id.location);
            fecha = view.findViewById(R.id.restTime);
            ofertaEstado = view.findViewById(R.id.ofertaEstado);
            guardar = view.findViewById(R.id.guardar);


        }

    }
    public void checkguardadas(int id_usuario, final OnOfertasResponse callback){
        RetrofitService retrofitService = RetrofitService.getInstance();
        PabloAPI api = retrofitService.getApiProxyServer();
        Call<ArrayList<Oferta>> call = api.getGuardadas(1);
        call.enqueue(new Callback<ArrayList<Oferta>>() {
            @Override
            public void onResponse(Call<ArrayList<Oferta>> call, Response<ArrayList<Oferta>> response) {
                Log.d("traza", "por aqui");
                Log.d("traza", response.body().toString());
                guardadas = response.body();

                // ejecutamos el callback
                callback.ofertas(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Oferta>> call, Throwable t) {
                Log.d("traza","por alla");
                Log.d("traza", t.toString());
            }
        });


    };





}




