package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Integer>{
    Period findByIdPeriod(Integer idPeriod);
}
