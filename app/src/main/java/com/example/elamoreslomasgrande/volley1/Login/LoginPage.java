package com.example.elamoreslomasgrande.volley1.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elamoreslomasgrande.volley1.MyAsyncTask;
import com.example.elamoreslomasgrande.volley1.Navigation.Navigation;
import com.example.elamoreslomasgrande.volley1.Precarga.Preferences;
import com.example.elamoreslomasgrande.volley1.R;
import com.example.elamoreslomasgrande.volley1.User.Usuario;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class LoginPage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener  {
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private int SIGN_IN_CODE;
    private String host_register ="http://46.105.28.25:3020";
    private GoogleSignInOptions gso;
    double id_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        //confirmar si esta logeado
        id_login = new Preferences(getApplicationContext()).getPreferenceDouble("id");
       /*############# if (id_login > 0){
            Intent intento = new Intent(this, Navigation_test.class);
            startActivity(intento);
        }*/



        //GOOGLE SIGN IN
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // .requestIdToken("545781173452-h0gsn1g07f6n5et1gjak001qo0ffjtqv.apps.googleusercontent.com")
                // .requestIdToken(this.getResources().getString(R.string.default_web_client_id))
                .requestEmail()//Esta línea es opcional, por si quiero el mail del usuario
                .requestId()
                .requestProfile()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
    }
    // Mirar si los datos estan correctos
    public void Login(View view){
        EditText mail = (EditText) findViewById(R.id.Email);
        EditText password = (EditText) findViewById(R.id.Pass);
        validarLogin(mail,password);
        id_login = new Preferences(getApplicationContext()).getPreferenceDouble("id");
    }


    private void validarLogin(EditText mail,EditText password){
        String peticion = host_register+"/users/login";
        try{

            new MyAsyncTask(this).execute(peticion,"correo",mail.getText().toString(),"password",password.getText().toString());


        }catch (Exception e){

        }
    }











    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);


        if(opr.isDone()){
            Log.d("hello2", "hello");
            GoogleSignInResult result = opr.get();
            //ENVIAR A LA HOME


            //######## manejaResultado(result);
        }else {
            Log.d("hello3", "hello");

            //SI NO ESTA LOGEADO EN GOOGLE AL INICIAR EL ACTIVITY
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {


                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    Log.d("hello4", "hello");
                  manejaResultado(googleSignInResult);
                }
            });
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            manejaResultado(result);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    //Registro del usuario
    public void registrarse(View view){

        Intent in = new Intent(this, RegisterPage.class);
        startActivity(in);

        //Log.d(email.getText().toString(),password.getText().toString());
        //new MyAsyncTask().execute(host_register);
    }


    private void manejaResultado( GoogleSignInResult result ){

        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            double id =Double.parseDouble(account.getId());
            String nombre = account.getDisplayName().toString();
            String correo = account.getEmail().toString();


            Usuario user = new Usuario(id, nombre, correo);

            //Intent intent = new Intent(this, Datos_Usuario_Google.class);
            //intent.putExtra("usuario",  user);

            //startActivity(intent);
            // Toast.makeText(this, "Exito en el Login", Toast.LENGTH_SHORT).show();
            Intent intento = new Intent(this, Navigation.class);
            startActivity(intento);
        }else{
            //Toast.makeText(this, "Error en el Login",Toast.LENGTH_SHORT).show();
        }
    }
}
