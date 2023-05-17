package com.mark1708.clients.statistics.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeSeriesDateISOSerializer extends StdSerializer<LocalDateTime> {


  public TimeSeriesDateISOSerializer() {
    super((Class<LocalDateTime>) null);
  }
  public TimeSeriesDateISOSerializer(Class<LocalDateTime> t) {
    super(t);
  }

  public TimeSeriesDateISOSerializer(JavaType type) {
    super(type);
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
  }
}
