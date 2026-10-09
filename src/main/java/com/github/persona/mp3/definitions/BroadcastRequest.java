package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BroadcastRequest {
  public String src;
  public String dest;
  public Body body;

  public BroadcastRequest(String src, String dest, Body body) {
    this.src = src;
    this.dest = dest;
    this.body = body;
  }

  public static class Body {
    public String type = "broadcast";

    @JsonProperty("msg_id")
    public int msgId;

    public int message;
  }
}
