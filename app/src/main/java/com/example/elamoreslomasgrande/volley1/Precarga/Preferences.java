package com.example.elamoreslomasgrande.volley1.Precarga;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
/**
 * Created by elamorhallegadoavues on 02/11/2017.
 */

public class Preferences {
    SharedPreferences sharedpreferences;

    public Preferences(Context context) {
        this.sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void PreferenceString(String variable, String valor) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedpreferences.edit();
        sharedPreferencesEditor.putString(variable, valor);
        sharedPreferencesEditor.commit();

    }
    public void setPreferenceDouble(String variable, double valor) {
        SharedPreferences.Editor sharedPreferencesEditor = sharedpreferences.edit();
        String hello = String.valueOf(valor);
        sharedPreferencesEditor.putString(variable, hello);
        sharedPreferencesEditor.commit();

    }

    public String getPreferenceString(String variable){
        return sharedpreferences.getString(variable,"ERROR");

    }
    public double getPreferenceDouble(String variable){
        Log.d("rrrrrrrrrrrrrr", variable);

        double valor = Double.parseDouble(sharedpreferences.getString(variable, "-1"));

        return valor;
    }


}
