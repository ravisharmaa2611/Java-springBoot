package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.booking.Booking;
import com.xyalgo.bookmyshow.booking.BookingRepo;
import com.xyalgo.bookmyshow.theater.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    TheaterRepo theaterRepo;
    @Autowired
    TheaterService theaterService;
    @Autowired
    MovieService movieService;

    public void bookSeat(Booking booking) {
        bookingRepo.save(booking);
    }


    public long availableSeats(long movieId, long theaterId, long showId) {
        String movieName = movieService.getMovieById(movieId).getMovieName();
        String theaterName = theaterService.getTheaterById(theaterId).getTheaterName();
        List<Booking> seatList = bookingRepo.findAll();
        final long[] count = {0};
        seatList.stream().filter((seats) -> seats.getMovieName().equals(movieName)).collect(Collectors.toList()).stream().filter((seat) ->

                seat.getTheaterName().equals(theaterName)).collect(Collectors.toList()).stream().filter((seats) -> seats.getShowId() == showId).forEach(item -> {

            count[0] = count[0] + item.getSeats();
        });
        return theaterRepo.findByTheaterName(theaterName).get().getCapacity() - count[0];
    }

    public List<Booking> getBookingByUserName(String userName) {
        return bookingRepo.findAllByUserName(userName);
    }

    public List<Booking> getBookings() {
        return bookingRepo.findAll();
    }
}
