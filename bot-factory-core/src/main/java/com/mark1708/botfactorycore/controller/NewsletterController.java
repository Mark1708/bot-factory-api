package com.mark1708.botfactorycore.controller;

import com.mark1708.botfactorycore.model.entity.Project;
import com.mark1708.botfactorycore.model.newsletter.NewsletterDto;
import com.mark1708.botfactorycore.service.ProjectService;
import com.mark1708.kafka.DeleteNewsletter;
import com.mark1708.kafka.Document;
import com.mark1708.kafka.KafkaMessageProducer;
import com.mark1708.kafka.NewsletterMessage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/newsletter/{projectId}")
public class NewsletterController {

  @Value(value = "${spring.kafka.topic}")
  private String topic;

  private final KafkaMessageProducer producer;
  private final ProjectService projectService;

  @PostMapping( consumes = { "multipart/form-data" })
  public boolean sendNewsletter(
      @PathVariable Long projectId,
      @RequestPart("newsletter") NewsletterDto newsletterDto,
      @RequestPart("files") List<MultipartFile> files
  ) {
    List<Document> documents = files.stream().flatMap(multipartFile -> {
      try {
        String base64File = Base64.getEncoder().encodeToString(multipartFile.getBytes());
        return Stream.of(new Document(multipartFile.getName(), base64File));
      } catch (IOException e) {
        return Stream.empty();
      }
    }).collect(Collectors.toList());

    if (documents.size() != files.size()) {
      return false;
    }

    Project project = projectService.getProjectById(projectId);

    NewsletterMessage newsletterMessage = NewsletterMessage.builder()
        .apiKey(project.getApiKey())
        .botId(project.getBotId())
        .chatIds(newsletterDto.getChatIds())
        .text(newsletterDto.getText())
        .documents(documents)
        .build();

    producer.publish(newsletterMessage, topic);
    return true;
  }

  @DeleteMapping("/{newsletterId}")
  public boolean deleteNewsletter(
      @PathVariable Long projectId,
      @PathVariable String newsletterId
  ) {
    Project project = projectService.getProjectById(projectId);
    DeleteNewsletter deleteNewsletter = DeleteNewsletter.builder()
        .apiKey(project.getApiKey())
        .botId(project.getBotId())
        .newsletterId(newsletterId)
        .build();

    producer.publish(deleteNewsletter, topic);
    return true;
  }
}
