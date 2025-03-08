package com.gereciador.estabelecimento.controllers;

import com.gereciador.estabelecimento.controllers.dto.request.LoginResquestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.LoginResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.request.UserRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.UserResponseDTO;
import com.gereciador.estabelecimento.entities.User;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.services.UserService;
import com.gereciador.estabelecimento.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutheticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginResquestDTO loginResquestDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginResquestDTO.username(), loginResquestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        String token = this.tokenUtil.generateToken((User) auth.getPrincipal());

        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) throws NotFoundException {
        UserResponseDTO responseDTO = this.userService.save(userRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
