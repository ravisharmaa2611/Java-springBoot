package com.xyalgo.bookmyshow.Controllers;


import com.xyalgo.bookmyshow.Services.*;
import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.movies.Movies;
import com.xyalgo.bookmyshow.theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class UserModelController {

    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;


    @Autowired
    HttpSession httpSession;

    @Autowired
    BookingService bookingService;

    @Autowired
    MovieTheaterShowsService movieTheaterShowsService;
    @Autowired
    UserService userService;
    @Autowired
    RatingForMovieService ratingForMovieService;

    @PostMapping("/movie")
    public ModelAndView movieSelectShow(@ModelAttribute DataModel model) {
        System.out.println("model is:  ----------" + model);
        ModelAndView modelAndView = new ModelAndView("WebContent/movie-select-show");
        modelAndView.addObject("movie", movieService.getMovieById(model.getMovieId()));
        modelAndView.addObject("theaterList", movieService.getMovieById(model.getMovieId()).getTheaters());
        modelAndView.addObject("showsList", movieTheaterShowsService.showsList(model.getMovieId()));
        modelAndView.addObject("isUserAlreadyRated",true);
        if (httpSession.getAttribute("email") != null) {
            modelAndView.addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));
            modelAndView.addObject("isUserAlreadyRated", ratingForMovieService.isUserAlreadyRated(userService.getUserByEmail(httpSession.getAttribute("email").toString()).getId(), model.getMovieId()));

        }
        return modelAndView;
    }

    @GetMapping("/pic-a-movie")
    public ModelAndView picAmovie() {

        ModelAndView modelAndView = new ModelAndView("WebContent/pic-a-movie");

        return modelAndView;
    }


    @GetMapping("/movie-payment")
    public ModelAndView moviePayment() {
        ModelAndView modelAndView = new ModelAndView("WebContent/movie-payment");
        return modelAndView;
    }

    @PostMapping("/movie-booking")
    public ModelAndView movieTicketBooking(@ModelAttribute DataModel model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if (userService.isUserLoggedIn()) {
            Theater theater = theaterService.getTheaterById(model.getTheaterId());
            Movies movie = movieService.getMovieById(model.getMovieId());
            modelAndView = new ModelAndView("WebContent/movie-booking");

            modelAndView.addObject("theater", theater);
            modelAndView.addObject("movie", movie);
            modelAndView.addObject("showId", model.getShowId());
            modelAndView.addObject("availableSeats", bookingService.availableSeats(model.getMovieId(), model.getTheaterId(), model.getShowId()));
            if (httpSession.getAttribute("email") != null)
                modelAndView.addObject("user", userService.getUserByEmail(httpSession.getAttribute("email").toString()));
        }

        return modelAndView;
    }
}
