package com.paulo.gestortarefas.infra.adapters.outbound.persistence;

import com.paulo.gestortarefas.infra.ports.outbound.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    @Autowired
    private SpringDataUserRepository userRepository;

    @Override
    public Optional<UserJpaEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserJpaEntity save(UserJpaEntity user) {
        return userRepository.save(user);
    }
}