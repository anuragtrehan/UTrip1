package anurag.com.utrip1.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.R;

public class BuildOwnActivity extends AppCompatActivity {

    SeekBar seekBar1,seekBar2,seekBar3,seekBar4,seekBar5,seekBar6,seekBar7,seekBar8,seekBar9;
    int must_see,adventure,art,entertainment,shopping,restaurant,nature,religious,park;
    long no_days;
    Places places;
    String plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_own);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar) findViewById(R.id.seekBar7);
        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar) findViewById(R.id.seekBar9);


        places=(Places) getIntent().getSerializableExtra("places");
        no_days=getIntent().getLongExtra("no_days",0);
        plan= getIntent().getStringExtra("plan");
        setSeekBar();

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

            Intent intent = new Intent(getApplicationContext(),PlaceListActivity.class);

            intent.putExtra("places",places);
            intent.putExtra("no_days",no_days);

            intent.putExtra("must_see",must_see);
            intent.putExtra("adventure",adventure);
            intent.putExtra("art_gallery",art);
            intent.putExtra("entertainment",entertainment);
            intent.putExtra("shopping",shopping);
            intent.putExtra("park",park);
            intent.putExtra("nature",nature);
            intent.putExtra("religious",religious);
            intent.putExtra("restaurant",restaurant);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume()
    {
        super.onResume();

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                must_see =range(i);
//                Log.d("must",must_see);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                adventure=range(i);
//                Log.d("adventure",adventure);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                art= range(i);
//                Log.d("art",art);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                entertainment= range(i);
//                Log.d("enter",entertainment);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                shopping= range(i);
//                Log.d("shopping",shopping);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                restaurant= range(i);
//                Log.d("rest",restaurant);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nature= range(i);
//                Log.d("nature",nature);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                religious= range(i);
//                Log.d("religious",religious);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                park= range(i);
//                Log.d("park",park);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void setSeekBar()
    {
        switch(plan)
        {
            case "build_you_own":
                seekBar1.setProgress(0);
                seekBar2.setProgress(0);
                seekBar3.setProgress(0);
                seekBar4.setProgress(0);
                seekBar5.setProgress(0);
                seekBar6.setProgress(0);
                seekBar7.setProgress(0);
                seekBar8.setProgress(0);
                seekBar9.setProgress(0);

                must_see     =range(0);
                adventure    =range(0);
                art          =range(0);
                entertainment=range(0);
                shopping     =range(0);
                restaurant   =range(0);
                nature       =range(0);
                religious    =range(0);
                park         =range(0);
                break;
            case "luxury":
                seekBar1.setProgress(25);
                seekBar2.setProgress(25);
                seekBar3.setProgress(25);
                seekBar4.setProgress(25);
                seekBar5.setProgress(25);
                seekBar6.setProgress(25);
                seekBar7.setProgress(25);
                seekBar8.setProgress(25);
                seekBar9.setProgress(25);
                must_see     =range(25);
                adventure    =range(25);
                art          =range(25);
                entertainment=range(25);
                shopping     =range(25);
                restaurant   =range(25);
                nature       =range(25);
                religious    =range(25);
                park         =range(25);
                break;
            case "first_time":
                seekBar1.setProgress(50);
                seekBar2.setProgress(50);
                seekBar3.setProgress(50);
                seekBar4.setProgress(50);
                seekBar5.setProgress(50);
                seekBar6.setProgress(50);
                seekBar7.setProgress(50);
                seekBar8.setProgress(50);
                seekBar9.setProgress(50);
                must_see     =range(50);
                adventure    =range(50);
                art          =range(50);
                entertainment=range(50);
                shopping     =range(50);
                restaurant   =range(50);
                nature       =range(50);
                religious    =range(50);
                park         =range(50);
                break;
            //case "budget":
            default:
                seekBar1.setProgress(75);
                seekBar2.setProgress(75);
                seekBar3.setProgress(75);
                seekBar4.setProgress(75);
                seekBar5.setProgress(75);
                seekBar6.setProgress(75);
                seekBar7.setProgress(75);
                seekBar8.setProgress(75);
                seekBar9.setProgress(75);
                must_see     =range(75);
                adventure    =range(75);
                art          =range(75);
                entertainment=range(75);
                shopping     =range(75);
                restaurant   =range(75);
                nature       =range(75);
                religious    =range(75);
                park         =range(75);

        }

    }
    private static int range(int i)
    {
        if(i<=0&&i<=25)
        {
            return 1;
        }
        else if(i>=26&&i<=50)
        {
            return 2;
        }
        else if(i>=51&&i<=75)
        {
            return 3;
        }
        else
        {
            return 4;
        }
    }

}
