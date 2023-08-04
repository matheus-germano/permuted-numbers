package com.matheusgermano.permutednumbers.repositories;

import com.matheusgermano.permutednumbers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email); // optional para evitar o null pointer exception
}
