package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.movies.MovieRepo;
import com.xyalgo.bookmyshow.movies.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public void addMovie(Movies movies) {
        Optional<Movies> movieName = movieRepo.findByMovieName(movies.getMovieName());
        if (movieName.isPresent() && movieName.get().getMovieName().equals(movies.getMovieName())) {
        } else {
            movieRepo.save(movies);
        }
    }

    public void deleteProById(Long id) {
        movieRepo.deleteById(id);
    }

    public void UpdateMovieById(Movies movies) {
        movieRepo.save(movies);
    }

    public List<Movies> findMovieList() {
        return movieRepo.findAll();

    }

    public Movies getMovieById(Long id) {
        return movieRepo.findById(id).get();
    }

    public List<Movies> filterMovie() {
        return movieRepo.findAll().stream().filter((movie) -> movie.getMovieName().equals("vbn")).collect(Collectors.toList());
    }

    public List<Movies> nowPlayingMovies() {
        List<Movies> nowPlayingMovies = new ArrayList<>();
        movieRepo.findAll().forEach(movie -> {
            final long daysDiff = getDaysDiff(movie);
            if (daysDiff <= 0 && daysDiff > -7) {
                nowPlayingMovies.add(movie);
            }
        });
        return nowPlayingMovies;
    }

    public List<Movies> openingThisWeekMovies() {
        List<Movies> openingThisWeekMovies = new ArrayList<>();
        movieRepo.findAll().forEach(movie -> {
            final long daysDiff = getDaysDiff(movie);
            if (daysDiff > 0 && daysDiff < 7) {
                openingThisWeekMovies.add(movie);
            }
        });
        return openingThisWeekMovies;
    }

    public List<Movies> comingSoonMovies() {
        List<Movies> comingSoonMovies = new ArrayList<>();
        movieRepo.findAll().forEach(movie -> {
            final long daysDiff = getDaysDiff(movie);
            if (daysDiff >= 7) {
                comingSoonMovies.add(movie);
            }
        });
        return comingSoonMovies;
    }

    private long getDaysDiff(Movies movie) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        final LocalDate releaseDate = LocalDate.parse(movie.getReleaseDate(), formatter);
        final LocalDate currentDate = LocalDate.parse(LocalDate.now().format(formatter), formatter);
        return ChronoUnit.DAYS.between(currentDate, releaseDate);
    }
}
