// Book.java - reprezinta o carte din catalogul editurii

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private String title;
    private List<String> authors;
    private int year;
    private int pages;
    private double price;
    private int deliveryDays;
    private List<Review> reviews;

    public Book(String title, List<String> authors, int year, int pages, double price, int deliveryDays, List<Review> reviews) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.pages = pages;
        this.price = price;
        this.deliveryDays = deliveryDays;
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return title + " by " + String.join(", ", authors) +
               ", " + year + ", " + pages + " pages, " +
               price + " RON, delivery in " + deliveryDays + " days";
    }
}
