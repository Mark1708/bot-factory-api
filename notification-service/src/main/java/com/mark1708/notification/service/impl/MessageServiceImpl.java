package com.mark1708.notification.service.impl;

import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import com.mark1708.notification.repository.MessageRepository;
import com.mark1708.notification.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository repository;

  @Override
  public List<Message> saveAll(List<Message> messages) {
    return repository.saveAllAndFlush(messages);
  }

  @Override
  public List<Message> findByNewsletter(Newsletter newsletter) {
    return repository.findAllByNewsletter(newsletter);
  }

  @Override
  public void deleteAll(List<Message> messages) {
    repository.deleteAll(messages);
  }
}
