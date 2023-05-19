package com.mark1708.clients.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.clients.statistics.utils.TimeSeriesDateDeserializer;
import com.mark1708.clients.statistics.utils.TimeSeriesDateSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class TimeSeriesItemValueDto {


  @JsonDeserialize(using = TimeSeriesDateDeserializer.class)
  @JsonSerialize(using = TimeSeriesDateSerializer.class)
  private LocalDateTime date;
  private Long value;
}
