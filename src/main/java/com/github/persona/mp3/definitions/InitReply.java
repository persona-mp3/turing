package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;

public class InitReply {
	public String src;
	public String dest;
	public Body body;

	public InitReply(String src, String dest, Body body) {
		this.src = src;
		this.dest = dest;
		this.body = body;
	}

	static public class Body {
		public String type = "init_ok";

		@JsonProperty("in_reply_to")
		public int inReplyTo;

		public Body(int inReplyTo) {
			this.inReplyTo = inReplyTo;
		}

	}

}
