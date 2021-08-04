package com.exampl.galaxy.Homepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.exampl.galaxy.Login;
import com.exampl.galaxy.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MaterialToolbar topAppBar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView,dealday,gridView,brand;
    CategoryAdapter adapter;
    CustomGridAdapter customGridAdapter;
    CustomDealAdapter customDealAdapter;
    BrandAdapter brandAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        topAppBar = findViewById(R.id.topAppBar);
        navigationView = findViewById(R.id.navigationView);
        brand=findViewById(R.id.brand);
        drawerLayout = findViewById(R.id.drawerLayout);
        recyclerView=findViewById(R.id.recyclerView);
        gridView=findViewById(R.id.gridview);
        dealday=findViewById(R.id.dealday);
        navigationDrawer();
        categoryRecycler();
        sliderData();
        gridData();
        dealData();
        brandData();
    }

    private void brandData() {
        brand.setHasFixedSize(true);
        brand.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<BrandData> brandData = new ArrayList<>();

        brandData.add(new BrandData(R.drawable.brand1));
        brandData.add(new BrandData(R.drawable.brand2));
        brandData.add(new BrandData(R.drawable.brand3));
        brandData.add(new BrandData(R.drawable.brand4));
        brandData.add(new BrandData(R.drawable.brand5));
        brandData.add(new BrandData(R.drawable.brand6));
        brandData.add(new BrandData(R.drawable.brand7));
        brandData.add(new BrandData(R.drawable.brand8));
        brandData.add(new BrandData(R.drawable.brand9));
        brandAdapter = new BrandAdapter(brandData,this);
        brand.setAdapter(brandAdapter);

    }

    private void dealData() {
        dealday.setHasFixedSize(true);
        dealday.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<DealHelper> dealAdapters = new ArrayList<>();

        dealAdapters.add(new DealHelper(R.drawable.deal1));
        dealAdapters.add(new DealHelper(R.drawable.deal2));
        dealAdapters.add(new DealHelper(R.drawable.deal3));
        dealAdapters.add(new DealHelper(R.drawable.deal4));
        dealAdapters.add(new DealHelper(R.drawable.deal5));
        customDealAdapter = new CustomDealAdapter(dealAdapters,this);
        dealday.setAdapter(customDealAdapter);
    }

    private void gridData() {
        gridView.setHasFixedSize(true);
        gridView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<GridData> gridDataArrayList=new ArrayList<>();
        gridDataArrayList.add(new GridData(R.drawable.men,"MEN SHIRT UNDER","₹799"));
        gridDataArrayList.add(new GridData(R.drawable.kurta,"KURTA SET UNDER","₹999"));
        gridDataArrayList.add(new GridData(R.drawable.flipflop,"FLIP-FLOP UNDER","₹399"));
        gridDataArrayList.add(new GridData(R.drawable.makeup,"MAKEUP UNDER","₹599"));
        customGridAdapter=new CustomGridAdapter(this,gridDataArrayList);
        gridView.setAdapter(customGridAdapter);
    }

    private void sliderData() {
        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(R.drawable.banner1));
        sliderDataArrayList.add(new SliderData(R.drawable.banner2));
        sliderDataArrayList.add(new SliderData(R.drawable.banner3));
        sliderDataArrayList.add(new SliderData(R.drawable.banner4));

        // passing this array list inside our adapter class.
        SliderAdapter slideadapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(slideadapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();
    }

    private void categoryRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoryHelper> categoryAdapters = new ArrayList<>();

        categoryAdapters.add(new CategoryHelper(R.drawable.men3, "Men"));
        categoryAdapters.add(new CategoryHelper(R.drawable.women2, "Women"));
        categoryAdapters.add(new CategoryHelper(R.drawable.kid1, "Kids"));
        categoryAdapters.add(new CategoryHelper(R.drawable.beauty1, "Beauty"));
        categoryAdapters.add(new CategoryHelper(R.drawable.footwear5, "Footwear"));
        categoryAdapters.add(new CategoryHelper(R.drawable.jewellary1, "Jewellery"));
        categoryAdapters.add(new CategoryHelper(R.drawable.gadget1, "Gadget"));
        adapter = new CategoryAdapter(categoryAdapters,this);
        recyclerView.setAdapter(adapter);
    }


    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }

        });
    }
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                break;
            case R.id.category:
                startActivity(new Intent(Homepage.this,CategoryShop.class));
                break;
            case R.id.order:
                startActivity(new Intent(Homepage.this,OrderHistory.class));
                break;
            case R.id.wishlist:
                startActivity(new Intent(Homepage.this,WishlistPage.class));
                break;
            case R.id.Logout:
                startActivity(new Intent(Homepage.this,Login.class));
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
                           System.exit(0);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


}