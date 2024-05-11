package dat152.websockets.services;

import java.io.IOException;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/demo")
public class WebsocketDemo {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Client " + session.getId() + " has opened a connection.");
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("Got message from client " + session.getId() + ": " + message);
		try {
			session.getBasicRemote().sendText("Data sent from server to client");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Client " + session.getId() + " has ended");
	}
}
