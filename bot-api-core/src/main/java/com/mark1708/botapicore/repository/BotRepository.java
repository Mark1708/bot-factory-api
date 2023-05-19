package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Bot;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot, Long> {

  Optional<Bot> findBotByApiKey(String apiKey);
}
