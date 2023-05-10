package com.mark1708.botfactorycore.repository;

import com.mark1708.botfactorycore.model.entity.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  Optional<Company> findCompanyBySlug(String slug);

  @Query(value = "SELECT c.* FROM companies c "
      + "INNER JOIN companies_users cu on c.id = cu.company_id "
      + "WHERE cu.user_id = :userId", nativeQuery = true)
  Company findCompanyByUserId(@Param("userId") Long userId);


  @Query(value = "SELECT c.* FROM companies c "
      + "INNER JOIN projects p on c.id = p.company_id "
      + "WHERE p.id = :projectId", nativeQuery = true)
  Company findCompanyByProjectId(@Param("projectId") Long projectId);


  @Modifying
  @Query(value = "INSERT INTO companies_users (user_id, company_id) VALUES (:userId, :companyId)",
      nativeQuery = true)
  void addUserToCompany(@Param("companyId") Long companyId, @Param("userId") Long userId);


  @Modifying
  @Query(value = "DELETE FROM companies_users cu WHERE cu.user_id = :userId AND cu.company_id = :companyId",
      nativeQuery = true)
  void deleteUserFromCompany(@Param("companyId") Long companyId, @Param("userId") Long userId);
}
