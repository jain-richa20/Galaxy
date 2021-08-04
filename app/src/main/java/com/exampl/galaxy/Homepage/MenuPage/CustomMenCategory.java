package com.exampl.galaxy.Homepage.MenuPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampl.galaxy.Homepage.CustomGridAdapter;
import com.exampl.galaxy.Homepage.GridData;
import com.exampl.galaxy.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomMenCategory extends RecyclerView.Adapter<CustomMenCategory.ViewHolder> {
    Context context;
    ArrayList<MenData> menDataArrayList;
    public CustomMenCategory(Context context,ArrayList<MenData> menDataArrayList) {
        this.context = context;
        this.menDataArrayList =menDataArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_mencategory,parent,false);
        ViewHolder customMenHolder=new ViewHolder(view);
        return customMenHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMenCategory.ViewHolder holder, int position) {
        MenData item = menDataArrayList.get(position);
        holder.mentitle.setText(item.getText());
        holder.menimage.setImageResource(item.getImage());
        holder.menimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getText().equals("Men's Shirts")||item.getText().equals("Men's T-Shirts")){
                    context.startActivity(new Intent(context,MenShirt.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mentitle;
        CircleImageView menimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mentitle=itemView.findViewById(R.id.mentitle);
            menimage=itemView.findViewById(R.id.menimage);
        }
    }
}
