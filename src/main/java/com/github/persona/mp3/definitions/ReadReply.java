package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class ReadReply {
  public String src;
  public String dest;
  public Body body;

  public ReadReply(String src, String dest, Body body) {
    this.src = src;
    this.dest = dest;
    this.body = body;
  }

  @Builder
  @AllArgsConstructor
  public static class Body {
    @Builder.Default public String type = "read_ok";

    @JsonProperty(value = "msg_id")
    public int msgId;

    @JsonProperty(value = "in_reply_to")
    public int inReplyTo;

    // all the messages the node has read, the order doesn't matter
    public List<Integer> messages;
  }
}
