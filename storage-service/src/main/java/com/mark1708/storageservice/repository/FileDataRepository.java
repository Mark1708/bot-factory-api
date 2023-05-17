package com.mark1708.storageservice.repository;

import com.mark1708.storageservice.model.entity.FileData;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

  Optional<FileData> findByBotIdAndSlug(long botId, String slug);

  List<FileData> findAllByCompanyId(long companyId);

  List<FileData> findAllByCompanyIdAndBotId(long companyId, long botId);
}
