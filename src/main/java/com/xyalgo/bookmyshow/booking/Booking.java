package com.xyalgo.bookmyshow.booking;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String movieName;
    String theaterName;
    String userName;
    long showId;
    long seats;
    long total;

}
