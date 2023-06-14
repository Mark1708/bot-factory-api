package com.mark1708.notification.job;

import com.mark1708.kafka.DeleteNewsletter;
import com.mark1708.kafka.NewsletterMessage;
import com.mark1708.notification.facade.NotificationFacade;
import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import com.mark1708.notification.model.enums.Messenger;
import com.mark1708.notification.service.MessageService;
import com.mark1708.notification.service.NewsletterService;
import com.mark1708.notification.service.SenderService;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramNotificationConsumer implements NotificationConsumer {

  @Qualifier("telegramSenderService")
  private final SenderService senderService;

  private NewsletterService newsletterService;
  private MessageService messageService;

  @Override
  @KafkaListener(topics = "${spring.kafka.topic}", groupId = "telegram")
  public void createNewsletter(NewsletterMessage message) {
    log.info("Received NewsletterMessage in group telegram {}", message);

    Newsletter newsletter = newsletterService.save(
        Newsletter.builder()
            .botId(message.getBotId())
            .messenger(Messenger.TELEGRAM)
            .sendAt(Instant.now())
            .isDeleted(false)
            .build()
    );

    List<Message> messages = senderService.sendNewsletter(newsletter, message);

//    newsletterMessage.getDocuments().forEach(document -> {
//      // Декодирование изображения из Base64
//      byte[] decodedImageBytes = Base64.getDecoder().decode(document.getBase64File());
//
//      String[] fileNameFull = document.getName().split("\\.");
//      // Запись декодированного изображения в файл
//      Path decodedImagePath = Paths.get("/Users/mark/Documents/bot-factory/bot-factory-api/notification-service/src/main/resources/" + fileNameFull[0] + "-new." + fileNameFull[1]);
//      try {
//        if (decodedImagePath.toFile().createNewFile()) {
//          Files.write(decodedImagePath, decodedImageBytes);
//        }
//      } catch (IOException e) {
//        throw new RuntimeException(e);
//      }
//    });
  }

  @Override
  @KafkaListener(topics = "${spring.kafka.topic}", groupId = "telegram")
  public void deleteNewsletter(DeleteNewsletter message) {
    log.info("Received DeleteNewsletter in group telegram {}", message);
   Optional<Newsletter> newsletterOpt = newsletterService.findNewsletterById(message.getNewsletterId());
   if (newsletterOpt.isPresent()) {
     Newsletter newsletter = newsletterOpt.get();
     senderService.deleteNewsletter(newsletter, message);
     newsletterService.setAsDeleted(newsletter);
   }
  }
}
