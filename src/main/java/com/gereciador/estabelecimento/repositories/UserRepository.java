package com.gereciador.estabelecimento.repositories;

import com.gereciador.estabelecimento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findUserByUsername(String username);
}
