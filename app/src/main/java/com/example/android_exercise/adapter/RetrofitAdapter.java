package com.example.android_exercise.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_exercise.R;
import com.example.android_exercise.model.ModelRecycler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    private ArrayList<ModelRecycler>dataModelArrayList;
    private Context mContext;
    public RetrofitAdapter(Context ctx, ArrayList<ModelRecycler>dataModelArrayList){
        this.dataModelArrayList=dataModelArrayList;
        this.mContext = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.retro_item,parent, false);


        return new MyViewHolder(v);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(mContext).load(dataModelArrayList.get(position).getImage()).into(holder.iv);
        holder.cake_name.setText(dataModelArrayList.get(position).getTitle());
      holder.cake_desc.setText(dataModelArrayList.get(position).getDesc());


    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cake_name, cake_desc;
        ImageView iv;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cake_name = (TextView) itemView.findViewById(R.id.country);
            cake_desc = (TextView) itemView.findViewById(R.id.name);

            iv = (ImageView) itemView.findViewById(R.id.ivv);




        }
    }


}
