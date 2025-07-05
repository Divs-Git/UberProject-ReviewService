package com.example.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "booking_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate // this annotation tells that only handle it for object creation
    private Date createdAt;

    @Column(name = "updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // this annotation tells that only handle it for object update
    private Date updatedAt;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewContent='" + reviewContent + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
