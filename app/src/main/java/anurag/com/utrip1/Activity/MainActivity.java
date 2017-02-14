package anurag.com.utrip1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.R;

public class MainActivity extends AppCompatActivity implements Serializable,GoogleApiClient.OnConnectionFailedListener  {

    Button check_in,check_out,search;
    TextView display_in,display_out,google_places;
    GoogleApiClient mGoogleApiClient;
    Places places=null;
    String init="",end="",splace;
    long result=0;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
//    PlaceAutocompleteFragment autocompleteFragment;


    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check_in = (Button) findViewById(R.id.check_in);
        check_out =  (Button) findViewById(R.id.check_out);
        search = (Button) findViewById(R.id.search);

        display_in=(TextView) findViewById(R.id.display_in);
        display_out=(TextView) findViewById(R.id.display_out);
        google_places=(TextView) findViewById(R.id.google_places);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        calendar = Calendar.getInstance();

        display_in.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1));
        display_out.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1));

        init=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
        end=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+1)+"-"+String.valueOf(calendar.get(Calendar.MONTH)+"-"+String.valueOf(calendar.get(Calendar.YEAR)));

//        autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
    final  DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                display_in.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear+1));
                init=String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear)+"-"+String.valueOf(year);

            }

        };
     final  DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                display_out.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear+1));
                end=String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear)+"-"+String.valueOf(year);
            }

        };
        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date1, calendar
                        .get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = calculateDate(init, end);
                if(result>0 && result<=10)
                {
                    if(places!=null) {

                        Intent intent = new Intent(getApplicationContext(), Plan_SelectActivity.class);
                        intent.putExtra("places", places);
                        intent.putExtra("no_days", result);
                        startActivity(intent);
                    }
                    else
                    {
                        Snackbar snackbar = Snackbar
                                .make(view, "Please select a place", Snackbar.LENGTH_LONG);

                        snackbar.show();
                    }
                }
                else
                {
                Snackbar snackbar = Snackbar
                        .make(view, "Trip should be between 1 to 10 days", Snackbar.LENGTH_LONG);

                snackbar.show();
                }
            }
        });

//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i("TAG", "Place: " + place.getName());
//                places = new Places();
//                places.setName(""+place.getName());
//                places.setLat(String.valueOf(place.getLatLng().latitude));
//                places.setLongi(String.valueOf(place.getLatLng().longitude));
//
//                places.setAddress(""+place.getAddress());
//                places.setPlace_id(place.getId());
//
//
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i("TAG", "An error occurred: " + status);
//            }
//        });
        google_places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            placeAutoComplete();
            }
        });
    }

    public long calculateDate(String input1, String input2)
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-mm-yyyy");
        String inputString1 = input1;
        String inputString2 = input2;
        long diff=0;
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            diff = date2.getTime() - date1.getTime();
            Log.d("TAG","Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sign) {

                signOut();
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("TAG", "onConnectionFailed:" + connectionResult);
    }
    @Override
    public void onBackPressed() {
    }

    public void placeAutoComplete()
    {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e)
        {
            Log.d("Error",e.toString());
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.d("Error",e.toString());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("PLACE", "Place: " + place.getName());
                places = new Places();
                places.setName(""+place.getName());
                places.setLat(String.valueOf(place.getLatLng().latitude));
                places.setLongi(String.valueOf(place.getLatLng().longitude));

                places.setAddress(""+place.getAddress());
                places.setPlace_id(place.getId());
                google_places.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("ERROR_MSG", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),"Please Enter Place",Toast.LENGTH_SHORT);
            }
        }
    }
}