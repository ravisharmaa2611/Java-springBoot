package com.xyalgo.bookmyshow.ratingForMovie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating_review")
public class RatingForMovie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    long movieId;
    long userId;
    String ratings;
    String comments;
}
