package com.noderland.dash.mapper;

import static com.noderland.commons.constants.Constants.*;

import com.noderland.commons.model.CallNodeMethodInput;
import com.noderland.dash.service.model.JsonRpcServiceInput;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonRpcProcessMapper {

  public static JsonRpcServiceInput toInput(Map<String, Object> request) {
    return JsonRpcServiceInput.builder().payload(request).build();
  }

  public static CallNodeMethodInput toCallNodeMethodInput(JsonRpcServiceInput input) {
    var id = input.getPayload().get(JSONRPC_ID_KEY).toString();
    var method = input.getPayload().get(JSONRPC_METHOD_KEY).toString();
    var params = parseParams(input.getPayload().get(JSONRPC_PARAMS_KEY));

    return CallNodeMethodInput.builder().id(id).methodName(method).parameters(params).build();
  }

  private static Object[] parseParams(Object value) {
    if (value instanceof List<?> paramsList) {
      return paramsList.toArray(new Object[0]);
    } else if (value instanceof Object[] parameters) {
      return parameters;
    }

    return new Object[0];
  }
}
