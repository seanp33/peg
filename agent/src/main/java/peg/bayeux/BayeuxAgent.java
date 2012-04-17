package peg.bayeux;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.client.BayeuxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.ConfigurableAgent;
import peg.configuration.AgentConfiguration;

import java.net.URI;
import java.util.List;

public class BayeuxAgent extends ConfigurableAgent {

    protected final static Logger logger = LoggerFactory.getLogger(BayeuxAgent.class);

    private String name;

    protected BayeuxClient client;

    protected BayeuxServer server;

    protected BayeuxAgent(AgentConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void initialize() {
        logger.info("Initializing");
        // TODO: establish listeners for group update
        // TODO: establish listeners for session start and stop
        // TODO: establish listeners for replication data

        // TODO: establish connection to buddies
        // TODO: batch up commands for starting the 3a stage
    }

    @Override
    public boolean isConnected() {
        return this.client.isConnected();
    }

    @Override
    public void onConnectionFault() {
        logger.error("a connection error occurred");
    }

    @Override
    public void onDisconnected() {
        logger.info("connection disconnected");
    }

    @Override
    public void onConnected() {
        logger.info("connection established");
    }

    @Override
    public void onGroupUpdate(List<String> groups) {
        logger.debug("groups are now: " + groups);
    }

    private void initClient() {

    }

    private void initServer() {
    }
}
