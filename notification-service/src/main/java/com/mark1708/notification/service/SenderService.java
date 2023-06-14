package com.mark1708.notification.service;

import com.mark1708.kafka.DeleteNewsletter;
import com.mark1708.kafka.NewsletterMessage;
import com.mark1708.notification.model.entity.Message;
import com.mark1708.notification.model.entity.Newsletter;
import java.util.List;

public interface SenderService {


  List<Message> sendNewsletter(Newsletter newsletter, NewsletterMessage newsletterMessage);

  void deleteNewsletter(Newsletter newsletter, DeleteNewsletter deleteNewsletter);
}
