package com.mark1708.storageservice.facade.impl;

import com.mark1708.storageservice.configuration.FileStorageConfiguration;
import com.mark1708.storageservice.converter.FileDataConverter;
import com.mark1708.storageservice.converter.ResourceConverter;
import com.mark1708.storageservice.exception.http.BadRequestException;
import com.mark1708.storageservice.facade.StorageFacade;
import com.mark1708.storageservice.model.dto.FileDataDto;
import com.mark1708.storageservice.model.dto.FileInfoDto;
import com.mark1708.storageservice.model.entity.FileData;
import com.mark1708.storageservice.model.entity.PublicLink;
import com.mark1708.storageservice.service.FileDataService;
import com.mark1708.storageservice.service.PublicLinkService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageFacadeImpl implements StorageFacade {

  private final FileStorageConfiguration fileStorageConfiguration;

  private final FileDataService fileDataService;
  private final PublicLinkService publicLinkService;

  private final FileDataConverter fileDataConverter;
  private final ResourceConverter resourceConverter;

  @Override
  public List<FileDataDto> getFilesByCompanyId(Long companyId) {
    return fileDataConverter.toDto(
        fileDataService.getAllByCompanyId(companyId)
    );
  }

  @Override
  public List<FileDataDto> getFilesByCompanyIdAndBotId(Long companyId, Long botId) {
    return fileDataConverter.toDto(
        fileDataService.getAllByCompanyIdAndBotId(companyId, botId)
    );
  }

  @Override
  public ResponseEntity<Resource> getResourceByToken(String token) {
    PublicLink publicLink = publicLinkService.getByToken(token);
    FileData fileData = fileDataService.getFileDataById(publicLink.getFileId());

    Path filePath = getFilePath(fileData);

    return resourceConverter.toDto(
        filePath
    );
  }

  @Override
  public ResponseEntity<Resource> getResourceByCompanyIdAndBotIdAndSlug(
      Long companyId, Long botId, String slug
  ) {

    FileData fileData = fileDataService.getFileDataByCompanyIdAndBotIdAndSlug(
        companyId, botId, slug
    );
    Path filePath = getFilePath(fileData);

    return resourceConverter.toDto(
        filePath
    );
  }

  @Override
  @SneakyThrows
  public FileDataDto uploadFile(Long companyId, Long botId, MultipartFile file,
      String slug, String description, boolean createPublicLink) {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
    if (!fileStorageConfiguration.getAllowedExtension().contains(extension)) {
      throw new BadRequestException("Extension [" + extension + "] not allowed!");
    }

    Path fileDir = getFileDirPath(companyId, botId);
    String newFileName = slug + "." + extension;
    Path filePath = fileDir.resolve(newFileName);
    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

    FileData fileData = FileData.builder()
        .companyId(companyId)
        .botId(botId)
        .name(fileName)
        .slug(slug)
        .description(description)
        .publicLink(createPublicLink)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .path(String.format("%d/%d/%s", companyId, botId, newFileName))
        .build();

    FileData savedFileData = fileDataService.saveFileData(fileData);
    if (createPublicLink) {
      publicLinkService.savePublicLink(savedFileData.getId());
    }

    return fileDataConverter.toDto(
        savedFileData
    );
  }

  @Override
  public FileDataDto updateFileInfo(Long companyId, Long botId, FileInfoDto fileInfoDto) {
    FileData fileData = fileDataService.getFileDataById(fileInfoDto.getId());

    if (fileData.isPublicLink() != fileInfoDto.isPublicLink()) {
      if (fileInfoDto.isPublicLink()) {
        publicLinkService.savePublicLink(fileData.getId());
      } else {
        publicLinkService.deleteByFileId(fileData.getId());
      }
    }

    BeanUtils.copyProperties(fileInfoDto, fileData, "id");
    return fileDataConverter.toDto(
        fileDataService.saveFileData(fileData)
    );
  }

  @Override
  public boolean deleteFile(Long companyId, Long botId, String slug) {
    FileData fileData = fileDataService.getFileDataByCompanyIdAndBotIdAndSlug(
        companyId, botId, slug);
    publicLinkService.deleteByFileId(fileData.getId());
    return fileDataService.deleteById(fileData.getId());
  }

  private Path getFilePath(FileData fileData) {
    Path storageDir = fileStorageConfiguration.getStorageDir();
    return storageDir.resolve(fileData.getPath()).normalize();
  }

  @SneakyThrows
  private Path getFileDirPath(Long companyId, Long botId) {
    Path storageDir = fileStorageConfiguration.getStorageDir();
    Path companyDir = storageDir.resolve(companyId.toString()).normalize();
    if (!Files.exists(companyDir)) {
      Files.createDirectory(companyDir);
    }

    Path botDir = companyDir.resolve(botId.toString()).normalize();
    if (!Files.exists(botDir)) {
      Files.createDirectory(botDir);
    }

    return botDir;
  }
}
