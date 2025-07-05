package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("***********************");

        Review r = Review
                    .builder()
                    .reviewContent("Amazing ride quality")
                    .rating(5.0)
                    .build();
//        reviewRepository.save(r); // This code executes sql query.

        List<Review> reviews = reviewRepository.findAll();
        for(Review review: reviews) {
            System.out.println(review.getReviewContent());
        }

        reviewRepository.deleteById(2);
    }
}
