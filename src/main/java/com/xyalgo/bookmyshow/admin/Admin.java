package com.xyalgo.bookmyshow.admin;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
public class Admin {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;
    String username;
    String password;
}




