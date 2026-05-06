package com.paulo.gestortarefas.infra.persistence.security;

import com.paulo.gestortarefas.infra.ports.inbound.UserValidateUseCase;
import com.paulo.gestortarefas.shared.security.JwtService;
import com.paulo.gestortarefas.shared.utils.UserRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserValidateService implements UserValidateUseCase {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserValidateService(JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String validateUser(UserRequest user){
        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        return jwtService.generateToken(user.getUsername());
    }
}
