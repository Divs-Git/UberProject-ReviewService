package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Driver;
import com.example.uberreviewservice.models.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Integer countAllByRatingIsLessThanEqual(Double rating);

    List<Review> findAllByRatingIsLessThanEqual(Double rating);

    List<Review> findAllByCreatedAtIsBefore(Date createdAt);
}