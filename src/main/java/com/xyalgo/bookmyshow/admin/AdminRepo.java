package com.xyalgo.bookmyshow.admin;


import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepo extends CrudRepository<Admin, Long> {
    List<Admin> findAll();

    Optional<Admin> findByUsername(String username);
}
