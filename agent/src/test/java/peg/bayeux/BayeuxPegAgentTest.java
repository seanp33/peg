package peg.bayeux;

import org.junit.After;
import org.junit.Test;
import peg.configuration.AgentConfiguration;

public class BayeuxPegAgentTest {

    private BayeuxPegAgent agent;

    @After
    public void shutDown(){
        agent.shutDown();
    }

    @Test
    public void testInitialization() throws Exception{
        agent = new BayeuxPegAgent(AgentConfiguration.getInstance());
        agent.initialize();
    }
}
