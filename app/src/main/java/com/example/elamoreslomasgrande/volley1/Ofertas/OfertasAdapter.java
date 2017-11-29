package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.Contador;
import com.example.elamoreslomasgrande.volley1.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {
    private Context mContext;
    private List<Oferta> ofertaList;
    Bitmap bitmap;



    public OfertasAdapter(Context mContext, List<Oferta> ofertaList){
        this.mContext = mContext;
        this.ofertaList = ofertaList;


    }

    @Override
    public OfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.oferta_card, viewGroup, false);

        return new MyViewHolder(view);

    }

    public List<Oferta> getOfertas() {
        return ofertaList;
    }




    @Override
    public void onBindViewHolder(final OfertasAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(ofertaList.get(i).getTitulo());
        String vote = ofertaList.get(i).getDescripcion();
        String url = ofertaList.get(i).getFotoportada();
        String location = ofertaList.get(i).getDireccion();
        String time = ofertaList.get(i).getFechalimite();
        Date fecha_oferta = null;
        Date fecha_actual = null;
        time = adaptador(time);

        //FORMATO DE LA FECHA
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            fecha_oferta = formatter.parse(time);
            fecha_actual = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long tiempo = fecha_oferta.getTime()-fecha_actual.getTime();

        Contador contador = new Contador(tiempo,1000, viewHolder.fecha,viewHolder.ofertaEstado);
        contador.start();


        viewHolder.userrating.setText(vote);
        viewHolder.lugar.setText(location);

        Picasso.with(this.mContext).load("http://46.105.28.25:3020/images/"+url).into(viewHolder.castingimg);


    }
    public String adaptador(String time){
        int t = time.indexOf("T");
        String dias = time.substring(0,t);
        String horas = " "+ time.substring(t+1,time.length()-5);

        return dias.concat(horas);
    }

    @Override
    public int getItemCount(){

        int n = ofertaList.size();
        return n;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating, lugar, fecha;
        public ImageView castingimg;
        public Button ofertaEstado;


        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            castingimg = (ImageView) view.findViewById(R.id.imagenCasting);
            lugar = view.findViewById(R.id.location);
            fecha = view.findViewById(R.id.restTime);
            ofertaEstado = view.findViewById(R.id.ofertaEstado);


        }

    }

    /*private Bitmap getImageBitmap(String url) {
        RetrofitService retrofitService = RetrofitService.getInstance();
        PabloAPI api = retrofitService.getApiProxyServer();
        Call<ResponseBody> call= api.getNotificationImage(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    bitmap =BitmapFactory.decodeStream(response.body().byteStream());
                    Log.d("bien",bitmap.toString());

                }else{
                    Log.d("traza","by bitch");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d("falla","i tant que falla");
            }

        });
        return bitmap;


    } */



}
