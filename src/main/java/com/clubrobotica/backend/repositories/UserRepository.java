package com.clubrobotica.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clubrobotica.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByControlNumber(String control_number);
}
