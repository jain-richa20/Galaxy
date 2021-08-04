package com.exampl.galaxy.Homepage.MenuPage;

public class MenShirtData {
    int image;
    String heading;
    String subheading;
    String price;

    public MenShirtData(int image, String heading, String subheading, String price) {
        this.image = image;
        this.heading = heading;
        this.subheading = subheading;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public String getPrice() {
        return price;
    }

}
