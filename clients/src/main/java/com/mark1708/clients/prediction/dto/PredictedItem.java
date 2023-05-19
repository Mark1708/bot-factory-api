package com.mark1708.clients.prediction.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictedItem implements Serializable {

  private LocalDateTime timeStamp;
  private double value;

}
