package com.noderland.dash.common.constant;

import java.util.List;

public class NodeWhiteList {
  public static final List<String> NODE_METHOD_WHITELIST =
      List.of(
          "getbestblockhash",
          "getblock",
          "getblockchaininfo",
          "getblockcount",
          "getblockhash",
          "getblockheader",
          "getblockstats",
          "getchaintips",
          "getchaintxstats",
          "getdifficulty",
          "getmempoolancestors",
          "getmempooldescendants",
          "getmempoolentry",
          "getmempoolinfo",
          "getrawmempool",
          "gettxout",
          "gettxoutproof",
          "preciousblock");
}
