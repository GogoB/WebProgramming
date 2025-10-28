package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.IBookReservationRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;

@Repository
public class InMemoryBookReservationRepository implements IBookReservationRepository {

    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
