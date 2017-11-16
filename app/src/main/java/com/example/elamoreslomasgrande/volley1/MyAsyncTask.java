package com.example.elamoreslomasgrande.volley1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.Navigation.Navigation;
import com.example.elamoreslomasgrande.volley1.Precarga.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by elamoreslomasgrande on 16/11/2017.
 */

class MyAsyncTask extends AsyncTask<String, Integer, String> {
    Activity activity;
    SharedPreferences sharedpreferences;
    //doInBackground es un método que se ejecuta en el background.
    //Aquí realizamos operaciones pesadas que pueden durar mucho tiempo dando lugar a una excepción
    //doInBackground no debe tocar nada de la interfaz visual. Estas operaciones serán hechas en onPostExecute

    public MyAsyncTask(Activity activity) {
        this.activity = activity;
    }

    protected String doInBackground(String... urls) {

        HttpURLConnection httpcon;
        String host_register =urls[0];
        String data1;
        String[] datos = maquillaje(urls);


        //String data1 = toJSon("Nombre","hello1","Edad","hello1");
        Log.d("hello",toJSon(datos));
        data1 = toJSon(datos);
        String result = null;
        try {
            //Connect
            httpcon = (HttpURLConnection) ((new URL(host_register).openConnection()));
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

            //Write
            OutputStream os = httpcon.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(data1);
            writer.close();
            os.close();

            //Read
            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            result = sb.toString();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;


        /*
        URL url;

        try {
            url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
            return content.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("FALLLOOOOO", "Fallo1");
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FALLLOOOOO", "Fallo2");
            return null;
        }
    }

    //El método onPostExecute debe recibir lo que devuelve el doInBackground (en este caso, un String)
    protected void onPostExecute(String txt) {
        Log.d("returned", txt);

    }
*/
    }

    //El método onPostExecute debe recibir lo que devuelve el doInBackground (en este caso, un String)
    protected void onPostExecute(String txt) {
        info(txt);

    }

    private void info(String datos){
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());


        String[] separar = datos.split(":");
        int aux ;
        String aux2;
        Log.d("DATOS", datos);
        aux = Integer.parseInt(separar[0]);
        datos = separar[1];

        switch (aux){
            case 0:
                Toast.makeText(activity.getApplicationContext(), "Error en el Login",Toast.LENGTH_SHORT).show();
                break;

            case 1:     // Registro OK (cambiar preferencias id)
                sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
                cambiarPreferencesId(datos);
                activity.startActivity(new Intent(activity, Navigation.class));

                break;
            case 2:
                Log.d("DATOS", String.valueOf(aux));
                break;
            default:

        }

    }
    public void cambiarPreferencesId(String id) {
        try {

            new Preferences(activity.getApplicationContext()).setPreferenceDouble("id", Double.parseDouble(id));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String[] maquillaje(String[] datos){

        String[] aux = new String[datos.length-1];

        for (int i = 0; i < datos.length-1 ; i++){
            aux[i] = datos[i+1];
            Log.d("HELLOOO", aux[i]);
        }

        return aux;
    }
    public static String toJSon(String... args) {

        try {
            JSONObject jsonObj = new JSONObject();
            // Here we convert Java Object to JSON
            for (int i = 0; i < args.length-1; i = i+2){
                jsonObj.put(args[i],args[i+1]);
            }

            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        return null;


    }


}