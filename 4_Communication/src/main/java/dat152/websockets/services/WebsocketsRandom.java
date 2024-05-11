package dat152.websockets.services;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/random")
public class WebsocketsRandom {
	private ScheduledExecutorService timer;

	@OnOpen
	public void showRandom(Session session) {
		System.out.println(session.getId() + " has opened a connection");
		timer = Executors.newSingleThreadScheduledExecutor();
		Random randomGenerator = new Random();
		timer.scheduleAtFixedRate(() -> sendRandom(session, randomGenerator), 0, 5, TimeUnit.SECONDS);
	}

	@OnClose
	public void onClose(Session session) {
		timer.shutdown();
		System.out.println("Session " + session.getId() + " has ended");
	}

	private void sendRandom(Session session, Random randomGenerator) {
		String randomNumberAsText = Integer.toString(randomGenerator.nextInt(100));
		System.out.println("To client: " + randomNumberAsText + ".");
		try {
			session.getBasicRemote().sendText(randomNumberAsText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
