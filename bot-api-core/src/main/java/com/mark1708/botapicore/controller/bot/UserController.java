package com.mark1708.botapicore.controller.bot;


import com.mark1708.botapicore.facade.UserFacade;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.user.CreateUserDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.botapicore.model.user.UserInfoDto;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot/users")
public class UserController {

  private final UserFacade userFacade;

  @GetMapping
  public List<UserDto> getUsers(@RequestHeader("Authorization") String apiKey) {
    return userFacade.getUsers(apiKey);
  }

  @GetMapping("/search")
  public List<UserDto> searchUsers(
      @RequestHeader("Authorization") String apiKey,
      @RequestParam(name = "query") String query,
      @Parameter(description = "username, firstName, lastName, state")
      @RequestParam(name = "type", defaultValue = "username") String type
  ) {
    return userFacade.searchUsers(apiKey, query, type);
  }

  @GetMapping("/{query}")
  public UserDto getUser(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type
  ) {
    log.debug("Get user: [{}, {}]", query, type);
    return userFacade.getUser(apiKey, query, type);
  }



  @PostMapping
  public UserDto createUser(
      @RequestHeader("Authorization") String apiKey,
      @RequestBody CreateUserDto createUserDto
  ) {
    return userFacade.createUser(apiKey, createUserDto);
  }


  @PutMapping("/{query}")
  public UserDto updateUserInfo(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type,
      @RequestBody UserInfoDto userInfoDto
  ) {
    log.debug("Update user info: [{}, {}]", query, userInfoDto);
    return userFacade.updateUserInfo(apiKey, query, type, userInfoDto);
  }

  @DeleteMapping("/{query}")
  public boolean deleteUser(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type
      ) {
    log.debug("Delete user: [{}]", query);
    return userFacade.deleteUser(apiKey, query, type);
  }

  @PostMapping("/{query}/roles")
  public UserDto addUserRole(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type,
      @RequestBody RoleDto roleDto
  ) {
    log.debug("Add role to user: [{}, {}]", query, roleDto);
    return userFacade.addUserRole(apiKey, query, type, roleDto);
  }

  @DeleteMapping("/{query}/roles")
  public UserDto deleteUserRole(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type,
      @RequestBody RoleDto roleDto
  ) {
    log.debug("Delete role from user: [{}, {}]", query, roleDto);
    return userFacade.deleteUserRole(apiKey, query, type, roleDto);
  }

  @GetMapping("/{query}/subscriptions")
  public List<SubscriptionDto> getUserSubscriptions(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type
  ) {
    log.debug("Get user's subscriptions: {}", query);
    return userFacade.getUserSubscriptions(apiKey, query, type);
  }

  @GetMapping("/{query}/pays")
  public List<PayDto> getUserPays(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable String query,
      @Parameter(description = "id, platform_id")
      @RequestParam(name = "type", required = false, defaultValue = "platform_id") String type
  ) {
    log.debug("Get user's pays: {}", query);
    return userFacade.getUserPays(apiKey, query, type);
  }

}
