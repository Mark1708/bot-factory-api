package com.mark1708.storageservice.service;

import com.mark1708.storageservice.model.entity.FileData;
import java.util.List;

public interface FileDataService {

  List<FileData> getAllByCompanyId(Long companyId);

  List<FileData> getAllByCompanyIdAndBotId(Long companyId, Long botId);

  FileData getFileDataById(Long fileId);

  FileData getFileDataByCompanyIdAndBotIdAndSlug(Long companyId, Long botId, String slug);

  FileData saveFileData(FileData fileData);

  boolean deleteById(Long id);
}
