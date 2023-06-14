package com.mark1708.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void publish(Object payload, String topic, String key) {
    log.info("Publishing to {} using key {}. Payload: {}", topic, key, payload);
    kafkaTemplate.send(topic, key, payload);
    log.info("Published to {} using key {}. Payload: {}", topic, key, payload);
  }

  public void publish(Object payload, String topic) {
    log.info("Publishing to {}. Payload: {}", topic, payload);
    kafkaTemplate.send(topic, payload);
    log.info("Published to {}. Payload: {}", topic, payload);
  }

}
