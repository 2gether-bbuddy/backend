package com.clubrobotica.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.clubrobotica.backend.models.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByControlNumber(String controlNumber);
    Optional<User> findByEmail(String email);
    void deleteByControlNumber(String controlNumber);

    // NUEVO MÉTODO: Trae a todos los que ya fueron aceptados
    @Query("SELECT u FROM User u WHERE u.role IN ('MIEMBRO', 'MESA', 'PRESIDENTE')")
    List<User> findAcceptedMembers();
}