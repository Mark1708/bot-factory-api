package com.mark1708.storageservice.controller;

import com.mark1708.storageservice.facade.StorageFacade;
import com.mark1708.storageservice.model.dto.FileDataDto;
import com.mark1708.storageservice.model.dto.FileInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot")
public class BotStorageController {

  private final StorageFacade storageFacade;

  @GetMapping("/{companyId}/{botId}")
  @Operation(method = "Get files dto")
  public List<FileDataDto> getFiles(@PathVariable Long companyId, @PathVariable Long botId) {
    return storageFacade.getFilesByCompanyIdAndBotId(companyId, botId);
  }

  @GetMapping("/{companyId}/{botId}/{slug:.+}")
  public ResponseEntity<Resource> downloadFile(
      @PathVariable Long companyId,
      @PathVariable Long botId,
      @PathVariable String slug
  ) {
    return storageFacade.getResourceByCompanyIdAndBotIdAndSlug(companyId, botId, slug);
  }

  @PostMapping("/{companyId}/{botId}")
  public FileDataDto uploadFile(
      @PathVariable Long companyId,
      @PathVariable Long botId,
      @RequestParam("file") MultipartFile file,
      @RequestParam("slug") String slug,
      @RequestParam("description") String description,
      @RequestParam("createPublicLink") boolean createPublicLink
  ) {
    return storageFacade.uploadFile(
        companyId, botId, file,
        slug, description, createPublicLink
    );
  }

  @PutMapping("/{companyId}/{botId}")
  public FileDataDto updateFileInfo(
      @PathVariable Long companyId,
      @PathVariable Long botId,
      @RequestBody FileInfoDto fileInfoDto
  ) {
    return storageFacade.updateFileInfo(companyId, botId, fileInfoDto);
  }


  @DeleteMapping("/{companyId}/{botId}/{slug}")
  public boolean deleteFile(
      @PathVariable Long companyId,
      @PathVariable Long botId,
      @PathVariable String slug
  ) {
    return storageFacade.deleteFile(companyId, botId, slug);
  }
}
