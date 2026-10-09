package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;

public class InitRequest {
  public String src;
  public String dest;
  public Body body;

  public InitRequest(String src, String dest, Body body) {
    this.src = src;
    this.dest = dest;
    this.body = body;
  }

  public static class Body {
    public String type = "init";

    @JsonProperty("msg_id")
    public int msgId;

    @JsonProperty("node_id")
    public String nodeId;

    @JsonProperty("node_ids")
    public String[] nodeIds;
  }
}
