package peg.configuration;

import static org.junit.Assert.*;
import org.junit.Test;

public class AgentConfigurationTest {

    @Test
    public void testAgentConfiguration(){
        AgentConfiguration config = AgentConfiguration.getInstance();
        assertNotNull(config);
        assertEquals("agent::8080", config.getName());
        assertEquals("http://localhost:8080/peg", config.getReplicationAddress());
        assertEquals(25, config.getCorePoolSize());
        assertEquals(30000, config.getRequestTimeout());
        assertEquals(86400000, config.getInitialReplicationWindow());

        assertNotNull(config.getBuddies());
        assertEquals(2, config.getBuddies().size());
        assertNotNull(config.getPegs());
        assertEquals(1, config.getPegs().size());
    }
}
