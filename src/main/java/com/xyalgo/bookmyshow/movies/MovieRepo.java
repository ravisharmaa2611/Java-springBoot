package com.xyalgo.bookmyshow.movies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends CrudRepository<Movies, Long> {
    List<Movies> findAll();

    Optional<Movies> findByMovieName(String movieName);
}
