package com.paulo.gestortarefas.infra.ports.inbound;

import com.paulo.gestortarefas.shared.utils.UserRequest;

public interface UserCreateUseCase {
    String registerUser(UserRequest user);
}
