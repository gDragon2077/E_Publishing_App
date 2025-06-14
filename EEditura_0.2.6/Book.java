public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private double price;
    private int deliveryDays;

    // Constructor
    public Book(String title, String author, int year, int pages, double price, int deliveryDays) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.price = price;
        this.deliveryDays = deliveryDays;
    }

    // Getter pentru titlul cartii
    public String getTitle() {
        return title;
    }

    // Getter pentru autor
    public String getAuthor() {
        return author;
    }

    // Getter pentru anul aparitiei
    public int getYear() {
        return year;
    }

    // Getter pentru numar de pagini
    public int getPages() {
        return pages;
    }

    // Getter pentru pret
    public double getPrice() {
        return price;
    }

    // Getter pentru numarul de zile pentru livrare
    public int getDeliveryDays() {
        return deliveryDays;
    }

    // Suprascriem toString pentru afisare mai usoara
    @Override
    public String toString() {
        return title + " by " + author + " (" + year + ")";
    }
}
