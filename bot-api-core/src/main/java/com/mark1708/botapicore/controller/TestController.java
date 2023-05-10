package com.mark1708.botapicore.controller;

import com.mark1708.botapicore.client.TestTwoClient;
import com.mark1708.botapicore.model.entity.User;
import com.mark1708.botapicore.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@OpenAPIDefinition(
    info = @Info(
        title = "Test Core Controller",
        version = "1.0",
        description = "Controller for test"
    )
)
public class Controller {


  private final TestTwoClient testTwoClient;
  private final UserRepository userRepository;

  /**
   * Тестовый метод
   *
   * @return OK
   */
  @GetMapping
  @Operation(method = "Get all users", description = "Retrieve a list of all users")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
          @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
          @ApiResponse(
              responseCode = "403",
              description = "Accessing the resource you were trying to reach is forbidden"
          ),
          @ApiResponse(
              responseCode = "404",
              description = "The resource you were trying to reach is not found"
          )
      }
  )
  public List<User> test() {
    log.info("Test");
    List<User> all = userRepository.findAll();
//		log.info("{}", all);
    return all;
  }

}
