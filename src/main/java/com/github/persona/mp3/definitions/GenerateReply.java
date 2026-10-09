package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class GenerateReply {
    public String src;
    public String dest;
    public Body body;

    public GenerateReply(String src, String dest, Body body) {
        this.src = src;
        this.dest = dest;
        this.body = body;
    }

    @Builder
    @AllArgsConstructor
    public static class Body {
        @Builder.Default public String type = "generate_ok";

        public String id;

        @JsonProperty("msg_id")
        public int msgId;

        @JsonProperty(value = "in_reply_to")
        public int inReplyTo;
    }
}
