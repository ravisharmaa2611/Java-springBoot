package com.xyalgo.bookmyshow.model;

import com.xyalgo.bookmyshow.shows.Shows;
import com.xyalgo.bookmyshow.theater.Theater;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class DataModel {
    Set<Theater> theaters;
    List<Theater> theaterIdList;
    long theaterId;
    long capacity;
    long movieId;
    long showId;
    long ticketPrice;

    long id;
    String movieName;
    String releaseDate;
    String image;
    String theaterName;
    String email;
    long seats;
    List<Shows> showsList;


String userName;
    long userId;
    String ratings;
    String comments;


    String name;
    String phone;

    String password;

}
