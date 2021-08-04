package com.exampl.galaxy.Homepage.MenuPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> topwear = new ArrayList<String>();
        topwear.add("T-Shirts");
        topwear.add("Casual Shirt");
        topwear.add("Formal Shirts");
        topwear.add("Sweaters & Sweatshirts");
        topwear.add("Jacket");

        List<String> bottomwear = new ArrayList<String>();
        bottomwear.add("Jeans");
        bottomwear.add("Casual Trousers");
        bottomwear.add("Formal Trousers");
        bottomwear.add("Shorts");
        bottomwear.add("Track Pants/ Joggers");

        List<String> sports = new ArrayList<String>();
        sports.add("Sports Apparels");
        sports.add("Active T-Shirts");
        sports.add("Track  Pants & Shorts");
        sports.add("Jackets & Sweatshirts");
        sports.add("Sports Shoes");

        expandableListDetail.put("Topwear", topwear);
        expandableListDetail.put("Bottomwear", bottomwear);
        expandableListDetail.put("Sports & Active Wear", sports);
        return expandableListDetail;
    }
}
