package com.mark1708.clients.statistics;

import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "statistics-service", path = "/api/v1/factory")
public interface StatisticsClient {

  @GetMapping("/{botId}/users")
  List<TimeSeriesItemDto> getUsersStatistics(
      @PathVariable(name = "botId") Long botId,
      @RequestParam(name = "query") String query
  );
}
