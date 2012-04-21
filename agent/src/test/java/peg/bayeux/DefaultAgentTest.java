package peg.bayeux;

import org.junit.Test;
import peg.configuration.AgentConfiguration;

public class DefaultAgentTest {

    @Test
    public void testInitialization() throws Exception{
        DefaultAgent agent = new DefaultAgent(AgentConfiguration.getInstance());
        agent.initialize();
    }
}
