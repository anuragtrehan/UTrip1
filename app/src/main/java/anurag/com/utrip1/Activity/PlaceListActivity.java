package anurag.com.utrip1.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import anurag.com.utrip1.Activity.Adapter.PlaceListAdapter;
import anurag.com.utrip1.Activity.Model.Data;
import anurag.com.utrip1.Activity.Model.DividerItemDecoration;
import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.Activity.Model.RecyclerTouchListener;
import anurag.com.utrip1.R;

public class PlaceListActivity extends AppCompatActivity {

    private List<Data> datalist = new ArrayList<>();

    private Spinner days;
    private RecyclerView recyclerView;
    private PlaceListAdapter mAdapter;

    private static String REGISTER_URL ="http://triplanitapi-95968.app.xervo.io/post1";
//    private static String REGISTER_URL ="http://192.168.0.108:8087/post1";
    private static String KEY_LAT          ="lat";
    private static String KEY_LNG           ="lng";
    private static String KEY_MUST          ="must_see";
    private static String KEY_ADVENTURE      ="adventure";
    private static String KEY_ART             ="art";
    private static String KEY_ENTERTAINMENT="entertainment";
    private static String KEY_SHOPPING       ="shopping";
    private static String KEY_RESTUARANT     ="restaurant";
    private static String KEY_NATURE         ="nature";
    private static String KEY_RELIGIOUS      ="religious";
    private static String KEY_PARK           ="park";
    private static String KEY_NO_DAYS        ="no_days";

    List<String []> mplaces = new ArrayList<>();
    List<Bitmap []> mimages = new ArrayList<>();
    List<String []> mLat = new ArrayList<>();
    List<String []> mLong = new ArrayList<>();

    String lati,lon,name;
    long no_days;
    Places places;
    int sel_days=0;
    int must_see,adventure,art,entertainment,shopping,restaurant,nature,religious,park;
    private ProgressDialog progress;

    String[] build;
    String rad="10000";
    String api_key="AIzaSyDJW7_0Q0-7JNdjVZgINbyuVotBQM7g3dI";
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        days = (Spinner) findViewById(R.id.days);

        recyclerView.setHasFixedSize(true);

        mAdapter = new PlaceListAdapter(datalist);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        places=(Places) getIntent().getSerializableExtra("places");
        no_days=getIntent().getLongExtra("no_days",0);

        build=getIntent().getStringArrayExtra("strings");
        must_see=getIntent().getIntExtra("must_see",1);
        adventure=getIntent().getIntExtra("adventure",1);
        art=getIntent().getIntExtra("art",1);
        entertainment=getIntent().getIntExtra("entertainment",1);
        shopping=getIntent().getIntExtra("shopping",1);
        restaurant=getIntent().getIntExtra("restaurant",1);
        nature=getIntent().getIntExtra("nature",1);
        religious=getIntent().getIntExtra("religious",1);
        park = getIntent().getIntExtra("park",1);
//        nearBy(rad,"restaurant",places.getLat(),places.getLongi());
//        addSpiner();

        lati=places.getLat();
        Log.e("LAT",lati);
        lon=places.getLongi();
        Log.e("LON",lon);
//        design();
        Log.d("DATA",lati);
        Log.d("DATA",lon);
        Log.d("DATA" ,String.valueOf(no_days));
        Log.d("DATA",String.valueOf(must_see));
        Log.d("DATA",String.valueOf(adventure));
        Log.d("DATA",String.valueOf(art));
        Log.d("DATA",String.valueOf(entertainment));
        Log.d("DATA",String.valueOf(shopping));
        Log.d("DATA",String.valueOf(restaurant));
        Log.d("DATA",String.valueOf(nature));
        Log.d("DATA",String.valueOf(religious));
        Log.d("DATA",String.valueOf(park));

