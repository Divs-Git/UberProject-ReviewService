package com.example.uberreviewservice.services;

import com.example.uberreviewservice.models.Booking;
import com.example.uberreviewservice.models.Driver;
import com.example.uberreviewservice.repositories.BookingRepository;
import com.example.uberreviewservice.repositories.DriverRepository;
import com.example.uberreviewservice.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final DriverRepository driverRepository;


    public ReviewService(ReviewRepository reviewRepository,BookingRepository bookingRepository,DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("***********************");

//        Review r = Review
//                    .builder()
//                    .reviewContent("Amazing ride quality")
//                    .rating(5.0)
//                    .build();
//
//
//        Booking b = Booking.builder().endTime(new Date()).review(r).build();
//        reviewRepository.save(r); // This code executes sql query.
//
//        bookingRepository.save(b);
//
//        List<Review> reviews = reviewRepository.findAll();
//        for(Review review: reviews) {
//            System.out.println(review.getReviewContent());
//        }
//
//        reviewRepository.deleteById(2);

//        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "DL12344");
//        driver.ifPresent(value -> System.out.println(value.getName()));

//        Optional<Driver> driver = driverRepository.hqFindByIdAndLicense(1L, "DL12344");
//        driver.ifPresent((d) -> System.out.println(d.getName()));

        List<Long> driverIds = Arrays.asList(1L,2L,3L,4L);
        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);
//
//        List<Booking> bookings = bookingRepository.findAllByIdIn(drivers);

        for(Driver driver: drivers) {
            List<Booking> bookings = driver.getBookings();
            bookings.forEach(booking -> System.out.println(booking.getId()));
        }
    }
}
