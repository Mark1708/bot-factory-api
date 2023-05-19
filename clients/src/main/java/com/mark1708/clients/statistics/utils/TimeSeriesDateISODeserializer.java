package com.mark1708.clients.statistics.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesDateISODeserializer extends StdDeserializer<LocalDateTime> {

  public TimeSeriesDateISODeserializer() {
    super(LocalDateTime.class);
  }

  public TimeSeriesDateISODeserializer(Class<?> vc) {
    super(vc);
  }

  public TimeSeriesDateISODeserializer(JavaType valueType) {
    super(valueType);
  }

  public TimeSeriesDateISODeserializer(StdDeserializer<?> src) {
    super(src);
  }

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException, JacksonException {
    String date = jsonParser.getText();
    return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
  }
}
