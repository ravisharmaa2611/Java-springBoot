package com.xyalgo.bookmyshow.ratingForMovie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepo extends CrudRepository<RatingForMovie,Long> {

    List<RatingForMovie> findAll();
    Optional<RatingForMovie>findByUserIdAndMovieId(long userId,long movieId);

}
