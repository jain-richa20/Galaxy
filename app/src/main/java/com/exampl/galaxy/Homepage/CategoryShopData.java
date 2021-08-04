package com.exampl.galaxy.Homepage;

public class CategoryShopData {
    int photo;
    String title_cat, subtitle;

    public CategoryShopData(int photo, String title_cat, String subtitle) {
        this.photo = photo;
        this.title_cat = title_cat;
        this.subtitle = subtitle;
    }

    public int getPhoto() {
        return photo;
    }

    public String getTitle_cat() {
        return title_cat;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
