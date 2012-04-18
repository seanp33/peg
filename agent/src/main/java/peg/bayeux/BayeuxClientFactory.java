package peg.bayeux;

import org.cometd.client.BayeuxClient;
import org.cometd.websocket.client.WebSocketTransport;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class BayeuxClientFactory {

    private Map<String, BayeuxClient> clients;

    private ScheduledExecutorService scheduledExecutorService;

    private WebSocketClientFactory webSocketClientFactory;

    public BayeuxClientFactory(int corePoolSize) {
        this.clients = new HashMap<String, BayeuxClient>();
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
        this.webSocketClientFactory = new WebSocketClientFactory();
    }

    public BayeuxClient getClient(String url) {

        if (!clients.containsKey(url)) {
            BayeuxClient client = new BayeuxClient(url, this.scheduledExecutorService,
                    WebSocketTransport.create(null, this.webSocketClientFactory));

            clients.put(url, client);
        }

        return clients.get(url);
    }
}
