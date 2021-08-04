package com.exampl.galaxy.Homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampl.galaxy.Homepage.MenuPage.MenuPage;
import com.exampl.galaxy.Login;
import com.exampl.galaxy.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    public final ArrayList<CategoryHelper> categoryLocations;
    public final Context context;


    public CategoryAdapter(ArrayList<CategoryHelper> categoryLocations, Context context) {
        this.categoryLocations = categoryLocations;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category,parent,false);
        ViewHolder categoryViewHolder=new ViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder,
                                 int position) {

        CategoryHelper categoryHelper = categoryLocations.get(position);
        holder.circleImage.setImageResource(categoryHelper.getImage());
        holder.text_title.setText(categoryHelper.getTitle());
        holder.circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(categoryHelper.getTitle().equals("Men")) {
                    Intent intent = new Intent(context, MenuPage.class);
                    context.startActivity(intent);
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImage;
        TextView text_title;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            circleImage=itemView.findViewById(R.id.circleImage);
            text_title=itemView.findViewById(R.id.text_title);

        }
    }
}
