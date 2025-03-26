import java.util.List;
import java.util.Objects;

public class Book {
    /// Variable Definition
    private final String title;
    private final List<String> authors;
    private final int publicationYear;
    private final int numberPages;
    private final double price;
    private final String deliveryPeriod;
    private final List<String> reviews;

    /// Class Constructor
    public Book(String title, List<String> authors, int publicationYear, int numberPages, double price, String deliveryPeriod, List<String> reviews){
        // Validate Date Constructor
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty!");
        }
        if(authors == null || authors.isEmpty()){
            throw new IllegalArgumentException("Author(s) cannot be null or empty!");
        }
        if(numberPages <= 0){
            throw new IllegalArgumentException("Number of pages must be positive!");
        }
        if(publicationYear <= 0){
            throw new IllegalArgumentException("Publication year must be positive!");
        }
        if(price < 0){
            throw new IllegalArgumentException("Price must be positive or zero!");
        }
        if(deliveryPeriod == null || deliveryPeriod.trim().isEmpty()){
            throw new IllegalArgumentException("Delivery period cannot be null or empty!");
        }

        // if is ok.
        this.title = title;
        this.authors = List.copyOf(authors);
        this.publicationYear = publicationYear;
        this.numberPages = numberPages;
        this.price = price;
        this.deliveryPeriod = deliveryPeriod;
        this.reviews = (reviews != null) ? List.copyOf(reviews) : List.of();
    }

    /// Get Functions
    public String getTitle(){
        return title;
    }
    public List<String> getAuthors(){
        return authors;
    }
    public String getDeliveryPeriod(){
        return deliveryPeriod;
    }
    public List<String> getReviews(){
        return reviews;
    }
    public int getPublicationYear(){
        return publicationYear;
    }
    public int getNumberPages(){
        return numberPages;
    }
    public double getPrice(){
        return price;
    }

    /// To make a summary of the book
    @Override
    public String toString(){
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationYear=" + publicationYear +
                ", numberPages=" + numberPages +
                ", price=" + price +
                ", deliveryPeriod='" + deliveryPeriod + '\'' +
                ", reviews=" + reviews +
                '}';
    }

    /// To see if two books are identical
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear &&
                numberPages == book.numberPages &&
                Double.compare(book.price, price) == 0 &&
                Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(deliveryPeriod, book.deliveryPeriod) &&
                Objects.equals(reviews, book.reviews);
    }

    /// Hash code for this book
    @Override
    public int hashCode() {
        return Objects.hash(title, authors, publicationYear, numberPages, price, deliveryPeriod, reviews);
    }
}
