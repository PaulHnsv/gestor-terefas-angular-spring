package com.paulo.gestortarefas.shared.errors;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Username já cadastrado: " + username);
    }
}
