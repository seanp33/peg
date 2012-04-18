package peg.bayeux;

import static org.junit.Assert.*;
import org.junit.Test;
import peg.configuration.AgentConfiguration;

public class BayeuxAgentTest {

    @Test
    public void testInitialization() throws Exception{
        BayeuxAgent agent = new BayeuxAgent(AgentConfiguration.getInstance());
        agent.initialize();
    }
}
