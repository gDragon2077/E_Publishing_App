import java.util.List;
import java.util.Objects;
// Partea de review-uri o sa fie modificata.
public class Book {
    // In clasa doar se stocheaza data, corectitudinea acestora se verifica doar in clasa din care se citesc datele sau in eventualitatea unei clase de verificare.
    private final String title;
    private final String author;
    private final int nrOfPages;
    private final int releaseYear;
    private double price;
    private int deliveryPeriod;
    private List<String> reviews;

    // Constructor pentru intierea cartii
    Book(String title, String author, int nrOfPages, int releaseYear, int deliveryPeriod, double price, List<String> reviews){
        this.title = title;
        this.author = author;
        this.nrOfPages = nrOfPages;
        this.releaseYear = releaseYear;
        this.deliveryPeriod = deliveryPeriod;
        this.price = price;
        this.reviews = List.copyOf(reviews);
    }

    /// Getters
    public List<String> getReviews(){
        return reviews;
    }
    public double getPrice(){
        return price;
    }

    /// Setters
    public void setReviews(List<String> reviews){
        this.reviews = List.copyOf(reviews);
    }

    @Override
    public String toString(){
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + author +
                ", publicationYear=" + releaseYear +
                ", numberPages=" + nrOfPages +
                ", price=" + price +
                ", deliveryPeriod='" + deliveryPeriod + '\'' +
                '}';
    }

    @Override
    public int hashCode(){
        return Objects.hash(title, author, releaseYear, nrOfPages); // Pretul, durata de licrare cat si review-urile se exclud deoarece sunt variabile schimbatoare.
    }
}
