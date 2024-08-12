package com.xyalgo.bookmyshow.theater;

import com.xyalgo.bookmyshow.movies.Movies;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    long theaterId;
    @Column(name = "theater_name")
    String theaterName;
    @Column(name = "capacity")
    long capacity;

    @ManyToMany(mappedBy = "theaters")
    Set<Movies> movieSet;


}
