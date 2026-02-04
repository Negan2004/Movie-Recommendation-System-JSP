package com.movierecommendation.model;

public class Movie {

    private String title;
    private String genre;
    private String description;
    private double rating;
    private String poster;
    private String teaserId;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public String getTeaserId() {
        return teaserId;
    }

    public void setTeaserId(String teaserId) {
        this.teaserId = teaserId;
    }

}
