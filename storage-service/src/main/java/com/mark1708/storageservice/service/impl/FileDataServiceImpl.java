package com.mark1708.storageservice.service.impl;

import com.mark1708.storageservice.exception.http.QueryType;
import com.mark1708.storageservice.exception.http.ResourceNotFoundException;
import com.mark1708.storageservice.exception.http.ResourceType;
import com.mark1708.storageservice.model.entity.FileData;
import com.mark1708.storageservice.repository.FileDataRepository;
import com.mark1708.storageservice.service.FileDataService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDataServiceImpl implements FileDataService {

  private final FileDataRepository repository;

  public Optional<FileData> findFileDataByBotIdAndSlug(long botId, String slug) {
    return repository.findByBotIdAndSlug(botId, slug);
  }

  public Optional<FileData> findFileDataById(long id) {
    return repository.findById(id);
  }

  @Override
  public List<FileData> getAllByCompanyId(Long companyId) {
    return repository.findAllByCompanyId(companyId);
  }

  @Override
  public List<FileData> getAllByCompanyIdAndBotId(Long companyId, Long botId) {
    return repository.findAllByCompanyIdAndBotId(companyId, botId);
  }

  @Override
  public FileData getFileDataById(Long fileId) {
    return findFileDataById(fileId)
        .orElseThrow(() -> new ResourceNotFoundException(
            ResourceType.FILE_DATA, QueryType.ID, fileId
        ));
  }

  @Override
  public FileData getFileDataByCompanyIdAndBotIdAndSlug(Long companyId, Long botId, String slug) {
    return findFileDataByBotIdAndSlug(botId, slug)
        .orElseThrow(() -> new ResourceNotFoundException(
            ResourceType.FILE_DATA, QueryType.BOT_ID_AND_SLUG, botId + "/" + slug
        ));
  }

  @Override
  public FileData saveFileData(FileData fileData) {
    return repository.saveAndFlush(fileData);
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(
          ResourceType.PUBLIC_LINK, QueryType.ID, id
      );
    }
  }
}
