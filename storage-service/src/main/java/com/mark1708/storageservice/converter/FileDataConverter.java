package com.mark1708.storageservice.converter;

import com.mark1708.storageservice.model.dto.FileDataDto;
import com.mark1708.storageservice.model.entity.FileData;
import com.mark1708.storageservice.model.entity.PublicLink;
import com.mark1708.storageservice.service.PublicLinkService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileDataConverter {

  @Value("${app.service-url}")
  private String serviceUrl;

  private final PublicLinkService publicLinkService;

  public FileDataDto toDto(FileData fileData) {
    FileDataDto result = new FileDataDto();
    BeanUtils.copyProperties(fileData, result);

    if (fileData.isPublicLink()) {
      PublicLink publicLink = publicLinkService.getByFileId(fileData.getId());
      result.setPublicLink(serviceUrl + "/api/v1/public/" + publicLink);
    }

    return result;
  }

  public List<FileDataDto> toDto(Collection<FileData> fileDataList) {
    return fileDataList
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
