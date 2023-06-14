package com.mark1708.tracker.service;

import com.mark1708.tracker.model.entities.clicker.ClickerInfo;
import java.util.Optional;

public interface ClickerInfoService {

  Optional<ClickerInfo> findBySlugAndBotId(String slug, Long botId);

  ClickerInfo save(ClickerInfo clickerInfo);
}
