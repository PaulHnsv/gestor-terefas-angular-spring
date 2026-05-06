package com.paulo.gestortarefas.shared.security;

import com.paulo.gestortarefas.infra.persistence.security.UserCreateService;
import com.paulo.gestortarefas.infra.persistence.security.UserValidateService;
import com.paulo.gestortarefas.shared.utils.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserCreateService userCreateService;
    private final UserValidateService userValidateService;

    public AuthController(UserCreateService userCreateService, UserValidateService userValidateService) {
        this.userCreateService = userCreateService;
        this.userValidateService = userValidateService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {

        String token = userValidateService.validateUser(request);

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request){

        String token = userCreateService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }
}
