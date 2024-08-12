package com.xyalgo.bookmyshow.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);

}
