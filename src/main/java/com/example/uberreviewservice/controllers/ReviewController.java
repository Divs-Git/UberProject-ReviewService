package com.example.uberreviewservice.controllers;

import com.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapter;
import com.example.uberreviewservice.dtos.CreateReviewDto;
import com.example.uberreviewservice.dtos.ReviewDto;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import com.example.uberreviewservice.utils.ReviewDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllReviewById(@PathVariable Long id) {
        try {
            Review review = reviewService.findReviewById(id).orElseThrow(() -> new EntityNotFoundException("Review with given id not found"));
            ReviewDto reviewDto = ReviewDtoMapper.convert(review);
            return new ResponseEntity<>(reviewDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReview() {
        List<Review> reviews = reviewService.findAllReviews();

        List<ReviewDto> reviewDtos = new ArrayList<>();

        for(Review review: reviews) {
            reviewDtos.add(ReviewDtoMapper.convert(review));
        }

        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id) {
        boolean isDeleted = reviewService.deleteReviewById(id);
        if (!isDeleted)
            return new ResponseEntity<>("Unable to delete the selected review", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request) {
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(request);
        if (incomingReview == null) {
            return new ResponseEntity<>("Invalid argument", HttpStatus.BAD_REQUEST);
        }
        Review newReview = reviewService.publishReview(incomingReview);
        ReviewDto response = ReviewDtoMapper.convert(newReview);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody CreateReviewDto review) {
        try {
            Review reviewToUpdate = createReviewDtoToReviewAdapter.convertDto(review);

            if (reviewToUpdate == null) {
                return new ResponseEntity<>("Invalid argument", HttpStatus.BAD_REQUEST);
            }

            Review updatedReview = reviewService.updateReview(id, reviewToUpdate);

            ReviewDto response = ReviewDtoMapper.convert(updatedReview);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
