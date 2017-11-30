package com.example.elamoreslomasgrande.volley1;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elamorhallegadoavues on 29/11/2017.
 */

public class Contador extends CountDownTimer {

    TextView tv;
    Button button;

    public Contador(long millisInFuture, long countDownInterval, TextView tv, Button button) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
        this.button = button;
    }

    @Override
    public void onFinish() {
        tv.setText("");
        button.setText("Cerrado");

    }

    @Override
    public void onTick(long millisUntilFinished) {


        long segs = millisUntilFinished/1000;

        int mDays = (int) (segs/86400);
        segs = segs-(mDays*86400);
        int mHours = (int) (segs/3600);
        segs = segs -(mHours*3600);
        int mMin = (int) (segs/60);
       // segs = segs -(mMin*60);


       // tv.setText(String.valueOf("Quedan "+mDays+"d | "+mHours+"h | "+mMin+"m | "+segs+"s"));
        tv.setText(String.valueOf("Quedan "+mDays+"d | "+mHours+"h | "+mMin+"m"));

    }
    public static void countDown(String time,long time_lapse,TextView tv, Button button){
        Date fecha_oferta = null;
        Date fecha_actual = null;
        time = adaptador(time);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            fecha_oferta = formatter.parse(time);
            fecha_actual = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long tiempo = fecha_oferta.getTime()-fecha_actual.getTime();

        Contador contador = new Contador(tiempo,time_lapse, tv,button);
        contador.start();
    }
    private static String adaptador(String time){
        int t = time.indexOf("T");
        String dias = time.substring(0,t);
        String horas = " "+ time.substring(t+1,time.length()-5);

        return dias.concat(horas);
    }
}