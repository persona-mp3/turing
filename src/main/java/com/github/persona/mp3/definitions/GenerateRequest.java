package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateRequest {
	public String src;
	public String dest;
	public Body body;

	public GenerateRequest(String src, String dest, Body body) {
		this.src = src;
		this.dest = dest;
		this.body = body;
	}

	static public class Body {
		public String type = "generate";

		@JsonProperty("msg_id")
		public int msgId;

	}

}
