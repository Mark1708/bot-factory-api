package com.mark1708.botfactorycore.repository;

import com.mark1708.botfactorycore.model.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findRoleByName(String name);
}
