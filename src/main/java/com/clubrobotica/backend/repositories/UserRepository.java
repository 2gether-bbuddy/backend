package com.clubrobotica.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clubrobotica.backend.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByControlNumber(String control_number);
    Optional<User> findByEmail(String email);
    Optional<User> deleteByControlNumber(String control_number);
}
