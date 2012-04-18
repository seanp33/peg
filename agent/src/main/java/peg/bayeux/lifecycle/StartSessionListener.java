package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartSessionListener implements ClientSessionChannel.MessageListener {

    protected final static Logger logger = LoggerFactory.getLogger(StartSessionListener.class);

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
        logger.debug("onMessage");
    }
}
