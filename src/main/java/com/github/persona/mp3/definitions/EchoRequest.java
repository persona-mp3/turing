package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;

public class EchoRequest {
	public String src;
	public String dest;
	public Body body;

	public EchoRequest(String src, String dest, Body body) {
		this.src = src;
		this.dest = dest;
		this.body = body;
	}

	static public class Body {
		public String type = "echo";
		@JsonProperty("msg_id")
		public int msgId;
		public String echo;
	}
}
