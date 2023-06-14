package com.mark1708.notification.job;

import com.mark1708.kafka.DeleteNewsletter;
import com.mark1708.kafka.NewsletterMessage;

public interface NotificationConsumer {

  void createNewsletter(NewsletterMessage message);

  void deleteNewsletter(DeleteNewsletter message);

}
