package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

  List<Service> findAllByBotId(long id);
  List<Service> findAllByType(long type);
}
