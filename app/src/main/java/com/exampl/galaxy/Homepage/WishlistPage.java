package com.exampl.galaxy.Homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.exampl.galaxy.R;

public class WishlistPage extends AppCompatActivity {
RecyclerView wishCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_page);
        wishCategory=findViewById(R.id.wishCategory);

    }
}