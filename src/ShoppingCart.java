import java.util.List;

public class ShoppingCart {
    private double price;
    private Book[] books;
    private User user;
    int nrOfBooks;

    ShoppingCart(){
        books = new Book[2500]; /// To Do - Trebiue sa fie facuta dinamnica
    }

    // To Do - Trebuie vazut cum se face integrarea cu un sistem de management al cartilor.
    public void addBook(Book book){
        this.books[nrOfBooks ++] = book;
    }


    public double price(){
        double price = 0;
        for(int i = 0; i < nrOfBooks; i++){
            price += books[i].getPrice();
        }
        return price;
    }

}
