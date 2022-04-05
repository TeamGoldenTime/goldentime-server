package com.api.goldentime.repository;

import com.api.goldentime.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentifier(String identifier);
}
