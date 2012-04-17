package peg.bayeux.lifecycle;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

public class StartSessionListener implements ClientSessionChannel.MessageListener {

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
    }
}
