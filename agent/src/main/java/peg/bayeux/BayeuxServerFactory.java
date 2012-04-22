package peg.bayeux;

import org.eclipse.jetty.websocket.WebSocketClientFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class BayeuxServerFactory {

    private ScheduledExecutorService scheduledExecutorService;

    private WebSocketClientFactory webSocketClientFactory;

    public BayeuxServerFactory(int corePoolSize) {
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
        this.webSocketClientFactory = new WebSocketClientFactory();
    }
}
