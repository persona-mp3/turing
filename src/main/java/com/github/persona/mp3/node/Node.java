package com.github.persona.mp3.node;

import com.github.persona.mp3.definitions.EchoRequest;
import com.github.persona.mp3.definitions.GenerateReply;
import com.github.persona.mp3.definitions.EchoReply;
import com.github.persona.mp3.definitions.InitRequest;
import com.github.persona.mp3.definitions.InitReply;
import com.github.persona.mp3.definitions.GenerateRequest;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Node {
	int listenPort;

	public Node(int listenPort) {
		this.listenPort = listenPort;
	}

	public void start() throws Exception {
		ObjectMapper mapper = JsonMapper.builder().disable(JsonParser.Feature.AUTO_CLOSE_SOURCE).build();

		String assignedId = "";
		String line;

		try (
				BufferedReader stdout = new BufferedReader(new InputStreamReader(System.in))) {

			while ((line = stdout.readLine()) != null) {
				JsonNode payload = mapper.readTree(line);

				String src = payload.path("src").asText();
				JsonNode body = payload.get("body");
				String payloadType = body.path("type").asText();

				String jsonReply;
				if (payloadType.equals("echo")) {
					EchoRequest.Body requestBody = mapper.convertValue(body, EchoRequest.Body.class);
					EchoReply.Body replyBody = new EchoReply.Body(requestBody.msgId, requestBody.msgId, requestBody.echo);
					EchoReply reply = new EchoReply(assignedId, src, replyBody);
					jsonReply = mapper.writeValueAsString(reply);

				} else if (payloadType.equals("init")) {
					InitRequest.Body requestBody = mapper.convertValue(body, InitRequest.Body.class);
					assignedId = requestBody.nodeId;

					InitReply.Body replyBody = new InitReply.Body(requestBody.msgId);
					InitReply reply = new InitReply(assignedId, src, replyBody);

					jsonReply = mapper.writeValueAsString(reply);
				} else if (payloadType.equals("generate")) {
					GenerateRequest.Body reqBody = mapper.convertValue(body,
							GenerateRequest.Body.class);

					String uuid = UUID.randomUUID().toString();
					GenerateReply reply = GenerateReply.builder()
							.src(assignedId).dest(src)
							.body(GenerateReply.Body.builder()
									.id(uuid)
									.msgId(reqBody.msgId)
									.inReplyTo(reqBody.msgId).build())
							.build();

					jsonReply = mapper.writeValueAsString(reply);
				} else {
					throw new Exception(
							String.format("Unexpected payload recvd\n %s\n, payloadType: %s\n ", payload.toPrettyString(),
									payloadType));
				}

				System.out.println(jsonReply);
				System.out.flush();

			}

		}
	}

}
