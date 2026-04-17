package com.paulo.gestortarefas.infra.ports.outbound;

import com.paulo.gestortarefas.infra.adapters.outbound.persistence.UserJpaEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserJpaEntity> findByUsername(String username);

}
