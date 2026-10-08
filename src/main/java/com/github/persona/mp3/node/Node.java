package com.github.persona.mp3.node;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.InputStream;

public class Node {
	int listenPort;
	String id;

	public Node(int listenPort) {
		this.listenPort = listenPort;
	}

	public void start() throws IOException {
		try (
				ServerSocket ss = new ServerSocket(this.listenPort)) {

			while (true) {
				Socket conn = ss.accept();
				conn.setSoTimeout(10_000); // 10 seconds
				System.out.printf("accepted new connection from %s\n", conn.getInetAddress());

				Thread vt = Thread.ofVirtual().start(() -> {
					handleConn(conn);
				});

				vt.run();
			}
		} catch (SocketException err) {
			System.out.println("error occured for server");
			err.printStackTrace();
		}
	}

	static void handleConn(Socket conn) {
		String addr = conn.getLocalAddress().toString();
		try (
				InputStream stream = conn.getInputStream();) {
			while (!conn.isClosed() && conn.isConnected()) {
				// blocking
				stream.read();
			}

		} catch (SocketTimeoutException err) {
			System.out.println("timeout reached from " + addr);
		} catch (Exception err) {
			System.out.println("error occured while handling connection");
			err.printStackTrace();
			return;

		}
	}
}
