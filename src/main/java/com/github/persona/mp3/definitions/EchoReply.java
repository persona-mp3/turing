package com.github.persona.mp3.definitions;

import com.fasterxml.jackson.annotation.*;

public class EchoReply {
	public String src;
	public String dest;
	public Body body;

	public EchoReply(String src, String dest, Body body) {
		this.src = src;
		this.dest = dest;
		this.body = body;
	}

	static public class Body {
		public String type = "echo_ok";

		@JsonProperty(value = "msg_id")
		public int msgId;

		@JsonProperty(value = "in_reply_to")
		public int inReplyTo;

		public String echo;

		public Body(int msgId, int inReplyTo, String echo) {
			this.msgId = msgId;
			this.inReplyTo = inReplyTo;
			this.echo = echo;
		}
	}

}
