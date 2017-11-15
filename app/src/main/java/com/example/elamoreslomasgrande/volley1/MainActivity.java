package com.example.elamoreslomasgrande.volley1;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OfertasAdapter adapter;
    private List<Oferta> ofertas;
    private SwipeRefreshLayout swipeContainer;
    public static final String LOG_TAG = OfertasAdapter.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJSON();
       // initViews();

    }


  /*  private void initViews(){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ofertas = new ArrayList<>();
        adapter = new OfertasAdapter(this, ofertas);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                initViews();
                Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
            }
        });


    }*/

        private void loadJSON(){
            RetrofitService retrofitService = RetrofitService.getInstance();
            PabloAPI api = retrofitService.getApiProxyServer();
            Call<ArrayList<Oferta>> call = api.getOfertas();
            call.enqueue(new Callback<ArrayList<Oferta>>() {
                @Override
                public void onResponse(Call<ArrayList<Oferta>> call, Response<ArrayList<Oferta>> response) {
                    Log.d("traza", "por aqui");
                    Log.d("traza", response.body().toString());
                    ofertas = response.body();
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setAdapter(new OfertasAdapter(getApplicationContext(), ofertas));
                    recyclerView.smoothScrollToPosition(0);
                  /*  if (swipeContainer.isRefreshing()){
                        swipeContainer.setRefreshing(false);
                    }*/
                }

                @Override
                public void onFailure(Call<ArrayList<Oferta>> call, Throwable t) {
                    Log.d("traza","por alla");
                    Log.d("traza", t.toString());
                }
            });
        }

        }

