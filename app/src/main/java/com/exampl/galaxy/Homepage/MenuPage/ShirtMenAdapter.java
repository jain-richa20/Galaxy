package com.exampl.galaxy.Homepage.MenuPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampl.galaxy.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShirtMenAdapter extends RecyclerView.Adapter<ShirtMenAdapter.ViewHolder> {
    Context context;
    ArrayList<MenShirtData> menShirtDataArrayList;
    public ShirtMenAdapter(Context context,ArrayList<MenShirtData> menShirtDataArrayList) {
        this.context = context;
        this.menShirtDataArrayList =menShirtDataArrayList;
    }

    @Override
    public ShirtMenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shirt_men,parent,false);
        ViewHolder menHolder=new ViewHolder(view);
        return menHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShirtMenAdapter.ViewHolder holder, int position) {
        MenShirtData item = menShirtDataArrayList.get(position);
        holder.heading.setText(item.getHeading());
        holder.subheading.setText(item.getSubheading());
        holder.price_shirt.setText(item.getPrice());
        holder.shirt_image.setImageResource(item.getImage());
        holder.shirt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DescriptionPage.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return menShirtDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading,subheading,price_shirt,wish_icon;
        ImageView shirt_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.heading);
            subheading=itemView.findViewById(R.id.subheading);
            price_shirt=itemView.findViewById(R.id.price_shirt);
            wish_icon=itemView.findViewById(R.id.wish_icon);
            shirt_image=itemView.findViewById(R.id.shirtimage);
        }
    }
}