package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Integer> {
    Optional<Period> findByNamePeriod(String namePeriod);
}