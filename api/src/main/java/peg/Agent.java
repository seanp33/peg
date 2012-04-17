package peg;

import java.util.List;

/**
 * Agent provides the mechanism to establish and maintain a {@link Session} with other Agents. A Agent
 * can be uniquely identified by the their name and id properties
 */
public interface Agent {

    /**
     * Obtains the name of the Agent as it is to be known to he group
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

    // TODO: consider moving these to a connection handler abstraction

    /**
     * Indicates that the Agent is connected to a corresponding client
     * @return
     */
    boolean isConnected();

    /**
     * Handler for connection
     */
    void onConnected();

    /**
     * Disconnection handler
     */
    void onDisconnected();

    /**
     * Connection fault handler
     */
    void onConnectionFault();

    /**
     * Handler for updates to the group layout
     * @param groups
     */
    void onGroupUpdate(List<String> groups);

}
