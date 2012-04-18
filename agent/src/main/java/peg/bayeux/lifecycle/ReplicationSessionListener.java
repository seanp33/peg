package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.Agent;

/**
 * A Listener for replication session events
 */
public class ReplicationSessionListener implements ClientSessionChannel.MessageListener {

    protected final static Logger logger = LoggerFactory.getLogger(ReplicationListener.class);

    private Agent agent;

    public ReplicationSessionListener(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
        logger.debug("onMessage");
        // TODO: enable handling of session and start, stop, and fault events
    }

}
