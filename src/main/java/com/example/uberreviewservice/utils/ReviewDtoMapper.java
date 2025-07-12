package com.example.uberreviewservice.utils;

import com.example.uberreviewservice.dtos.CreateReviewDto;
import com.example.uberreviewservice.dtos.ReviewDto;
import com.example.uberreviewservice.models.Review;
import org.springframework.stereotype.Component;

public class ReviewDtoMapper {

    public static ReviewDto convert(Review review) {
        return  ReviewDto
                .builder()
                .id(review.getId())
                .reviewContent(review.getReviewContent())
                .bookingId(review.getBooking().getId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();

    }
}
