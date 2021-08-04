package com.exampl.galaxy.Homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.exampl.galaxy.R;
import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    ArrayList<BrandData> brandData;
    Context context;
    public BrandAdapter(ArrayList<BrandData> brandData, Context context) {
        this.brandData = brandData;
        this.context = context;
    }
    @Override
    public BrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_brand,parent,false);
        BrandAdapter.ViewHolder brandViewHolder=new BrandAdapter.ViewHolder(view);
        return brandViewHolder;
    }

        @Override
        public void onBindViewHolder(@NonNull BrandAdapter.ViewHolder holder,int position) {

            BrandData brandData1 = brandData.get(position);
            holder.brandimage.setImageResource(brandData1.getImage());
            holder.brandimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }

        @Override
        public int getItemCount() {
            return brandData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView brandimage;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                brandimage=itemView.findViewById(R.id.brandimage);

            }
    }
}
