package com.example.elamoreslomasgrande.volley1;

import com.example.elamoreslomasgrande.volley1.Feed.Publicacion;
import com.example.elamoreslomasgrande.volley1.Ofertas.Oferta;

import java.lang.annotation.Target;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public interface PabloAPI {
    @Multipart
    @POST("imgUpload")
    Call<ResponseBody> uploadFile(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    @Headers("Content-Type: application/json")
    @POST("oferta/cargar")
    Call<ArrayList<Oferta>> getOfertas();

    @FormUrlEncoded
   // @Headers("Content-Type: application/json")
    @POST("oferta/misofertas")
    Call<ArrayList<Oferta>> getMisOfertas(
            @Field("id") int id
    );

    @Headers("Content-Type: application/json")
    @POST("portfolio/feed")
    Call<ArrayList<Publicacion>> getFeed();

 /*   @FormUrlEncoded
    @POST("/api/userlogin")
    Call<ResponseBody>  getUserLogin(
            @Field("client_id") String id,
            @Field("client_secret") String secret,
            @Field("username") String uname,
            @Field("password") String password
    ); */


 @GET
    Call<ResponseBody> getNotificationImage(
         @Url String url
 );
}
