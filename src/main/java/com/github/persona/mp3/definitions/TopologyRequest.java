package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public class TopologyRequest {
  public String src;
  public String dest;
  public Body body;

  public TopologyRequest(String src, String dest, Body body) {
    this.src = src;
    this.dest = dest;
    this.body = body;
  }

  public static class Body {
    public String type = "topology";

    @JsonProperty("msg_id")
    public int msgId;

    public Map<String, List<String>> topology = new HashMap<>();
  }
}
