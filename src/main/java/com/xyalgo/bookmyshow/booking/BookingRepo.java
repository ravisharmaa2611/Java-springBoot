package com.xyalgo.bookmyshow.booking;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepo extends CrudRepository<Booking, Long> {
    List<Booking> findAll();

    List<Booking> findAllByUserName(String userName);
}
