package com.example.elamoreslomasgrande.volley1.Ofertas;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.Navigation.Guardados.Guardadas.Guardadas;
import com.example.elamoreslomasgrande.volley1.PabloAPI;
import com.example.elamoreslomasgrande.volley1.R;
import com.example.elamoreslomasgrande.volley1.RetrofitService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
        checkguardadas(1);
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


    @Override
    public void onBindViewHolder(final OfertasAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(ofertaList.get(i).getTitulo());
        String vote = ofertaList.get(i).getDescripcion();
        String url = ofertaList.get(i).getFotoportada();
        String location = ofertaList.get(i).getDireccion();
        final int id_oferta = ofertaList.get(i).getId();
        viewHolder.userrating.setText(vote);
        viewHolder.lugar.setText(location);


        Picasso.with(this.mContext).load("http://46.105.28.25:3020/images/" + url).into(viewHolder.castingimg);

        viewHolder.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetrofitService retrofitService = RetrofitService.getInstance();
                PabloAPI api = retrofitService.getApiProxyServer();
                Call<ResponseBody> call = api.postGuardar(1, id_oferta);
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

        Iterator it = guardadas.iterator();
        while (it.hasNext()) {
            if (ofertaList.get(i).getId() == guardadas.get(i).getId()) {
                viewHolder.guardar.setChecked(true);
            }
        }

    }

    @Override
    public int getItemCount() {

        int n = ofertaList.size();
        return n;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userrating, lugar;
        public ImageView castingimg;
        public CheckBox guardar;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            castingimg = (ImageView) view.findViewById(R.id.imagenCasting);
            lugar = view.findViewById(R.id.location);
            guardar = view.findViewById(R.id.guardar);


        }

    }

    public void checkguardadas(int id_usuario) {

        RetrofitService retrofitService = RetrofitService.getInstance();
        PabloAPI api = retrofitService.getApiProxyServer();

       ArrayList<Observable<?>> requests = new ArrayList<>();

       requests.add(api.getGuardadax(1));
        Observable.zip(
                requests,
                new Function<Object[], Object>() {
                    @Override
                    public Object apply(Object[] objects) throws Exception {
                        // Objects[] is an array of combined results of completed requests

                        // do something with those results and emit new event
                        return new Object();
                    }
                })
                // After all requests had been performed the next observer will receive the Object, returned from Function
                .subscribe(
                        // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
                        new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                //Do something on successful completion of all requests
                                guardadas =(ArrayList)o;
                            }
                        },

                        // Will be triggered if any error during requests will happen
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                //Do something on error completion of requests
                            }
                        }
                );

        /* Call<ArrayList<Oferta>> call = api.getGuardadas(1);
        call.enqueue(new Callback<ArrayList<Oferta>>() {
            @Override
            public void onResponse(Call<ArrayList<Oferta>> call, Response<ArrayList<Oferta>> response) {

                Log.d("traza", "por aqui");
                Log.d("traza", response.body().toString());
                guardadas = response.body();


            }

            @Override
            public void onFailure(Call<ArrayList<Oferta>> call, Throwable t) {
                Log.d("traza", "por alla");
                Log.d("traza", t.toString());
            }
        }); */






    }

}




