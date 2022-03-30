package com.example.apidemo.persistence.repository;

import com.example.apidemo.persistence.entity.HolidayDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HolidayDescriptionRepository extends JpaRepository<HolidayDescription, Long> {

    Optional<HolidayDescription>findByDescription(String description);

}
