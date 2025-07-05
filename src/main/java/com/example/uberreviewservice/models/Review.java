package com.example.uberreviewservice.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "booking_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "review_content", nullable = false)
    String reviewContent;

    @Column(name = "rating")
    Double rating;

    @Column(name = "created_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate // this annotation tells that only handle it for object creation
    Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // this annotation tells that only handle it for object update
    Date updatedAt;
}
