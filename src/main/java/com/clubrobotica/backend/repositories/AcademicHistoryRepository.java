package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.AcademicHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicHistoryRepository extends JpaRepository<AcademicHistory, Integer> {

    // 1. El método nuevo que usa el Robot para saber quiénes están activos
    List<AcademicHistory> findByCurrentTrue();

    // 2. El método que le faltaba a tu AcademicHistoryService para no dar error
    @Query("SELECT h FROM AcademicHistory h WHERE h.user.controlNumber = :controlNumber")
    List<AcademicHistory> findByUser(@Param("controlNumber") String controlNumber);
}