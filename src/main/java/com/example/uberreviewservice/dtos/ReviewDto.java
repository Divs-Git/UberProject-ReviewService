package com.example.uberreviewservice.dtos;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;

    private String reviewContent;

    private Double rating;

    private Long bookingId;

    private Date createdAt;

    private Date updatedAt;


}
