package com.mark1708.clients.statistics.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesDateSerializer extends StdSerializer<LocalDateTime> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public TimeSeriesDateSerializer() {
    super((Class<LocalDateTime>) null);
  }
  public TimeSeriesDateSerializer(Class<LocalDateTime> t) {
    super(t);
  }

  public TimeSeriesDateSerializer(JavaType type) {
    super(type);
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(formatter.format(localDateTime));
  }
}
