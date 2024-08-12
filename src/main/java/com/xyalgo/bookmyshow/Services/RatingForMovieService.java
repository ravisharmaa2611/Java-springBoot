package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.model.DataModel;
import com.xyalgo.bookmyshow.ratingForMovie.RatingForMovie;
import com.xyalgo.bookmyshow.ratingForMovie.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingForMovieService {


    @Autowired
    RatingRepo ratingRepo;

    public void saveRateForMovie(RatingForMovie ratingForMovie){
        ratingRepo.save(ratingForMovie);
    }

    public boolean isUserAlreadyRated(long userId,long movieId){
        return ratingRepo.findByUserIdAndMovieId(userId,movieId).isPresent();
    }
}
