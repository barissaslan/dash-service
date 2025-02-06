package com.noderland.dash.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JsonFormatter {

  public static String formatJsonString(String response) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(response);

      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    } catch (Exception e) {
      log.error("#JsonFormatter101: {}", e.getMessage(), e);
      return null;
    }
  }
}
