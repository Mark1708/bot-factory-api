package com.mark1708.prediction;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictedItem {

  private LocalDateTime timeStamp;
  private double value;

}
