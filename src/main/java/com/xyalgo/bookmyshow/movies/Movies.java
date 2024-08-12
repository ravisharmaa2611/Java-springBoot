package com.xyalgo.bookmyshow.movies;


import com.xyalgo.bookmyshow.theater.Theater;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    long movieId;
    @Column(name = "movie_name")
    String movieName;
    @Column(name = "ticket_price")
    long ticketPrice;
    @Column(name = "release_date")
    String releaseDate;
    @Column(name = "image")
    String image;

    @ManyToMany
    @JoinTable(
            name = "movie_to_theater",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "theater_id"))
    Set<Theater> theaters;

    @Transient
    public List<String> getPhotosImagePath() {
        if (image == null || movieId == 0) return null;
        List<String> imagePathList = new ArrayList<>();
        for (String photo : image.split(",")) {
            imagePathList.add("movie_photos/" + photo);
        }
        return imagePathList;
    }

}
