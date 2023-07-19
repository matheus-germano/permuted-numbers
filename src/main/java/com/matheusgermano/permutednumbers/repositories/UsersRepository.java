package com.matheusgermano.permutednumbers.repositories;

import com.matheusgermano.permutednumbers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
