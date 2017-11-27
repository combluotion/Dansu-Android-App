package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.R;
import com.squareup.picasso.Picasso;

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

        viewHolder.userrating.setText(vote);
        viewHolder.lugar.setText(location);

        Picasso.with(this.mContext).load("http://46.105.28.25:3020/images/"+url).into(viewHolder.castingimg);


    }

    @Override
    public int getItemCount(){

        int n = ofertaList.size();
        return n;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating, lugar;
        public ImageView castingimg;


        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            castingimg = (ImageView) view.findViewById(R.id.imagenCasting);
            lugar = view.findViewById(R.id.location);


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
