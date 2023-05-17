package com.mark1708.clients.statistics.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.clients.statistics.utils.TimeSeriesDateISODeserializer;
import com.mark1708.clients.statistics.utils.TimeSeriesDateISOSerializer;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeSeriesItemDto {


  @JsonDeserialize(using = TimeSeriesDateISODeserializer.class)
  @JsonSerialize(using = TimeSeriesDateISOSerializer.class)
  private LocalDateTime name;

  private TimeSeriesItemValueDto value;

  public TimeSeriesItemDto(LocalDateTime dateTime, Long value) {
    this.name = dateTime;
    this.value = new TimeSeriesItemValueDto(dateTime, value);
  }
}
