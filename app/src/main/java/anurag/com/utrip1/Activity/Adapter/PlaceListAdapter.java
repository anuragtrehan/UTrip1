package anurag.com.utrip1.Activity.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anurag.com.utrip1.Activity.Model.Data;
import anurag.com.utrip1.R;

/**
 * Created by AnuragTrehan on 12/17/2016.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder>
{
    private List<Data> datalist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView place_name,init_time,end_time,rating,distance;

        public MyViewHolder(View view) {
            super(view);
            place_name = (TextView) view.findViewById(R.id.place_name);
            init_time = (TextView) view.findViewById(R.id.init_time);
            end_time = (TextView) view.findViewById(R.id.end_time);
            rating=(TextView) view.findViewById(R.id.rating);
            distance=(TextView) view.findViewById(R.id.distance);
        }
    }

    public PlaceListAdapter(List<Data> datalist) {
        this.datalist = datalist;
    }

    @Override
    public int getItemCount() {
        Log.d("GetCount",String.valueOf(datalist.size()));
        return datalist.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceListAdapter.MyViewHolder holder, int position)
    {
        Data data = datalist.get(position);
        holder.place_name.setText(data.getPlace_name());
        holder.init_time.setText(data.getInit_time());
        holder.end_time.setText(data.getEnd_time());
        holder.rating.setText(data.getRating());
        holder.distance.setText(data.getDistance());
        Log.i("Place Name"+" "+"InitTime"+" "+"End Time"+"rating"+"distance",data.getPlace_name()+" "+data.getInit_time()+" "+ data.getEnd_time()+" "+data.getRating()+" "+data.getDistance());

    }


}
