package peg.bayeux;

import org.cometd.bayeux.Channel;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.ConfigurableAgent;
import peg.bayeux.lifecycle.ConnectionListener;
import peg.bayeux.lifecycle.InitializationListener;
import peg.configuration.AgentConfiguration;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BayeuxAgent extends ConfigurableAgent {

    protected final static Logger logger = LoggerFactory.getLogger(BayeuxAgent.class);

    private String name;

    private List<BayeuxClient> pegClients;

    private List<BayeuxClient> buddyClients;

    private InitializationListener initializationListener;

    private ConnectionListener connectionListener;

    protected BayeuxServer server;

    private BayeuxClientFactory clientFactory;

    protected BayeuxAgent(AgentConfiguration configuration) {
        super(configuration);
        clientFactory = new BayeuxClientFactory(configuration.getCorePoolSize());
        buddyClients = new ArrayList<BayeuxClient>();
        initializationListener = new InitializationListener(this);

        // TODO: define roll of connection listener
        // will Agent maintain the notion of shared listeners as it is currently?
        // Most likely each Bayeux should maintain its own set if LifecycleListeners
        connectionListener = new ConnectionListener();
    }

    @Override
    public void initialize() {
        logger.info("Initializing");

        initBuddyClients();

        BayeuxClient primaryBuddy = buddyClients.get(0);
        primaryBuddy.handshake();
        primaryBuddy.waitFor(1000, BayeuxClient.State.CONNECTED);

        // TODO: establish listeners for group update
        // TODO: establish listeners for session start and stop
        // TODO: establish listeners for replication data
    }

    @Override
    public void onGroupUpdate(List<String> groups) {
        logger.debug("groups are now: " + groups);
    }

    private void initBuddyClients() {
        List<String> buddies = configuration.getBuddies();
        for (String buddy : buddies) {
            BayeuxClient buddyClient = clientFactory.getClient(buddy);
            buddyClient.getChannel(Channel.META_HANDSHAKE).addListener(initializationListener);
            buddyClient.getChannel(Channel.META_CONNECT).addListener(connectionListener);
            buddyClients.add(buddyClient);
        }
    }

    private void initPegs(){
        List<String> pegs = configuration.getPegs();
        for(String peg : pegs){
            BayeuxClient pegClient = clientFactory.getClient(peg);

        }
    }

    private void initServer() {
    }
}
