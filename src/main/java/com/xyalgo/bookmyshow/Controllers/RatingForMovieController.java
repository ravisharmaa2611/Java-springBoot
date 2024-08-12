package com.xyalgo.bookmyshow.Controllers;

import com.xyalgo.bookmyshow.Services.RatingForMovieService;
import com.xyalgo.bookmyshow.Services.UserService;
import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.ratingForMovie.RatingForMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RatingForMovieController {

@Autowired
    RatingForMovieService ratingForMovieService;
@Autowired
    UserService userService;



    @PostMapping("/rateMovie")
    public ModelAndView rateTheMovie(@ModelAttribute DataModel dataModel){

if(!ratingForMovieService.isUserAlreadyRated(dataModel.getUserId(), dataModel.getMovieId())) {
    RatingForMovie ratingForMovie = new RatingForMovie();
    ratingForMovie.setMovieId(dataModel.getMovieId());
    ratingForMovie.setUserId(dataModel.getUserId());
    ratingForMovie.setRatings(dataModel.getRatings());
    ratingForMovie.setComments(dataModel.getComments());
    ratingForMovieService.saveRateForMovie(ratingForMovie);
}
        return new ModelAndView("redirect:/");
    }

}
