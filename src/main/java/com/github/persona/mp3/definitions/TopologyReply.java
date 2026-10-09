package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class TopologyReply {
  public String src;
  public String dest;
  public Body body;

  public TopologyReply(String src, String dest, Body body) {
    this.src = src;
    this.dest = dest;
    this.body = body;
  }

  @Builder
  @AllArgsConstructor
  public static class Body {
    @Builder.Default public String type = "topology_ok";

    @JsonProperty(value = "msg_id")
    public int msgId;

    @JsonProperty(value = "in_reply_to")
    public int inReplyTo;
  }
}
