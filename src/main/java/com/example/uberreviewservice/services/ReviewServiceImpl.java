package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        Optional<Review> review;
        try {
           review =  reviewRepository.findById(id);

           if(review.isEmpty()) {
               throw new EntityNotFoundException("Review with given id " + id + " not found");
           }
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getClass() == EntityNotFoundException.class) {
                throw new FetchNotFoundException("Review with given id " + id + " not found", id);
            }

            throw new FetchNotFoundException("Unable to fetch, please try again later",id);
        }

        return review;
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
      try {
          Review review =  reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
          reviewRepository.delete(review);
          return true;
      } catch (Exception e) {
          return false;
      }
    }

    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review review) {
            Review reviewToUpdate = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            if(review.getReviewContent() != null) {
                reviewToUpdate.setReviewContent(review.getReviewContent());
            }
            if(review.getRating() != null) {
                reviewToUpdate.setRating(review.getRating());
            }

            return reviewRepository.save(reviewToUpdate);
    }
}
