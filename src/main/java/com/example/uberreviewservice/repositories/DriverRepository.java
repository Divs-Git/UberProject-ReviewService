package com.example.uberreviewservice.repositories;

import com.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    // raw mysql query, error is thrown at runtime
    @Query(nativeQuery = true, value = "SELECT * FROM DRIVER WHERE id = :id and license_number = :license")
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);

    // Hibernate query, error is thrown at compile time
    @Query("SELECT d FROM Driver as d WHERE d.id = :id and d.licenseNumber = :ln")
    Optional<Driver> hqFindByIdAndLicense(Long id, String ln);
}
