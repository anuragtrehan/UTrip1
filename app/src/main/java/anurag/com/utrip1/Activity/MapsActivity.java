package anurag.com.utrip1.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anurag.com.utrip1.Activity.Model.Data;
import anurag.com.utrip1.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ProgressDialog progress;
    String[] day;
    int checker=0;
    Polyline line;
    private static String API_KEY="AIzaSyDJW7_0Q0-7JNdjVZgINbyuVotBQM7g3dI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        mplaces=(ArrayList<String[]>) getIntent().getSerializableExtra("places");
//        mLat=(ArrayList<String[]>) getIntent().getSerializableExtra("lat");
//        mLong=(ArrayList<String[]>) getIntent().getSerializableExtra("lng");
        day=getIntent().getStringArrayExtra("places");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        preparePlaceListData (googleMap);

        // Add a marker in Sydney and move the camera

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));

    }
    private void preparePlaceListData (GoogleMap googleMap)
    {
        progress=new ProgressDialog(this);
        progress.setMessage("Downloading Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        for(int i=0;i<day.length;i++)
        {

            String [] separated = day [i].split("/");
            LatLng latLng = new LatLng(Double.parseDouble(separated[1]), Double.parseDouble(separated[2]));
            if(i==0)
            {
            googleMap.addMarker(new MarkerOptions().position(latLng).title(separated[0]).icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
            }
            else if(i==day.length-1)
            {
                googleMap.addMarker(new MarkerOptions().position(latLng).title(separated[0]).icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
            else
            {
                googleMap.addMarker(new MarkerOptions().position(latLng).title(separated[0]));
            }
        }

        getPoylines(googleMap);
    }
    private void getPoylines(final GoogleMap googleMap)
    {
        if(checker+1<day.length) {
            String[] separated = day[checker].split("/");
            String[] separated1 = day[checker + 1].split("/");
            String source = separated[1] + "," + separated[2];
            String dest = separated1[1] + "," + separated1[2];
            String json_url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + source + "&destination=" + dest + "&key=" + API_KEY;
            Log.d("JSON_URL", json_url);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            JSONObject jObject;
                            List<List<HashMap<String, String>>> routes = null;

                            try {
                                jObject = new JSONObject(response);


                                // Starts parsing data
                                routes = parse(jObject);
                                addPoly(routes, googleMap);
                                checker++;
                                getPoylines(googleMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JSON ERROR", error.toString());
                }


            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else
        {
            progress.cancel();
        }
    }
    private void addPoly(List<List<HashMap<String, String>>> result,GoogleMap googleMap)
    {
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;
        MarkerOptions markerOptions = new MarkerOptions();

        // Traversing through all the routes
        for (int i = 0; i < result.size(); i++) {
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Fetching i-th route
            List<HashMap<String, String>> path = result.get(i);

            // Fetching all the points in i-th route
            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            // Adding all the points in the route to LineOptions
            lineOptions.addAll(points);
            lineOptions.width(2);
            lineOptions.color(Color.RED);
        }

        // Drawing polyline in the Google Map for the i-th route
        googleMap.addPolyline(lineOptions);
    }
    private List<List<HashMap<String,String>>> parse(JSONObject jObject){

        List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String,String>>>() ;
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        try {

            jRoutes = jObject.getJSONArray("routes");

            /** Traversing all routes */
            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List path = new ArrayList<HashMap<String, String>>();

                /** Traversing all legs */
                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                    /** Traversing all steps */
                    for(int k=0;k<jSteps.length();k++){
                        String polyline = "";
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePoly(polyline);

                        /** Traversing all points */
                        for(int l=0;l<list.size();l++){
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng) list.get(l)).latitude) );
                            hm.put("lng", Double.toString(((LatLng) list.get(l)).longitude) );
                            path.add(hm);
                        }
                    }
                    routes.add(path);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
        }

        return routes;
    }
    /**
     * Method to decode polyline points
     * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     * */
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
