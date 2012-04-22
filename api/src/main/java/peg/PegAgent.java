package peg;

import java.util.List;

/**
 * PegAgent provides the mechanism to establish and maintain a {@link Session} with other Agents. A PegAgent
 * can be uniquely identified by the their name and id properties
 */
public interface PegAgent {

    /**
     * Obtains the name of the PegAgent as it is to be known to he group
     * @return
     */
    String getName();

    /**
     * Obtains the agent's handle
     * @return
     */
    AgentHandle getAgentHandle();

    /**
     * Triggers an initialization implementation
     */
    void initialize();

    /**
     * Triggers a shutdown implementation
     */
    void shutDown();

    /**
     * Handler for updates to the group layout
     * @param groups
     */
    void onGroupUpdate(List<String> groups);

}
