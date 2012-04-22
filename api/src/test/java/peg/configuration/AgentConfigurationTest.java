package peg.configuration;

import static org.junit.Assert.*;
import org.junit.Test;

public class AgentConfigurationTest {

    @Test
    public void testAgentConfiguration(){
        AgentConfiguration config = AgentConfiguration.getInstance();
        assertNotNull(config);
        assertEquals("agent::7070", config.getName());
        assertEquals(7070, config.getPort());
        assertEquals("http://localhost:7171/replication", config.getReplicationAddress());
        assertEquals(25, config.getCorePoolSize());
        assertEquals(30000, config.getRequestTimeout());
        assertEquals(86400000, config.getInitialReplicationWindow());

        assertNotNull(config.getBuddies());
        assertEquals(2, config.getBuddies().size());
        assertNotNull(config.getPegs());
        assertEquals(1, config.getPegs().size());
    }
}
