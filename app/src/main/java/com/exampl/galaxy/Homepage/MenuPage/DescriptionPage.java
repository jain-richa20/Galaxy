package com.exampl.galaxy.Homepage.MenuPage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exampl.galaxy.Cart.CartProduct;
import com.exampl.galaxy.R;

public class DescriptionPage extends AppCompatActivity {
    private ViewPager expandedImage;
    Button addwish, addcart;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar desctoolbar;
    TextView two;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);
        expandedImage = (ViewPager) findViewById(R.id.expandedImage);
        viewPagerAdapter = new ViewPagerAdapter(this);
        two = findViewById(R.id.two);
        expandedImage.setAdapter(viewPagerAdapter);
        addcart = findViewById(R.id.addcart);
        addwish = findViewById(R.id.addwish);
        desctoolbar = (Toolbar) findViewById(R.id.desctoolbar);
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcart.setText("");
                addcart.setText("Go To Bag");
                startActivity(new Intent(DescriptionPage.this, CartProduct.class));
            }
        });
        desctoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DescriptionPage.this, MenShirt.class));
            }
        });
        desctoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/Plain");
                        String shareBody = two.getText().toString();

                        // subject of the content. you can share anything
                        String shareSubject = "Highlander";

                        // passing body of the content
                        intent.putExtra(Intent.EXTRA_TEXT, shareBody);

                        // passing subject of the content
                        intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                        startActivity(Intent.createChooser(intent, "Share This Product"));
                        break;
                    case R.id.wish:
                        Toast.makeText(DescriptionPage.this, "Wish", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.cart:
                        startActivity(new Intent(DescriptionPage.this, CartProduct.class));
                        break;
                }
                return false;
            }
        });
    }
}