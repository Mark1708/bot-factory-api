package com.mark1708.botfactorycore.repository;

import com.mark1708.botfactorycore.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u "
      + "WHERE u.username LIKE ?1 OR u.name LIKE ?1 "
      + "OR u.email LIKE ?1 OR u.surname LIKE ?1 OR u.surname LIKE ?1")
  Page<User> searchUsers(String search, Pageable pageable);

  @Query(value = "SELECT u.*, company_id FROM users u "
      + "INNER JOIN companies_users cu on u.id = cu.user_id "
      + "WHERE cu.company_id = :companyId", nativeQuery = true)
  List<User> getUsersByCompanyId(@Param("companyId") Long companyId);

  @Query(value = "SELECT u.*, company_id FROM users u "
      + "INNER JOIN projects_users pu on u.id = pu.user_id "
      + "INNER JOIN projects p on p.id = pu.project_id "
      + "WHERE pu.project_id = :projectId", nativeQuery = true)
  List<User> getUsersByProjectId(@Param("projectId") Long projectId);

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  @Modifying
  @Query(value = "INSERT INTO users_roles (user_id, role_id) VALUES (:userId, :roleId)",
      nativeQuery = true)
  void addRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

  @Modifying
  @Query(value = "DELETE FROM users_roles ur WHERE ur.user_id = :userId AND ur.role_id = :roleId",
      nativeQuery = true)
  void deleteRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
