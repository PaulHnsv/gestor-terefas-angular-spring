package com.paulo.gestortarefas.infra.persistence.security;

import com.paulo.gestortarefas.infra.adapters.outbound.persistence.UserJpaEntity;
import com.paulo.gestortarefas.infra.ports.inbound.UserCreateUseCase;
import com.paulo.gestortarefas.infra.ports.outbound.UserRepository;
import com.paulo.gestortarefas.shared.errors.UserAlreadyExistsException;
import com.paulo.gestortarefas.shared.security.JwtService;
import com.paulo.gestortarefas.shared.utils.Role;
import com.paulo.gestortarefas.shared.utils.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCreateService implements UserCreateUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService service;

    public UserCreateService(PasswordEncoder passwordEncoder, UserRepository repository, JwtService service){
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.service = service;
    }

    public String registerUser(UserRequest user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        if (repository.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException(user.getUsername());
        }

        UserJpaEntity newUser = new UserJpaEntity(
                null,
                user.getUsername(),
                hashedPassword,
                user.getEmail(),
                Role.USER,
                LocalDateTime.now()
        );

        repository.save(newUser);

        return service.generateToken(user.getUsername());
    }
}
