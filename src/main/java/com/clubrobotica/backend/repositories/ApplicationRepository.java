package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Application;
import com.clubrobotica.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // Consulta optimizada para traer TODOS los datos de la solicitud y del usuario (incluyendo su carrera)
    @Query("SELECT a FROM Application a JOIN FETCH a.user u LEFT JOIN FETCH u.career WHERE a.state = :state")
    List<Application> findByState(@Param("state") String state);

    Optional<Application> findByUser(User user);
}