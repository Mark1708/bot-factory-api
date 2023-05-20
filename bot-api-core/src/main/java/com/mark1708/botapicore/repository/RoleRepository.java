package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByBotIdAndName(long botId, String name);
}
