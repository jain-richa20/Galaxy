package com.exampl.galaxy.Homepage;

public class GridData {
    int image;
    String text;
    String price;

    public GridData(int image, String text,String price) {
        this.image = image;
        this.text = text;
        this.price=price;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getPrice() {
        return price;
    }
}
