package com.xyalgo.bookmyshow.Controllers;


import com.xyalgo.bookmyshow.Services.*;
import com.xyalgo.bookmyshow.booking.Booking;
import com.xyalgo.bookmyshow.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController
public class BookingController {
    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;


    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    private MailService mailService;

    @PostMapping("/booking")

    public ModelAndView bookingSeats(@ModelAttribute DataModel model) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if (userService.isUserLoggedIn()) {
            Booking booking = new Booking();
            booking.setMovieName(movieService.getMovieById(model.getMovieId()).getMovieName());
            booking.setTheaterName(theaterService.getTheaterById(model.getTheaterId()).getTheaterName());
            booking.setUserName(model.getEmail());
            booking.setSeats(model.getSeats());
            booking.setShowId(model.getShowId());
            booking.setTotal(movieService.getMovieById(model.getMovieId()).getTicketPrice() * model.getSeats());
            bookingService.bookSeat(booking);
            mailService.sendEmail(booking.getUserName(), booking,
                    "Ticket Booking confirmation.");
            modelAndView = new ModelAndView("WebContent/bookingConfirmation")
                    .addObject("bookingList", booking).addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));
        }
        return modelAndView;
    }

    @GetMapping("/bookings")
    public ModelAndView bookingSummery() {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if (userService.isUserLoggedIn()) {
            modelAndView = new ModelAndView("WebContent/bookingConfirmation")
                    .addObject("bookingList", bookingService.getBookingByUserName(httpSession.getAttribute("email").toString())).addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));

        }

        return modelAndView;
    }

    @GetMapping("/allBookings")
    public ModelAndView getTheaterList() {
        return new ModelAndView("booking_table")
                .addObject("bookings", bookingService.getBookings());
    }
}
