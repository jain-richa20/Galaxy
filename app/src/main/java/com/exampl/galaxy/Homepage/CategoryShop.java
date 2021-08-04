package com.exampl.galaxy.Homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.exampl.galaxy.R;

import java.util.ArrayList;

public class CategoryShop extends AppCompatActivity {

    RecyclerView categoryShop;
    CustomCategoryShop customCategoryShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_shop);
        categoryShop=findViewById(R.id.categoryShop);
        shopCategory();
    }

    private void shopCategory() {
        categoryShop.setHasFixedSize(true);
        categoryShop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CategoryShopData> categoryShopData = new ArrayList<>();

        categoryShopData.add(new CategoryShopData(R.drawable.women,"Women","Kurtas & Suits, Tops & Tees...."));
        categoryShopData.add(new CategoryShopData(R.drawable.men_category,"Men","Shirts, T-shirts,Jeans, Trouser ....."));
        categoryShopData.add(new CategoryShopData(R.drawable.kids,"Kids","Brands, Clothing, Footwear...."));
        categoryShopData.add(new CategoryShopData(R.drawable.cosmetics,"Beauty & Personal Care","Makeup, Fragrances, Grooming....."));
        categoryShopData.add(new CategoryShopData(R.drawable.headphones,"Gadgets","HeadPhones, Smart wearables"));
        categoryShopData.add(new CategoryShopData(R.drawable.shoe,"Footwear","Flats, Shoes, Sandals...."));
        customCategoryShop = new CustomCategoryShop(categoryShopData,this);
        categoryShop.setAdapter(customCategoryShop);
    }
}