package com.mark1708.clients.prediction.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

  private String id;

  private String status;

  private List<PredictedItem> predictedItems;
}
