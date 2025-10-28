package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.IBookReservationRepository;
import mk.ukim.finki.wp.lab.service.IBookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements IBookReservationService {
    private final IBookReservationRepository bookReservationRepository;

    public BookReservationServiceImpl(IBookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
      return bookReservationRepository.save(new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies));
    }
}
