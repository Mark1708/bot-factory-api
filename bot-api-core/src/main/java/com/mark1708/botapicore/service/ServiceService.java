package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.SubService;
import java.util.List;
import java.util.Optional;

public interface ServiceService {

  List<SubService> getServicesByBotId(Long botId);

  List<SubService> searchServicesByBotIdAndName(Long botId, String name);

  List<SubService> searchServicesByBotIdAndType(Long botId, int type);

  SubService getServiceByIdAndBotId(Long id, Long botId);

  Optional<SubService> findServiceByNameAndBotId(String name, Long botId);

  SubService saveService(SubService service);

  boolean deleteServiceByIdAndBotId(Long id, Long botId);
}
