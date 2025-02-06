package com.noderland.dash.service.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class JsonRpcProcessOutput {

  private ResponseEntity<Map<String, Object>> response;
}
