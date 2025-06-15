import java.util.List;

// Clasa Book reprezinta o carte din catalog
public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private double price;
    private int deliveryTime;
    private List<String> reviews; // Lista de recenzii, maxim 5
    private String path;

    // Constructorul principal
    public Book(String title, String author, int year, int pages, double price, int deliveryTime, List<String> reviews, String path) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.reviews = reviews; // Salvam lista de recenzii
        this.path = path;
    }

    // Getteri si setteri
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public String getPath() {
        return path;
    }
    
    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
