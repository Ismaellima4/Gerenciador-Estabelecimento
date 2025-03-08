package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findUserByUsername(String username);
}
