package com.example.uberapp_tim3.model.DTO;

public class ReviewDTO {
    private int rating;
    private String comment;

    public ReviewDTO() {

    }

    public ReviewDTO(int rating, String comment) {

        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
