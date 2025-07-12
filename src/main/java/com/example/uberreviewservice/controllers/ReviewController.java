package com.example.uberreviewservice.controllers;

import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllReviewById(@PathVariable Long id) {
        try {
            Optional<Review> review = reviewService.findReviewById(id);
            return new ResponseEntity<>(review,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview() {
        List<Review> reviews = reviewService.findAllReviews();
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id) {
        boolean isDeleted = reviewService.deleteReviewById(id);
        if(!isDeleted) return new ResponseEntity<>("Unable to delete the selected review",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> publishReview(@RequestBody Review review) {
        Review newReview = reviewService.publishReview(review);
        return new ResponseEntity<>(newReview,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody Review review) {
        try {
            Review updatedReview = reviewService.updateReview(id,review);
            return new ResponseEntity<>(updatedReview,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
