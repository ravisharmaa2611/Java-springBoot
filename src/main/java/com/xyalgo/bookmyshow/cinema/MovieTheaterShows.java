package com.xyalgo.bookmyshow.cinema;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class MovieTheaterShows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long movieId;
    long theaterId;
    long showId;
}
