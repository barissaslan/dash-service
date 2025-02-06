package com.noderland.dash.service.impl;

import static com.noderland.commons.constants.Constants.*;

import com.noderland.commons.enums.JsonRpcError;
import com.noderland.commons.error.JsonRpcException;
import com.noderland.commons.service.NodeService;
import com.noderland.dash.common.constant.NodeWhiteList;
import com.noderland.dash.mapper.JsonRpcProcessMapper;
import com.noderland.dash.service.JsonRpcService;
import com.noderland.dash.service.model.JsonRpcProcessOutput;
import com.noderland.dash.service.model.JsonRpcServiceInput;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonRpcServiceImpl implements JsonRpcService {

  private final NodeService nodeService;

  @Override
  public JsonRpcProcessOutput process(JsonRpcServiceInput input) {
    validate(input);

    var callNodeMethodInput = JsonRpcProcessMapper.toCallNodeMethodInput(input);
    var output = nodeService.callNodeMethod(callNodeMethodInput);

    return JsonRpcProcessOutput.builder()
        .response(ResponseEntity.status(output.getStatusCode()).body(output.getResult()))
        .build();
  }

  private void validate(JsonRpcServiceInput input) {
    var payload = input.getPayload();

    if (!payload.containsKey(JSONRPC_ID_KEY) || !payload.containsKey(JSONRPC_METHOD_KEY)) {
      throw new JsonRpcException(JsonRpcError.INVALID_REQUEST);
    }

    var id = payload.get(JSONRPC_ID_KEY).toString();
    var params = payload.get(JSONRPC_PARAMS_KEY);

    if (!NodeWhiteList.NODE_METHOD_WHITELIST.contains(payload.get(JSONRPC_METHOD_KEY).toString())) {
      throw new JsonRpcException(JsonRpcError.METHOD_NOT_FOUND, id);
    }

    if (!isParameterValid(params)) {
      throw new JsonRpcException(JsonRpcError.INVALID_PARAMS, id);
    }
  }

  private boolean isParameterValid(Object value) {
    return value == null || value instanceof List<?> || value instanceof Object[];
  }
}
