package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.cinema.MovieTheaterShows;
import com.xyalgo.bookmyshow.cinema.MovieTheaterShowsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieTheaterShowsService {
    @Autowired
    MovieTheaterShowsRepo movieTheaterShowsRepo;

    public List<MovieTheaterShows> showsList(long movieId) {
        return movieTheaterShowsRepo.findAll().stream().filter((shows) -> shows.getMovieId() == movieId).collect(Collectors.toList());
    }
}
