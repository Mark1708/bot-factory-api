package com.mark1708.notification.service;

import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import java.util.List;

public interface MessageService {

  List<Message> saveAll(List<Message> messages);

  List<Message> findByNewsletter(Newsletter newsletter);

  void deleteAll(List<Message> messages);
}
