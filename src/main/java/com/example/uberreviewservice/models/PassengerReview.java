package com.example.uberreviewservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "passenger_review")
public class PassengerReview extends Review{

    @Column(nullable = false)
    private String passengerReviewComment;

    @Column(nullable = false)
    private String passengerRating;

}
