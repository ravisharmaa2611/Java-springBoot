package com.xyalgo.bookmyshow.cinema;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheaterShowsRepo extends CrudRepository<MovieTheaterShows, Long> {
    List<MovieTheaterShows> findAll();
}
