package peg.bayeux.server.service;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for maintaining information pertaining to the state of the peg group, and for exposing this
 * information to requesting peg agents
 */
public class GroupInformationService extends AbstractService {

    protected final static Logger logger = LoggerFactory.getLogger(GroupInformationService.class);

    public GroupInformationService(BayeuxServer bayeux, String name) {
        super(bayeux, name);
        configure();
    }

    public GroupInformationService(BayeuxServer bayeux, String name, int maxThreads) {
        super(bayeux, name, maxThreads);
        configure();
    }

    // TODO: externalize this to some kind of services configuration
    private void configure() {
        addService("/service/group/layout", "getGroupLayout");
    }

    public List<String> getGroupLayout(ServerSession remote, Message message) {
        String msg = "Handling getGroupLayoutRequest from " + message.getData();
        System.out.println(msg);
        logger.trace(msg);

        // TODO: consider what the structure will be for a group layout - use a simple list for now
        List<String> group = new ArrayList<String>();
        group.add("http://example.com:7777/peg");
        group.add("http://example.org:8888/peg");
        group.add("http://example.org:9999/peg");

        return group;
    }
}
