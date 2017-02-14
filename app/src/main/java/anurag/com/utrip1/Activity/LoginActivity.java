package anurag.com.utrip1.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import anurag.com.utrip1.R;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {


    Button google,facebook;
    GoogleApiClient  mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    private static final String REGISTER_URL ="http://192.168.0.108:8089/login";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";

    String username;
    String email1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        google = (Button) findViewById(R.id.google);
//        facebook = (Button) findViewById(R.id.facebook);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
//        btnSignIn.setColorScheme(SignInButton.COLOR_DARK);
//        btnSignIn.setSize(SignInButton.SIZE_WIDE);

//        btnSignIn.setOnClickListener(this);
        google.setOnClickListener(this);
//        facebook.setOnClickListener(this);

//        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
//        btnSignIn.setScopes(gso.getScopeArray());
//        setGooglePlusButtonText(btnSignIn);

    }
    private void signIn()
    {
        Log.d("Signin","Yes");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("Signin","Yes1");
    }
//    protected void setGooglePlusButtonText(SignInButton signInButton) {
//        for (int i = 0; i < signInButton.getChildCount(); i++) {
//            View v = signInButton.getChildAt(i);
//
//            if (v instanceof TextView) {
//                TextView tv = (TextView) v;
//                tv.setTextSize(25);
//
//                tv.setTypeface(null, Typeface.NORMAL);
//                tv.setText("Sign In With Google");
//                return;
//            }
//        }
//    }
//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//
//                    }
//                });
//    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("TAG", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e("TAG", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
//            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            Log.e("TAG", "Name: " + personName + ", email: " + email);



        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        switch (id) {
            case R.id.google:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d("TAG", "onConnectionFailed:" + connectionResult);
    }
}


