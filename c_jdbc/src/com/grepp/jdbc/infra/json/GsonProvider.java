package com.grepp.jdbc.infra.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GsonProvider {
    
    private static final Gson gson;
    
    static {
        gson = new GsonBuilder()
                   .registerTypeAdapter(LocalDateTime.class, new DateTimeConverter())
                   .registerTypeAdapter(LocalDate.class, new DateConverter())
                   .setPrettyPrinting().create();
    }
    
    public static Gson get() {
        return gson;
    }
    
    private static class DateTimeConverter implements JsonSerializer<LocalDateTime>,
                                                          JsonDeserializer<LocalDateTime> {
        
        @Override
        public JsonElement serialize(LocalDateTime dateTime, Type type,
            JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(dateTime.toString());
        }
        
        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(jsonElement.getAsString());
        }
    }
    
    private static class DateConverter implements JsonSerializer<LocalDate>,
                                                      JsonDeserializer<LocalDate> {
        
        @Override
        public JsonElement serialize(LocalDate date, Type type,
            JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(date.toString());
        }
        
        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsString());
        }
    }
}
