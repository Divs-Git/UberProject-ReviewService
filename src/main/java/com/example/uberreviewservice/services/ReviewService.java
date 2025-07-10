package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.BookingRepository;
import com.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;


    public ReviewService(ReviewRepository reviewRepository,BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("***********************");

        Review r = Review
                    .builder()
                    .reviewContent("Amazing ride quality")
                    .rating(5.0)
                    .build();


        Booking b = Booking.builder().endTime(new Date()).review(r).build();
        reviewRepository.save(r); // This code executes sql query.

        bookingRepository.save(b);

        List<Review> reviews = reviewRepository.findAll();
        for(Review review: reviews) {
            System.out.println(review.getReviewContent());
        }

        reviewRepository.deleteById(2);
    }
}
