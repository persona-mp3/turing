package com.github.persona.mp3;

import com.github.persona.mp3.node.Node;

// Entry point to application
public class Main {
	public static void main(String[] args) {
		System.out.println("turing application running");

		Node node = new Node(3000);
		try {
			node.start();
		} catch (Exception err) {
			System.err.printf("error occured while running node %s\n", err);
		}
	}
}
