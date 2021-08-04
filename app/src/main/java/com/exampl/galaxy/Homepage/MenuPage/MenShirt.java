package com.exampl.galaxy.Homepage.MenuPage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.exampl.galaxy.R;

import java.util.ArrayList;

public class MenShirt extends AppCompatActivity {

    Toolbar mtoolbar;
    RecyclerView menShirt;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_shirt);
        mtoolbar=findViewById(R.id.mtoolbar);
        menShirt=findViewById(R.id.menShirt);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenShirt.this, MenuPage.class));
            }
        });
        menShirtList();

    }

    private void menShirtList() {
        menShirt.setHasFixedSize(true);
        menShirt.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<MenShirtData> menShirtDataArrayList=new ArrayList<>();
        menShirtDataArrayList.add(new MenShirtData(R.drawable.shirt1,"HIGHLANDER","Men Slim Fit Casual Shirt","₹499"));
        menShirtDataArrayList.add(new MenShirtData(R.drawable.shirt2,"WROGN","Checked Casual Shirt","₹1559"));
        menShirtDataArrayList.add(new MenShirtData(R.drawable.shirt3,"HERE&NOW","Men Regular Fit Casual Shirt","₹1599"));
        menShirtDataArrayList.add(new MenShirtData(R.drawable.shirt4,"HARVARD","Slim Fit Casual Shirt","₹999"));

        ShirtMenAdapter shirtMenCategory=new ShirtMenAdapter(this,menShirtDataArrayList);
        menShirt.setAdapter(shirtMenCategory);
    }
}