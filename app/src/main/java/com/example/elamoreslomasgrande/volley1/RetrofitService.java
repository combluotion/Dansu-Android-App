package com.example.elamoreslomasgrande.volley1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public class RetrofitService {


    private static RetrofitService INSTANCE = null;
    private static final String BASE_URL = "http://46.105.28.25:3020";
    public PabloAPI apiService;

    // Private constructor suppresses
    public RetrofitService(){
    Retrofit builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    apiService = builder.create(PabloAPI.class);
}

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitService();
        }
    }

    public static RetrofitService getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    public PabloAPI getApiProxyServer()
    {
        return apiService;
    }

}
