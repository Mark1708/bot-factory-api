package com.mark1708.clients.statistics.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesDateDeserializer extends StdDeserializer<LocalDateTime> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  protected TimeSeriesDateDeserializer() {
    super(LocalDateTime.class);
  }

  protected TimeSeriesDateDeserializer(Class<?> vc) {
    super(vc);
  }

  protected TimeSeriesDateDeserializer(JavaType valueType) {
    super(valueType);
  }

  protected TimeSeriesDateDeserializer(StdDeserializer<?> src) {
    super(src);
  }

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException, JacksonException {
    String date = jsonParser.getText();
    LocalDate localDate = LocalDate.parse(date, formatter);
    return localDate.atStartOfDay();
  }
}
