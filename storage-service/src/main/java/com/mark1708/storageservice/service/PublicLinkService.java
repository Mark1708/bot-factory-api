package com.mark1708.storageservice.service;

import com.mark1708.storageservice.model.entity.PublicLink;

public interface PublicLinkService {

  PublicLink getByFileId(Long fileId);

  PublicLink getByToken(String token);

  PublicLink savePublicLink(Long fileDataId);

  boolean deleteByFileId(Long fileDataId);
}
