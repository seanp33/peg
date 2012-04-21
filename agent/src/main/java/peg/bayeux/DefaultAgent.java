package peg.bayeux;

import com.google.common.eventbus.EventBus;
import org.cometd.bayeux.server.BayeuxServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.Client;
import peg.ConfigurableAgent;
import peg.configuration.AgentConfiguration;

import java.util.ArrayList;
import java.util.List;

public class DefaultAgent extends ConfigurableAgent {

    protected final static Logger logger = LoggerFactory.getLogger(DefaultAgent.class);

    private EventBus eventBus;

    private List<Client> pegClients;

    private List<Client> buddyClients;

    protected BayeuxServer server;

    private BayeuxClientFactory bayeuxClientFactory;

    protected DefaultAgent(AgentConfiguration configuration) {
        super(configuration);
        eventBus = new EventBus();
        buddyClients = new ArrayList<Client>();
        pegClients = new ArrayList<Client>();

        // inject?
        bayeuxClientFactory = new BayeuxClientFactory(configuration.getCorePoolSize());
    }

    @Override
    public void initialize() {
        logger.info("Initializing Buddies");

        initBuddies();

        // we'll just initialize them immediately
        for (Client client : buddyClients) {
            client.handshake();
        }

        logger.info("Initializing Known Pegs");
        initPegs();
    }

    @Override
    public void onGroupUpdate(List<String> groups) {
        logger.debug("groups are now: " + groups);
    }

    private void initBuddies() {
        List<String> buddies = configuration.getBuddies();
        for (String buddy : buddies) {
            buddyClients.add(
                    new DefaultClient(this.getName(), bayeuxClientFactory.getClient(buddy),
                            this.configuration.getRequestTimeout(), this.eventBus)
            );
        }
    }

    private void initPegs() {
        List<String> pegs = configuration.getPegs();
        for (String peg : pegs) {
            pegClients.add(
                    new DefaultClient(this.getName(), bayeuxClientFactory.getClient(peg),
                            this.configuration.getRequestTimeout(), this.eventBus)
            );
        }
    }

    private void initServer() {
    }

    public static void main(String[] args) {
        new DefaultAgent(AgentConfiguration.getInstance());
    }
}
