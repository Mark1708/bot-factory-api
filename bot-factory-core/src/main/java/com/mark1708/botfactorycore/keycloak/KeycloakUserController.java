package com.mark1708.botfactorycore.keycloak;

import com.mark1708.botfactorycore.facade.UserFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.role.RoleDto;
import com.mark1708.botfactorycore.model.user.UserDto;
import com.mark1708.botfactorycore.model.user.UserInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keycloak/users")
@OpenAPIDefinition(
    info = @Info(
        title = "User Controller",
        version = "1.0",
        description = "Документация к Users API"
    )
)
public class KeycloakUserController {

  private final UserFacade userFacade;

  @GetMapping("/search")
  @Operation(method = "Get all users", description = "Retrieve a list of searched users")
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
  public List<UserDto> getAllSearchedUsers(
      @Parameter(description = "Search query")
      @RequestParam(name = "search", required = false) String search,
      @Parameter(description = "Offset of result")
      @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
      @Parameter(description = "Limit of result")
      @RequestParam(name = "limit", defaultValue = "20", required = false) int limit
  ) {
    log.debug("Get all searched users: [{}, {}, {}]", search, offset, limit);
    return userFacade.getAllSearchedUsers(search, offset, limit);
  }

  @GetMapping("/{query}")
  public UserDto getUser(@PathVariable String query, @RequestParam(name = "type") String type) {
    log.debug("Get user: [{}, {}]", query, type);
    return userFacade.getUser(query, type);
  }

  @GetMapping("/count")
  @Operation(method = "Get count of users", description = "Retrieve a count of users")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved count"),
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
  public Integer getUsersCount() {
    log.debug("Get users count");
    return userFacade.getUsersCount();
  }
}
