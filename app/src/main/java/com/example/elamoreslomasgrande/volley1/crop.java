package com.example.elamoreslomasgrande.volley1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.adamstyrc.cookiecutter.CookieCutterImageView;
import com.adamstyrc.cookiecutter.CookieCutterShape;
import com.adamstyrc.cookiecutter.ImageUtils;
import com.adamstyrc.cookiecutter.Logger;

public class crop extends AppCompatActivity {
    CookieCutterImageView a;
    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        a = (CookieCutterImageView) findViewById(R.id.ivCrop);
        a.getParams().setShape(CookieCutterShape.SQUARE);
    }



    public void hola(View v) {

        choosePhotoFromGallery();




    }

    public void caca(View v){
        Bitmap image = a.getCroppedBitmap();

        ImageView b = (ImageView) findViewById(R.id.holderpa);

        b.setImageBitmap(image);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case 1:
                try {
                    Uri imageUri = data.getData();
//                    ivCrop.setImageURI(imageUri);

                    Point screenSize = ImageUtils.getScreenSize(this);
                    Bitmap scaledBitmap = ImageUtils.decodeUriToScaledBitmap(this, imageUri, screenSize.x, screenSize.y);
                    a.setImageBitmap(scaledBitmap);

                } catch (Exception e) {
                    Logger.log(e.getMessage());
                }
                break;

        }
    }
    private void choosePhotoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }
}
