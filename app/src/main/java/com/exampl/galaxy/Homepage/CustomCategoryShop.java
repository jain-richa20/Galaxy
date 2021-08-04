package com.exampl.galaxy.Homepage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampl.galaxy.Homepage.MenuPage.MenuPage;
import com.exampl.galaxy.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomCategoryShop extends RecyclerView.Adapter<CustomCategoryShop.ViewHolder> {

    public final ArrayList<CategoryShopData> categoryshopdata;
    public final Context context;
    public CustomCategoryShop(ArrayList<CategoryShopData> categoryshopdata, Context context) {
        this.categoryshopdata = categoryshopdata;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CustomCategoryShop.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_categoryshop,parent,false);
        CustomCategoryShop.ViewHolder customCategory=new CustomCategoryShop.ViewHolder(view);
        return customCategory;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomCategoryShop.ViewHolder holder, int position) {

        CategoryShopData categoryShopData = categoryshopdata.get(position);
        holder.photo.setImageResource(categoryShopData.getPhoto());
        holder.title_cat.setText(categoryShopData.getTitle_cat());
        holder.subtitle.setText(categoryShopData.getSubtitle());
        if(categoryShopData.getTitle_cat().equals("Women")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        if(categoryShopData.getTitle_cat().equals("Men")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#c8bfd0"));
        }
        if(categoryShopData.getTitle_cat().equals("Kids")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#d9f0fe"));
        }
        if(categoryShopData.getTitle_cat().equals("Beauty & Personal Care")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        if(categoryShopData.getTitle_cat().equals("Gadgets")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#cfb159"));
        }
        if(categoryShopData.getTitle_cat().equals("Footwear")) {
            holder.subcategory.setBackgroundColor(Color.parseColor("#f194af"));
        }


//        holder.photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(categoryShopData.getTitle().equals("Men")) {
//                    Intent intent = new Intent(context, MenuPage.class);
//                    context.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return categoryshopdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title_cat,subtitle;
        LinearLayout subcategory;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.photo);
            subtitle=itemView.findViewById(R.id.subtitle);
            title_cat=itemView.findViewById(R.id.title_cat);
            subcategory=itemView.findViewById(R.id.subcategory);
        }
    }
}
