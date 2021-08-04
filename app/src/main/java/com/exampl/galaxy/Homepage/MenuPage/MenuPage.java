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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.exampl.galaxy.Homepage.CustomGridAdapter;
import com.exampl.galaxy.Homepage.GridData;
import com.exampl.galaxy.Homepage.Homepage;
import com.exampl.galaxy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuPage extends AppCompatActivity {
Toolbar mentoolbar;
RecyclerView categoryformen;
RelativeLayout relativeLayout;
ExpandableListView listView;
ExpandableListAdapter expandableListAdapter;
List<String> expandableListTitle;
HashMap<String, List<String>> expandableListDetail;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        mentoolbar=findViewById(R.id.mentoolbar);
        categoryformen=findViewById(R.id.categoryformen);
        listView=findViewById(R.id.listView);
        relativeLayout=findViewById(R.id.relativeLayout);
        expandableListDetail = ExpandableListDataItems.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomizedExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        listView.setAdapter(expandableListAdapter);
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });
        mentoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPage.this, Homepage.class));
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition)
                        + " -> "
                        + expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        menCategory();

    }

    private void menCategory() {
        categoryformen.setHasFixedSize(true);
        categoryformen.setLayoutManager(new GridLayoutManager(this,3));

        ArrayList<MenData> menDataArrayList=new ArrayList<>();
        menDataArrayList.add(new MenData(R.drawable.menshirtpic,"Men's Shirts"));
        menDataArrayList.add(new MenData(R.drawable.mentshirt,"Men's T-Shirts"));
        menDataArrayList.add(new MenData(R.drawable.watch,"Mens's Watches"));
        menDataArrayList.add(new MenData(R.drawable.trouser,"Men's Trousers"));
        menDataArrayList.add(new MenData(R.drawable.headphonesmen,"Headphones"));
        menDataArrayList.add(new MenData(R.drawable.backpack,"Trolleys & Backpacks"));
        menDataArrayList.add(new MenData(R.drawable.slipper,"Men's Flip-Flops"));
        menDataArrayList.add(new MenData(R.drawable.menshoes,"Men's Casual Shoes"));
        menDataArrayList.add(new MenData(R.drawable.men_sport,"Men's Sport Shoes"));
        menDataArrayList.add(new MenData(R.drawable.belt_wallet,"Belts & Wallets"));
        menDataArrayList.add(new MenData(R.drawable.men_trackpant,"Men's Trackpants"));
        CustomMenCategory customMenCategory=new CustomMenCategory(this,menDataArrayList);
        categoryformen.setAdapter(customMenCategory);
    }
}