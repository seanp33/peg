package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.Agent;

public class InitializationListener implements ClientSessionChannel.MessageListener {

    protected final static Logger logger = LoggerFactory.getLogger(InitializationListener.class);

    private Agent agent;

    public InitializationListener(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
        if (message.isSuccessful()) {
            agent.initialize();
        }
    }
}
