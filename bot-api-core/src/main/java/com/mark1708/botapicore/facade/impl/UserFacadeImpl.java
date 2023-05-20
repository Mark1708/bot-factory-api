package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.PayConverter;
import com.mark1708.botapicore.converter.ServiceConverter;
import com.mark1708.botapicore.converter.SubscriptionConverter;
import com.mark1708.botapicore.converter.UserConverter;
import com.mark1708.botapicore.exception.http.BadRequestException;
import com.mark1708.botapicore.facade.UserFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.entity.Role;
import com.mark1708.botapicore.model.entity.User;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.user.CreateUserDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.botapicore.model.user.UserInfoDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.PayService;
import com.mark1708.botapicore.service.RoleService;
import com.mark1708.botapicore.service.ServiceService;
import com.mark1708.botapicore.service.SubscriptionService;
import com.mark1708.botapicore.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

  private final BotService botService;
  private final UserService userService;
  private final RoleService roleService;
  private final PayService payService;
  private final SubscriptionService subscriptionService;

  private final UserConverter userConverter;
  private final PayConverter payConverter;
  private final SubscriptionConverter subscriptionConverter;

  @Override
  public List<UserDto> getUsers(String apiKey) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return userConverter.toDto(
        userService.getUsersByBotId(bot.getId())
    );
  }

  @Override
  public List<UserDto> searchUsers(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    switch (type) {
      case "username":
        return userConverter.toDto(
            userService.searchUsersByBotIdAndUsername(bot.getId(), query)
        );
      case "firstName":
        return userConverter.toDto(
            userService.searchUsersByBotIdAndFirstName(bot.getId(), query)
        );
      case "lastName":
        return userConverter.toDto(
            userService.searchUsersByBotIdAndLastName(bot.getId(), query)
        );
      case "state":
        return userConverter.toDto(
            userService.searchUsersByBotIdAndState(bot.getId(), query)
        );
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }

  @Override
  public UserDto getUser(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return userConverter.toDto(getUserByQuery(query, type, bot));
  }

  @Override
  public UserDto createUser(String apiKey, CreateUserDto createUserDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    if (userService.existByBotIdAndPlatformId(bot.getId(), createUserDto.getPlatformId())) {
      throw new BadRequestException(
          String.format("User with platformId - %s, already exist!", createUserDto.getPlatformId())
      );
    }

    User user = new User();
    BeanUtils.copyProperties(createUserDto, user, "roles");
    user.setBlocked(false);
    user.setRegisteredAt(LocalDateTime.now());
    user.setLastActivityAt(LocalDateTime.now());
    user.setBot(bot);

    User newUser = userService.saveUser(user);
    createUserDto.getRoles().forEach(roleName -> {
      Role role = roleService.getRoleByName(bot.getId(), roleName);
      userService.addRoleToUser(newUser.getId(), role.getId());
    });

    return userConverter.toDto(newUser);
  }

  @Override
  public UserDto updateUserInfo(String apiKey, String query, String type, UserInfoDto userInfoDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    BeanUtils.copyProperties(userInfoDto, user);
    return userConverter.toDto(
        userService.saveUser(user)
    );
  }

  @Override
  public boolean deleteUser(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    return userService.deleteUserById(user.getId());
  }

  @Override
  public UserDto addUserRole(String apiKey, String query, String type, RoleDto roleDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    Role role = roleService.getRoleByName(bot.getId(), roleDto.getName());
    userService.addRoleToUser(user.getId(), role.getId());
    return userConverter.toDto(user);
  }

  @Override
  public UserDto deleteUserRole(String apiKey, String query, String type, RoleDto roleDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    Role role = roleService.getRoleByName(bot.getId(), roleDto.getName());
    userService.deleteRoleFromUser(user.getId(), role.getId());
    return userConverter.toDto(user);
  }

  @Override
  public List<PayDto> getUserPays(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    return payConverter.toDto(
        payService.getPaysByBotIdAndUserId(bot.getId(), user.getId())
    );
  }

  @Override
  public List<SubscriptionDto> getUserSubscriptions(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    User user = getUserByQuery(query, type, bot);
    return subscriptionConverter.toDto(
        subscriptionService.getSubscriptionsByBotIdAndUserId(bot.getId(), user.getId())
    );
  }

  private User getUserByQuery(String query, String type, Bot bot) {
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }
        return userService.getUserByIdAndBotId(Long.valueOf(query), bot.getId());

      case "platform_id":
        return userService.getUserByPlatformIdAndBotId(query, bot.getId());
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }
}
