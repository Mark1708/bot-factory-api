package com.mark1708.notification.service;

import com.mark1708.notification.model.entity.Newsletter;
import java.util.Optional;

public interface NewsletterService {

  Newsletter save(Newsletter newsletter);

  Optional<Newsletter> findNewsletterById(String id);

  void setAsDeleted(Newsletter newsletter);
}
