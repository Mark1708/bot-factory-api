package com.mark1708.botfactorycore.admin;

import com.mark1708.botfactorycore.facade.UserFacade;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

  private final UserFacade userFacade;

  @GetMapping
  public List<UserSmallDto> getAllUsers() {
    log.debug("Get all users");
    return userFacade.getAllUsers();
  }
}
