package peg.bayeux.client;

import com.google.common.eventbus.EventBus;
import org.cometd.bayeux.Channel;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.client.PegClient;

import java.util.List;

public class BayeuxPegClient implements PegClient, ClientSessionChannel.MessageListener {

    protected final static Logger logger = LoggerFactory.getLogger(BayeuxPegClient.class);

    private String name;

    private BayeuxClient bayeuxClient;

    private long requestTimeout;

    private EventBus eventBus;

    public BayeuxPegClient(final String name, final BayeuxClient bayeuxClient,
                           final long requestTimeout, final EventBus eventBus) {
        this.name = name;
        this.bayeuxClient = bayeuxClient;
        this.requestTimeout = requestTimeout;
        this.eventBus = eventBus;
        initializeListeners();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void handshake() {
        bayeuxClient.handshake();
        boolean ok = bayeuxClient.waitFor(requestTimeout, BayeuxClient.State.CONNECTED);
        if (!ok) {
            String msg = "Could not establish handshake with...";
            logger.error(msg);
            throw new RuntimeException(msg);
        }
    }

    @Override
    public void requestGroupInformation() {
        bayeuxClient.getChannel("/service/group/layout-response").addListener(this);
        bayeuxClient.getChannel("/service/group/layout").publish(name);
    }

    @Override
    public boolean isConnected() {
        return bayeuxClient.isConnected();
    }

    @Override
    public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
        String channel = clientSessionChannel.getId();
        String msg = name + "...[handling message  " + channel + "]...";
        System.out.println(msg);
        logger.trace(msg);

        if(channel.equals(Channel.META_CONNECT)){
            requestGroupInformation();
        }else if(channel.equals("/service/group/layout-response")){
            System.out.println("/service/group/layout-response : " + message.getJSON());
        }
    }

    private void initializeListeners() {
        bayeuxClient.getChannel(Channel.META_HANDSHAKE).addListener(this);
        bayeuxClient.getChannel(Channel.META_CONNECT).addListener(this);
    }
}
