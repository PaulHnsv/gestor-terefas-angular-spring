package com.paulo.gestortarefas.infra.persistence.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder;

    public CustomUserDetailsService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return User.builder()
                .username("admin")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();
    }

//    private final UsuarioRepository usuarioRepository;
//
//    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        Usuario usuario = usuarioRepository.findByEmail(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("Usuário não encontrado"));
//
//        return User.builder()
//                .username(usuario.getEmail())
//                .password(usuario.getSenha())
//                .roles(usuario.getRole()) // ex: "USER"
//                .build();
//    }
}

