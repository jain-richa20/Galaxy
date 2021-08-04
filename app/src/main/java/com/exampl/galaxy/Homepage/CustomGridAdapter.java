package com.exampl.galaxy.Homepage;

import android.content.Context;
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

public class CustomGridAdapter extends RecyclerView.Adapter<CustomGridAdapter.ViewHolder> {
    Context context;
    ArrayList<GridData> gridDataArrayList;
    public CustomGridAdapter(@NonNull Context context, ArrayList<GridData> gridDataArrayList) {
        this.context = context;
        this.gridDataArrayList =gridDataArrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridlayout_homepage,parent,false);
        ViewHolder gridViewHolder=new ViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomGridAdapter.ViewHolder holder, int position) {
        GridData item = gridDataArrayList.get(position);
        holder.tvOverlayText.setText(item.getText());
        holder.price.setText(item.getPrice());
        holder.ivBackground.setImageResource(item.getImage());
//        holder.ivBackground.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String value="Click on"+item.getText()+" "+item.getPrice();
//                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return gridDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvOverlayText,price;
        ImageView ivBackground;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvOverlayText = itemView.findViewById(R.id.tvOverlayText);
            price = itemView.findViewById(R.id.price);
            ivBackground = itemView.findViewById(R.id.ivBackground);
        }
    }

    /*Context context;
    ArrayList<GridData> gridDataArrayList;

    public CustomGridAdapter(ArrayList<GridData>gridDataArrayList) {
        this();
    }

    public CustomGridAdapter(@NonNull Context context ArrayList<GridData> gridDataArrayList) {
        super(context,gridDataArrayList);
        this.context = context;
        this.gridDataArrayList =gridDataArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.gridlayout_homepage, parent, false);

            holder = new RecordHolder();
            holder.tvOverlayText = row.findViewById(R.id.tvOverlayText);
            holder.price = row.findViewById(R.id.price);
            holder.ivBackground = row.findViewById(R.id.ivBackground);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        GridData item = gridDataArrayList.get(position);
        holder.tvOverlayText.setText(item.getText());
        holder.price.setText(item.getPrice());
        holder.ivBackground.setImageResource(item.getImage());
        holder.ivBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value="Click on"+item.getText()+" "+item.getPrice();
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
            }
        });
        return row;

    }

    static class RecordHolder {
        TextView tvOverlayText,price;
        ImageView ivBackground;

    }*/
}
