package com.paulo.gestortarefas.infra.persistence.security;

import com.paulo.gestortarefas.infra.ports.outbound.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return repository.findByUsername(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Usuário não encontrado: " + username));

    }
}

