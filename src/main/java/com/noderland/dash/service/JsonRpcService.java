package com.noderland.dash.service;

import com.noderland.dash.service.model.JsonRpcProcessOutput;
import com.noderland.dash.service.model.JsonRpcServiceInput;

public interface JsonRpcService {

  JsonRpcProcessOutput process(JsonRpcServiceInput input);
}
