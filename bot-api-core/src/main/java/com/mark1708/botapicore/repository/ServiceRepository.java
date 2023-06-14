package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.SubService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<SubService, Long> {

  List<SubService> findAllByBotId(long id);
  List<SubService> findAllByBotIdAndNameLike(long botId, String name);
  List<SubService> findAllByBotIdAndType(long botId, int type);
  SubService findByIdAndBotId(long id, long botId);
  Optional<SubService> findByBotIdAndName(long botId, String name);

}
