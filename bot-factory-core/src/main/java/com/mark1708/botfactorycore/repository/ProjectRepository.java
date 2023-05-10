package com.mark1708.botfactorycore.repository;

import com.mark1708.botfactorycore.model.entity.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


  @Query(value = "SELECT p.* FROM projects p "
      + "WHERE company_id = :companyId", nativeQuery = true)
  List<Project> getCompanyProjectsById(@Param("companyId") Long companyId);

  Optional<Project> findProjectBySlug(String slug);

  @Query(value = "SELECT p.* FROM projects p "
      + "INNER JOIN projects_users pu ON p.id = pu.project_id "
      + "WHERE pu.user_id = :userId", nativeQuery = true)
  List<Project> findProjectsByUserId(@Param("userId") Long userId);

  @Modifying
  @Query(value = "INSERT INTO projects_users (user_id, project_id) VALUES (:userId, :projectId)",
      nativeQuery = true)
  void addUserToProject(@Param("projectId") Long projectId, @Param("userId") Long userId);

  @Modifying
  @Query(value = "DELETE FROM projects_users pu WHERE pu.user_id = :userId AND pu.project_id = :projectId",
      nativeQuery = true)
  void deleteUserFromProject(@Param("projectId") Long projectId, @Param("userId") Long userId);

  @Query(value = "SELECT p.* FROM projects p "
      + "         INNER JOIN companies c on c.id = p.company_id "
      + "WHERE p.company_id = :companyId "
      + "  AND :userId NOT IN (SELECT pu.user_id "
      + "                FROM projects_users pu "
      + "                WHERE pu.project_id = p.id)", nativeQuery = true)
  List<Project> getCompanyProjectsAvailableForUserAdd(@Param("companyId") Long companyId,
      @Param("userId") Long userId);
}
