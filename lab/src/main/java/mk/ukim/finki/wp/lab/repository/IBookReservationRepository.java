package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.BookReservation;

public interface IBookReservationRepository {
    BookReservation save(BookReservation reservation);
}
