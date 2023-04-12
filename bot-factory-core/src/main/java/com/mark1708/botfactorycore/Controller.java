package com.mark1708.botfactorycore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
@OpenAPIDefinition(
		info = @Info(
				title = "Test Core Controller",
				version = "1.0",
				description = "Controller for test"
		)
)
public class Controller {

	/**
	 * Тестовый метод
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
	public ResponseEntity<String> test() {
		log.info("Test");
		return ResponseEntity.ok("OK");
	}

}
