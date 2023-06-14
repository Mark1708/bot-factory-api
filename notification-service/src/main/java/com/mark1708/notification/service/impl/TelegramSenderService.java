package com.mark1708.notification.service.impl;

import com.mark1708.kafka.DeleteNewsletter;
import com.mark1708.kafka.NewsletterMessage;
import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import com.mark1708.notification.service.MessageService;
import com.mark1708.notification.service.SenderService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("telegramSenderService")
@RequiredArgsConstructor
public class TelegramSenderService implements SenderService {

  private final MessageService messageService;

  @Override
  public List<Message> sendNewsletter(Newsletter newsletter, NewsletterMessage newsletterMessage) {
    List<Message> messages = newsletterMessage.getChatIds().stream()
        .map(chatId -> {
          // TODO: send message request
          return Message.builder()
              .chatId(chatId)
              .platformId("23542526")
              .newsletter(newsletter)
              .build();
        }).collect(Collectors.toList());

    messageService.saveAll(messages);
    return null;
  }

  @Override
  public void deleteNewsletter(Newsletter newsletter, DeleteNewsletter deleteNewsletter) {
    List<Message> messages = messageService.findByNewsletter(newsletter);
    messages.forEach(message -> {
          // TODO: send delete request
        });

    messageService.deleteAll(messages);
  }
}
