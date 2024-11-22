package com.example.recipeworld;
public class Recipe {
    private String title;
    private String subtitle;
    private float rating;
    private String imageUrl;

    public Recipe(String title, String subtitle, float rating, String imageUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public float getRating() { return rating; }
    public String getImageUrl() { return imageUrl; }
}
