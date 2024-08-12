package com.xyalgo.bookmyshow.user;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    String name;
    String phone;
    String email;
    String password;


    @Column(name = "image")
    String image;

    @Transient
    public String getImagePath() {
        if (image == null || id== 0){
            return null;
        }

        return "user_photos/"+image;
    }



}
