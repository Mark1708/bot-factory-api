package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.User;
import java.util.List;

public interface UserService {

  List<User> getUsersByBotId(Long botId);

  User getUserById(Long id);

  List<User> searchUsersByBotIdAndUsername(Long botId, String username);

  List<User> searchUsersByBotIdAndFirstName(Long botId, String firstName);

  List<User> searchUsersByBotIdAndLastName(Long botId, String lastName);

  List<User> searchUsersByBotIdAndState(Long botId, String state);

  User getUserByIdAndBotId(Long id, Long botId);

  User getUserByPlatformIdAndBotId(String platformId, Long botId);

  User saveUser(User user);

  void addRoleToUser(Long id, Long roleId);

  boolean deleteUserById(Long id);

  void deleteRoleFromUser(Long id, Long roleId);

  boolean existByBotIdAndPlatformId(Long botId, String platformId);
}
