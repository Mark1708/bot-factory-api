package com.mark1708.botapicore.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

  public LocalDateTimeSerializer() {
    super((Class<LocalDateTime>) null);
  }
  public LocalDateTimeSerializer(Class<LocalDateTime> t) {
    super(t);
  }

  public LocalDateTimeSerializer(JavaType type) {
    super(type);
  }

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(formatter.format(localDateTime));
  }
}
