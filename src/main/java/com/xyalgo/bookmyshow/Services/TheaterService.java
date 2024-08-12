package com.xyalgo.bookmyshow.Services;

import com.xyalgo.bookmyshow.theater.Theater;
import com.xyalgo.bookmyshow.theater.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepo theaterRepo;

    public void updateTheater(Theater theater) {
        theaterRepo.save(theater);
    }

    boolean isTheaterPresent(String theaterName) {
        return theaterRepo.findByTheaterName(theaterName).isPresent();
    }

    public List<Theater> getTheaters() {
        return theaterRepo.findAll();
    }

    public boolean isTheaterPresent(long id) {
        return theaterRepo.findById(id).isPresent();
    }

    public Theater getTheaterById(long id) {
        return theaterRepo.findById(id).get();
    }

    public void removeTheater(long id) {
        theaterRepo.deleteById(id);
    }
}
