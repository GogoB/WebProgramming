package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import jdk.jfr.Category;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    @PostConstruct
    public void init(){
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        books.add(new Book("1984", "Dystopia", 10.0));
        books.add(new Book("Brave New World", "Dystopia", 8.0));
        books.add(new Book("Fahrenheit 451", "Dystopia", 8.2));
        books.add(new Book("Brothers Karamazov", "Novel", 10.0));
        books.add(new Book("Casino Royale", "Fiction", 9.0));
        books.add(new Book("Live and Let Die", "Fiction", 9.3));
        books.add(new Book("Poslednite Selani", "Fiction", 9.5));
        books.add(new Book("Pirej", "Novel", 10.0));
        books.add(new Book("Hemija za 8mo Odd", "Torture", 1.0));
        books.add(new Book("Hemija za 1va Sredno", "Torture", 1.0));
    }
}
