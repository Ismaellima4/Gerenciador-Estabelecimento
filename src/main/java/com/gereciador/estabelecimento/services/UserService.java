package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.controllers.dto.request.UserRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.UserResponseDTO;
import com.gereciador.estabelecimento.entities.User;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.mapper.UserMapper;
import com.gereciador.estabelecimento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserResponseDTO save(UserRequestDTO obj) throws NotFoundException {
        User user = this.mapper.toEntity(obj);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        User userReturn = this.userRepository.save(user);
        return this.mapper.toDTO(userReturn);
    }

    public UserResponseDTO update(Long primaryKey, UserRequestDTO obj) throws NotFoundException {
        User user = this.userRepository.findById(primaryKey).orElseThrow(() ->  new NotFoundException("User not found id: " + primaryKey));
        if (obj.password() != null && this.passwordEncoder.matches(obj.password(), user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(obj.password()));
        }

        if (obj.username() != null) user.setUsername(obj.username());
        if (obj.role() != null) user.setRole(obj.role());
        User userSaved = this.userRepository.save(user);
        return this.mapper.toDTO(userSaved);
    }

    public void delete(Long primaryKey) {
        this.userRepository.deleteById(primaryKey);
    }

    public UserResponseDTO getById(Long primaryKey) throws NotFoundException {
        User userFind = this.userRepository.findById(primaryKey).orElseThrow(() -> new NotFoundException("User not found id " + primaryKey));
        return this.mapper.toDTO(userFind);
    }

    public List<UserResponseDTO> getAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(this.mapper::toDTO)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found username " + username));
    }
}
