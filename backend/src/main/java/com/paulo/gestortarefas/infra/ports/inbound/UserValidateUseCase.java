package com.paulo.gestortarefas.infra.ports.inbound;

import com.paulo.gestortarefas.shared.utils.UserRequest;

public interface UserValidateUseCase {
    String validateUser(UserRequest user);
}
