package com.mark1708.storageservice.facade;

import com.mark1708.storageservice.model.dto.FileDataDto;
import com.mark1708.storageservice.model.dto.FileInfoDto;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StorageFacade {

  List<FileDataDto> getFilesByCompanyId(Long companyId);

  List<FileDataDto> getFilesByCompanyIdAndBotId(Long companyId, Long botId);

  ResponseEntity<Resource> getResourceByToken(String token);

  ResponseEntity<Resource> getResourceByCompanyIdAndBotIdAndSlug(
      Long companyId, Long botId, String slug
  );

  FileDataDto uploadFile(
      Long companyId, Long botId, MultipartFile file,
      String slug, String description, boolean createPublicLink
  );

  FileDataDto updateFileInfo(Long companyId, Long botId, FileInfoDto fileInfoDto);

  boolean deleteFile(Long companyId, Long botId, String slug);
}
