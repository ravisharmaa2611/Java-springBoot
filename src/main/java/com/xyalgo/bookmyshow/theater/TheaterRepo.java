package com.xyalgo.bookmyshow.theater;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepo extends CrudRepository<Theater, Long> {
    List<Theater> findAll();

    Optional<Theater> findByTheaterName(String theaterName);
}
