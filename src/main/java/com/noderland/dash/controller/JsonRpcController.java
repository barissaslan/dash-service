package com.noderland.dash.controller;

import java.util.Map;

import com.noderland.dash.mapper.JsonRpcProcessMapper;
import com.noderland.dash.service.JsonRpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class JsonRpcController {

  private final JsonRpcService service;

  @PostMapping
  public ResponseEntity<Map<String, Object>> process(@RequestBody Map<String, Object> request) {
    var input = JsonRpcProcessMapper.toInput(request);
    var output = service.process(input);
    return output.getResponse();
  }
}
