package com.example.elamoreslomasgrande.volley1;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elamorhallegadoavues on 29/11/2017.
 */

public class Contador extends CountDownTimer {

    TextView tv;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date resultdate;

    public Contador(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    @Override
    public void onFinish() {
        tv.setText("get back to your car!");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        resultdate = new Date(millisUntilFinished);
        tv.setText("Left: " + sdf.format(resultdate) );

    }
}