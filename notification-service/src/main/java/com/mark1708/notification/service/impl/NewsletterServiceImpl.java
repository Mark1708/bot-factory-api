package com.mark1708.notification.service.impl;

import com.mark1708.notification.model.entity.Newsletter;
import com.mark1708.notification.repository.NewsletterRepository;
import com.mark1708.notification.service.NewsletterService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {

  private final NewsletterRepository repository;

  @Override
  public Newsletter save(Newsletter newsletter) {
    return repository.saveAndFlush(newsletter);
  }

  @Override
  public Optional<Newsletter> findNewsletterById(String id) {
    return repository.findById(id);
  }

  @Override
  public void setAsDeleted(Newsletter newsletter) {
    newsletter.setDeleted(true);
    repository.saveAndFlush(newsletter);
  }
}
