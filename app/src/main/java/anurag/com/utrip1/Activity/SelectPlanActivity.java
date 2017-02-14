package anurag.com.utrip1.Activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import anurag.com.utrip1.Activity.Model.Places;
import anurag.com.utrip1.R;

public class SelectPlanActivity extends AppCompatActivity  {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    static SeekBar seekBar1,seekBar2,seekBar3,seekBar4,seekBar5,seekBar6,seekBar7,seekBar8,seekBar9;
    static private String must_see,adventure,art,entertainment,shopping,restuarent,nature,religious,park,no_days;

    Places places;
    private String LINK="";

    static int checker=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
//        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
//        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
//        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
//        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
//        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
//        seekBar7 = (SeekBar) findViewById(R.id.seekBar7);
//        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
//        seekBar9 = (SeekBar) findViewById(R.id.seekBar9);



        Intent intent = getIntent();
        places=(Places) getIntent().getSerializableExtra("places");
        no_days=String.valueOf(intent.getLongExtra("no_days",0));
        Log.d("no_days",no_days);



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

//            Intent intent = new Intent(getApplicationContext(),PlaceListActivity.class);
//            startActivity(intent);
//            must_see=String.valueOf(seekBar1.getProgress());
//            Log.d("must_see",must_see);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_select_plan, container, false);
            seekBar1 = (SeekBar) rootView.findViewById(R.id.seekBar1);
            seekBar2 = (SeekBar) rootView.findViewById(R.id.seekBar2);
            seekBar3 = (SeekBar) rootView.findViewById(R.id.seekBar3);
            seekBar4 = (SeekBar) rootView.findViewById(R.id.seekBar4);
            seekBar5 = (SeekBar) rootView.findViewById(R.id.seekBar5);
            seekBar6 = (SeekBar) rootView.findViewById(R.id.seekBar6);
            seekBar7 = (SeekBar) rootView.findViewById(R.id.seekBar7);
            seekBar8 = (SeekBar) rootView.findViewById(R.id.seekBar8);
            seekBar9 = (SeekBar) rootView.findViewById(R.id.seekBar9);

//            if(checker==0)
//            {
//                setSeekBar(0);
//            }
//            else if(checker==1)
//            {
//                setSeekBar(1);
//            }
//            else if(checker==2)
//            {
//                setSeekBar(2);
//            }
//            else
//            {
//                setSeekBar(3);
//
//            }
            seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    must_see = String.valueOf(range(i));
                    Log.d("must",must_see);
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
                    adventure=String.valueOf(range(i));
                    Log.d("adventure",adventure);
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

                    art= String.valueOf(range(i));
                    Log.d("art",art);
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
                    entertainment= String.valueOf(range(i));
                    Log.d("enter",entertainment);
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
                    shopping= String.valueOf(range(i));
                    Log.d("shopping",shopping);
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
                    restuarent= String.valueOf(range(i));
                    Log.d("rest",restuarent);
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
                    nature= String.valueOf(range(i));
                    Log.d("nature",nature);
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
                    religious= String.valueOf(range(i));
                    Log.d("religious",religious);
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
                    park= String.valueOf(range(i));
                    Log.d("park",park);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Build Your Own";
                case 1:
                    checker=1;
                    return "Budget";
                case 2:
                    checker=2;
                    return "Luxury Traveler";
                case 3:
                    checker=3;
                    return "First Timer";
            }
            return null;
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
//    private static void setSeekBar(int position)
//    {
//        if(position==0)
//        {
//            seekBar1.setProgress(0);
//            seekBar2.setProgress(0);
//            seekBar3.setProgress(0);
//            seekBar4.setProgress(0);
//            seekBar5.setProgress(0);
//            seekBar6.setProgress(0);
//            seekBar7.setProgress(0);
//            seekBar8.setProgress(0);
//            seekBar9.setProgress(0);
//        }
//        else if(position==1)
//        {
//            seekBar1.setProgress(25);
//            seekBar2.setProgress(25);
//            seekBar3.setProgress(25);
//            seekBar4.setProgress(25);
//            seekBar5.setProgress(25);
//            seekBar6.setProgress(25);
//            seekBar7.setProgress(25);
//            seekBar8.setProgress(25);
//            seekBar9.setProgress(25);
//        }
//        else if(position==2)
//        {
//            seekBar1.setProgress(50);
//            seekBar2.setProgress(50);
//            seekBar3.setProgress(50);
//            seekBar4.setProgress(50);
//            seekBar5.setProgress(50);
//            seekBar6.setProgress(50);
//            seekBar7.setProgress(50);
//            seekBar8.setProgress(50);
//            seekBar9.setProgress(50);
//        }
//        else
//        {
//            seekBar1.setProgress(75);
//            seekBar2.setProgress(75);
//            seekBar3.setProgress(75);
//            seekBar4.setProgress(75);
//            seekBar5.setProgress(75);
//            seekBar6.setProgress(75);
//            seekBar7.setProgress(75);
//            seekBar8.setProgress(75);
//            seekBar9.setProgress(75);
//        }
//    }

    private void sendData()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LINK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                params.put("must_see",must_see);
                params.put("adventure",adventure);
                params.put("art",art);
                params.put("entertainment",entertainment);
                params.put("shopping",shopping);
                params.put("restaurent",restuarent);
                params.put("nature",nature);
                params.put("religious",religious);
                params.put("park",park);
                params.put("no_days",no_days);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
