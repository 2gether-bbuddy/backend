package com.clubrobotica.backend.repositories;

import com.clubrobotica.backend.models.Application;
import com.clubrobotica.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // Para filtrar las que ve el admin
    List<Application> findByState(String state);

    // ¡ESTA ES LA QUE NECESITAMOS PARA EL REGISTRO!
    Optional<Application> findByUser(User user);
}