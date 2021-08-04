package com.exampl.galaxy.Homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampl.galaxy.Homepage.MenuPage.MenDescription;
import com.exampl.galaxy.Homepage.MenuPage.MenShirtData;
import com.exampl.galaxy.R;

import java.util.ArrayList;

public class CustomDealAdapter extends RecyclerView.Adapter<CustomDealAdapter.ViewHolder> {
    Context context;
    ArrayList<DealHelper> dealAdapters;
    public CustomDealAdapter(ArrayList<DealHelper> dealAdapters, Context context) {
        this.context=context;
        this.dealAdapters=dealAdapters;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_day,parent,false);
        ViewHolder menHolder=new ViewHolder(view);
        return menHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CustomDealAdapter.ViewHolder holder, int position) {
        DealHelper item = dealAdapters.get(position);
        holder.dealimage.setImageResource(item.getImage());
//        holder.dealimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dealAdapters.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dealimage;
        public ViewHolder(View view) {
            super(view);
            dealimage=view.findViewById(R.id.dealimage);

        }
    }
}
