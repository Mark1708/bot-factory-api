package com.mark1708.botfactorycore.controller;

import com.mark1708.botfactorycore.facade.CredentialFacade;
import com.mark1708.botfactorycore.model.user.CredentialDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/users/{id}/credentials")
@OpenAPIDefinition(
    info = @Info(
        title = "Credential Controller",
        version = "1.0",
        description = "Документация к Users Credential API"
    )
)
public class CredentialController {

  private final CredentialFacade credentialFacade;

  @GetMapping
  @Operation(
      method = "Get user credentials",
      description = "Retrieve a user credentials (hashed password and salt)"
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved credentials"),
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
  public CredentialDto getCredential(@Parameter(description = "User's ID") @PathVariable Long id) {
    log.debug("Get credential: [{}]", id);
    return credentialFacade.getCredentialId(id);
  }

  @PutMapping
  @Operation(
      method = "Update user credentials",
      description = "Update user credentials (hashed password and salt)"
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Status of operation"),
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
  public boolean updateCredential(
      @Parameter(description = "User's ID") @PathVariable Long id,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Update user's credentials"
      ) @RequestBody CredentialDto credentialDto
  ) {
    log.debug("Update user's credential: [{}, {}]", id, credentialDto);
    return credentialFacade.updateCredential(id, credentialDto);
  }
}
