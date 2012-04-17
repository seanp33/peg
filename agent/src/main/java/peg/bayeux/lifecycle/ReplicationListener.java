package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.Agent;

/**
 * Defines a listener for handling incoming replication data
 */
public class ReplicationListener implements ClientSessionChannel.MessageListener{

    protected final static Logger logger = LoggerFactory.getLogger(ReplicationListener.class);

    private Agent agent;

    public ReplicationListener(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {

        // TODO: obtain
    }
}
