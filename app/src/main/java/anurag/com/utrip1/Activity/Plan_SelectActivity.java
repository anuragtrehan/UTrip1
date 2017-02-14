package anurag.com.utrip1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.R;

public class Plan_SelectActivity extends AppCompatActivity {

    TextView build_own,first_time,budget,luxury;
    Places places;
    long no_days;
//    int amusement_park,art_gallery,entertainment,shopping,nature,religious,park,restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan__select);

        build_own= (TextView) findViewById(R.id.build);
        first_time= (TextView) findViewById(R.id.first);
        budget = (TextView) findViewById(R.id.budget);
        luxury = (TextView) findViewById(R.id.luxury);

        Intent intent = getIntent();
        places=(Places) getIntent().getSerializableExtra("places");
        no_days=intent.getLongExtra("no_days",0);

//        Log.d("places",places.getLat()+places.getLongi());

    }

    @Override
    public void onResume()
    {
        super.onResume();

        build_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),BuildOwnActivity.class);
                intent.putExtra("places",places);
                intent.putExtra("no_days",no_days);
                intent.putExtra("plan","build_you_own");
                startActivity(intent);
            }
        });
        first_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BuildOwnActivity.class);
                intent.putExtra("places",places);
                intent.putExtra("no_days",no_days);
//                String[] build = new String[] {"amusement_park","art_gallery","movie_theater","shopping_mall","zoo","hindu_temple","park"};
//                intent.putExtra("strings", build);
//                intent.putExtra("amusement_park",amusement_park);
//                intent.putExtra("art_gallery",art_gallery);
//                intent.putExtra("entertainment",entertainment);
//                intent.putExtra("shopping",shopping);
//                intent.putExtra("park",park);
//                intent.putExtra("nature",nature);
//                intent.putExtra("religious",religious);
//                intent.putExtra("restaurant",restaurant);
                intent.putExtra("plan","first_time");
                startActivity(intent);
            }
        });

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),BuildOwnActivity.class);
                intent.putExtra("places",places);
                intent.putExtra("no_days",no_days);
//                String[] build = new String[] {"shopping_mall","amusement_park","art_gallery","movie_theater","zoo","hindu_temple","park"};
//                intent.putExtra("strings", build);
//                intent.putExtra("amusement_park",amusement_park);
//                intent.putExtra("art_gallery",art_gallery);
//                intent.putExtra("entertainment",entertainment);
//                intent.putExtra("shopping",shopping);
//                intent.putExtra("park",park);
//                intent.putExtra("nature",nature);
//                intent.putExtra("religious",religious);
//                intent.putExtra("restaurant",restaurant);
                intent.putExtra("plan","budget");
                startActivity(intent);
            }
        });
        luxury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),BuildOwnActivity.class);
                intent.putExtra("places",places);
                intent.putExtra("no_days",no_days);
//                String[] build = new String[] {"amusement_park","art_gallery","movie_theater","shopping_mall","zoo","hindu_temple","park"};
//                intent.putExtra("strings", build);
//                intent.putExtra("amusement_park",amusement_park);
//                intent.putExtra("art_gallery",art_gallery);
//                intent.putExtra("entertainment",entertainment);
//                intent.putExtra("shopping",shopping);
//                intent.putExtra("park",park);
//                intent.putExtra("nature",nature);
//                intent.putExtra("religious",religious);
//                intent.putExtra("restaurant",restaurant);
                intent.putExtra("plan","luxury");
                startActivity(intent);
            }
        });

    }



}
