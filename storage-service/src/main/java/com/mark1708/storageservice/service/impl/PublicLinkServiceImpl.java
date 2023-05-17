package com.mark1708.storageservice.service.impl;

import com.mark1708.storageservice.exception.http.QueryType;
import com.mark1708.storageservice.exception.http.ResourceNotFoundException;
import com.mark1708.storageservice.exception.http.ResourceType;
import com.mark1708.storageservice.model.entity.PublicLink;
import com.mark1708.storageservice.repository.PublicLinkRepository;
import com.mark1708.storageservice.service.PublicLinkService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicLinkServiceImpl implements PublicLinkService {

  private final PublicLinkRepository repository;

  @Override
  public PublicLink getByFileId(Long fileId) {
    return repository.findByFileId(fileId)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                ResourceType.PUBLIC_LINK, QueryType.FILE_DATA_ID, fileId
            ));
  }

  @Override
  public PublicLink getByToken(String token) {
    return repository.findByToken(token)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                ResourceType.PUBLIC_LINK, QueryType.TOKEN, token
            ));
  }

  @Override
  public PublicLink savePublicLink(Long fileDataId) {
    PublicLink publicLink = PublicLink.builder()
        .fileId(fileDataId)
        .token(UUID.randomUUID().toString())
        .build();
    return repository.saveAndFlush(publicLink);
  }

  @Override
  public boolean deleteByFileId(Long fileDataId) {
    try {
      repository.deleteByFileId(fileDataId);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(
          ResourceType.PUBLIC_LINK, QueryType.FILE_DATA_ID, fileDataId
      );
    }
  }
}
