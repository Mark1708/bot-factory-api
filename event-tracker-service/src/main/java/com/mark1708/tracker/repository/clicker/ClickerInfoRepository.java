package com.mark1708.tracker.repository.clicker;

import com.mark1708.tracker.model.entities.clicker.ClickerInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickerInfoRepository extends JpaRepository<ClickerInfo, Long> {

  Optional<ClickerInfo> findBySlugAndBotId(String slug, Long botId);
}
