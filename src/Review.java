// Review.java - reprezinta o recenzie pentru o carte

import java.io.Serializable;

public class Review implements Serializable {
    private String reviewer;
    private String text;
    private int rating; // rating intre 1 si 5

    public Review(String reviewer, String text, int rating) {
        this.reviewer = reviewer;
        this.text = text;
        this.rating = rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return reviewer + ": (" + rating + "/5) " + text;
    }
}
