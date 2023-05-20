package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.model.user.CreateUserDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.botapicore.model.user.UserInfoDto;
import java.util.List;

public interface UserFacade {

  List<UserDto> getUsers(String apiKey);

  List<UserDto> searchUsers(String apiKey, String query, String type);

  UserDto getUser(String apiKey, String query, String type);

  UserDto createUser(String apiKey, CreateUserDto createUserDto);

  UserDto updateUserInfo(String apiKey, String query, String type, UserInfoDto userInfoDto);

  boolean deleteUser(String apiKey, String query, String type);

  UserDto addUserRole(String apiKey, String query, String type, RoleDto roleDto);

  UserDto deleteUserRole(String apiKey, String query, String type, RoleDto roleDto);

  List<PayDto> getUserPays(String apiKey, String query, String type);

  List<SubscriptionDto> getUserSubscriptions(String apiKey, String query, String type);
}
