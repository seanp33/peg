package peg.bayeux;

import com.google.common.eventbus.EventBus;
import org.cometd.bayeux.server.BayeuxServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.PegAgent;
import peg.PegClient;
import peg.ConfigurableAgent;
import peg.configuration.AgentConfiguration;

import java.util.ArrayList;
import java.util.List;

public class BayeuxPegAgent extends ConfigurableAgent {

    protected final static Logger logger = LoggerFactory.getLogger(BayeuxPegAgent.class);

    private EventBus eventBus;

    private List<PegClient> pegClients;

    private List<PegClient> buddyClients;

    protected BayeuxPegServer server;

    private BayeuxClientFactory bayeuxClientFactory;

    protected BayeuxPegAgent(AgentConfiguration configuration) {
        super(configuration);
        eventBus = new EventBus();
        buddyClients = new ArrayList<PegClient>();
        pegClients = new ArrayList<PegClient>();

        // inject?
        bayeuxClientFactory = new BayeuxClientFactory(configuration.getCorePoolSize());
    }

    @Override
    public void initialize() {
        logger.trace("Initializing Server");
        initServer();

        logger.info("Initializing Buddies");
        initBuddies();

        // we'll just handshake with buddies
        for (PegClient client : buddyClients) {
            client.handshake();
        }

        logger.info("Initializing Known Pegs");
        initPegs();
    }

    @Override
    public void shutDown() {
        try {
            this.server.shutDown();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onGroupUpdate(List<String> groups) {
        logger.debug("groups are now: " + groups);
    }

    private void initBuddies() {
        List<String> buddies = configuration.getBuddies();
        for (String buddy : buddies) {
            buddyClients.add(
                    new BayeuxPegClient(this.getName(), bayeuxClientFactory.getClient(buddy),
                            this.configuration.getRequestTimeout(), this.eventBus)
            );
        }
    }

    private void initPegs() {
        List<String> pegs = configuration.getPegs();
        for (String peg : pegs) {
            pegClients.add(
                    new BayeuxPegClient(this.getName(), bayeuxClientFactory.getClient(peg),
                            this.configuration.getRequestTimeout(), this.eventBus)
            );
        }
    }

    private void initServer() {
        server = new BayeuxPegServer(configuration.getPort(), this.eventBus);
        try {
            server.initialize();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        PegAgent agent = new BayeuxPegAgent(AgentConfiguration.getInstance());
        agent.initialize();

        // TODO: handle ctrl c and shutdown
    }
}
