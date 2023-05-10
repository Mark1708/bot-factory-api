package com.mark1708.botfactorycore.controller;

import com.mark1708.botfactorycore.model.user.UserSmallDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@OpenAPIDefinition(
    info = @Info(
        title = "Auth Controller",
        version = "1.0",
        description = "Controller for auth operations and Keycloak"
    )
)
public class AuthController {


  @PostMapping("/signup")
  public List<UserSmallDto> signUp() {
    return null;
  }
}
