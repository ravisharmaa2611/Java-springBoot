package com.xyalgo.bookmyshow.shows;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepo extends CrudRepository<Shows, Long> {
    List<Shows> findAll();
}
