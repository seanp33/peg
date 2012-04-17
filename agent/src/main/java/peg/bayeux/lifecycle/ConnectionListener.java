package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.Agent;

public class ConnectionListener implements ClientSessionChannel.MessageListener {

    protected final static Logger logger = LoggerFactory.getLogger(ConnectionListener.class);

    private Agent agent;

    private boolean wasConnected;

    private boolean connected;

    public ConnectionListener(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
        if (!agent.isConnected()) {
            connected = false;
            agent.onDisconnected();
            return;
        }

        wasConnected = connected;
        connected = message.isSuccessful();
        if (!wasConnected && connected) {
            agent.onConnected();
        } else if (wasConnected && !connected) {
            agent.onConnectionFault();
        }
    }
}