        boolean check = isNetworkAvailable();
        if(check==true)
        getPostData();
        else
            Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_SHORT);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.next_button) {

            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
//            intent.putExtra("no_days",no_days);
//            intent.putExtra("places", (ArrayList<String[]>) mplaces);
//            intent.putExtra("lat", (ArrayList<String[]>) mLat);
//            intent.putExtra("lng", (ArrayList<String[]>) mLong);
            String [] day = mplaces.get(sel_days);
            intent.putExtra("places",day);



            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addSpiner()
    {
//        int adr= Integer.parseInt(no_days);
        List<String> list = new ArrayList<String>();

            for (int i = 1; i <=no_days; i++) {
                list.add(String.valueOf(i));
            }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PlaceListActivity.this,
                android.R.layout.simple_spinner_item,list );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days.setAdapter(dataAdapter);

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(mplaces.size()!=0)
                {preparePlaceListData(i);}
                sel_days=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void preparePlaceListData(int position)
 {
     datalist.clear();
     String [] day = mplaces.get(position);
     Data data;
     String init_time="",end_time="";
     for(int i=0;i<day.length;i++)
     {

         String [] separated = day [i].split("/");
       //  String init_time="",end_time="";
         if(i==0)
         {
         init_time="10:00";
         end_time=timeCalcuate(init_time,separated[4]);
         Log.d("INIT_TIME&END_TIME",init_time+"/"+end_time);
         }
         else
         {
             init_time=end_time;
             end_time=timeCalcuate(init_time,separated[4]);
             Log.d("INIT_TIME&END_TIME",init_time+"/"+end_time);
         }
         String url = separated[5];
         data = new Data(separated[0], init_time,end_time, separated[3], "23 km away");
//             Log.i("DATA INSERTION", separated[0] + "09:00AM" + "10:00AM" + separated[3] + "23 km away");
             datalist.add(data);


     }

     mAdapter.notifyDataSetChanged();
     progress.cancel();
 }
 private String timeCalcuate(String t1, String t2)
 {
     String [] separated = t1.split(":");
     int hr = Integer.parseInt(t2)/60;
     int min =Integer.parseInt(t2)%60;
     Log.d("TIME1",String.valueOf(hr)+":"+String.valueOf(min));
     hr=hr+Integer.parseInt(separated[0]);
     min= min+Integer.parseInt(separated[1]);

//     if(min==60)
//     {
//         hr=hr+1;
//         min=00;
//         return String.valueOf(hr)+":"+String.valueOf(min);
//     }
     if(min > 59)
     {
         hr=hr+1;
         min = min-60;
         if(min<10)
         {
             Log.d("TIME2",String.valueOf(hr)+":"+String.valueOf(min));
             return String.valueOf(hr)+":"+"0"+String.valueOf(min);

         }
         else
         {
             Log.d("TIME2", String.valueOf(hr) + ":" + String.valueOf(min));
             return String.valueOf(hr) + ":" + String.valueOf(min);
         }
     }
     else
     {
         if(min<10)
         {
             Log.d("TIME2",String.valueOf(hr)+":"+String.valueOf(min));
             return String.valueOf(hr)+":"+"0"+String.valueOf(min);

         }
         else
         {
             Log.d("TIME2", String.valueOf(hr) + ":" + String.valueOf(min));
             return String.valueOf(hr) + ":" + String.valueOf(min);
         }
     }
 }
    @Override
    public void onResume(){
        super.onResume();

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Data data = datalist.get(position);
                 //       Toast.makeText(getApplicationContext(),data.getPlace_name()+" "+data.getInit_time()+" "+data.getEnd_time(),Toast.LENGTH_SHORT).show();
                    }
                })
        );
        addSpiner();
//        days.setOnItemSelectedListener(this);
    }
    private void getPostData()
    {
        progress=new ProgressDialog(this);
        progress.setMessage("Downloading Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response)
                    {
                        Log.i("END","END");
                        Log.i("Response",response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("day");

                            Log.i("RESULTS",result.toString());
                            for (int i = 0; i < result.length(); i++) {
                                if(result!=null) {
                                    JSONArray jo = result.getJSONArray(i);
                                    String[] day = new String[jo.length()];
                                    for (int j = 0; j < jo.length(); j++) {

                                        JSONObject item = jo.getJSONObject(j);
                                        String place = item.getString("place");
                                        String lat = String.valueOf(item.getDouble("lat"));
                                        String lon = String.valueOf(item.getDouble("lng"));
                                        String rating = item.getString("rating");

                                        String type = item.getString("type");
                                        String time = String.valueOf(item.getInt("time1"));
                                        String url = item.getString("photo_icon");
                                        String append = place + "/" + lat + "/" + lon + "/" + rating  + "/" + time+ "/" +url+ "/" + type;
                                        day[j] = append;
                                        Log.i("APPEND", append);
                                    }
                                    mplaces.add(day);



                                }
                                else
                                {
                                    Log.d("ReSults is Null",response);
                                    Toast.makeText(getApplicationContext(),"Internal Server Error",Toast.LENGTH_SHORT);
                                    Intent intent = new Intent(PlaceListActivity.this,BuildOwnActivity.class);
                                    startActivity(intent);
                                }
                            }


                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progress.cancel();
                            Toast.makeText(getApplicationContext(),"Internal Server Error",Toast.LENGTH_SHORT);
                            Intent intent = new Intent(PlaceListActivity.this,BuildOwnActivity.class);
                            startActivity(intent);
                        }


                        preparePlaceListData(0);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_LAT          ,lati);
                params.put(KEY_LNG          ,lon);
                params.put(KEY_NO_DAYS       ,String.valueOf(no_days));
                params.put(KEY_MUST         ,String.valueOf(must_see));
                params.put(KEY_ADVENTURE    ,String.valueOf(adventure));
                params.put(KEY_ART          ,String.valueOf(art));
                params.put(KEY_ENTERTAINMENT,String.valueOf(entertainment));
                params.put(KEY_SHOPPING     ,String.valueOf(shopping));
                params.put(KEY_RESTUARANT   ,String.valueOf(restaurant));
                params.put(KEY_NATURE       ,String.valueOf(nature));
                params.put(KEY_RELIGIOUS    ,String.valueOf(religious));
                params.put(KEY_PARK         ,String.valueOf(park));
                return params;


            }
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                int mStatusCode = response.statusCode;
//                return super.parseNetworkResponse(response);
//            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


//public void downloadImage(String url,final VolleyCallback callback)
//{
//
//    ImageRequest imagerequest = new ImageRequest(url,
//            new Response.Listener<Bitmap>() {
//                @Override
//                public void onResponse(Bitmap bitmap)
//                {
//                    callback.onSuccess(bitmap);
//                }
//            }, 0, 0, null,
//            new Response.ErrorListener() {
//                public void onErrorResponse(VolleyError error)
//                {
//                Log.e("Image Download Error",error.toString());
//                }
//            });
//    RequestQueue requestQueue = Volley.newRequestQueue(this);
//    requestQueue.add(imagerequest);
//}
//
// public interface VolleyCallback
// {
//        void onSuccess(Bitmap result);
// }
private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
            = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}
}
