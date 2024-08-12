package com.xyalgo.bookmyshow.cinema;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheaterRepo extends CrudRepository<MovieTheater, Long> {
    List<MovieTheater> findAll();
}
