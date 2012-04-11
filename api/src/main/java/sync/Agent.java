package sync;

import java.net.URI;

/**
 * Agent provides the mechanism to establish and maintain a {@link Session} with other Agents. A Agent
 * can be uniquely identified by the their name and id properties
 */
public interface Agent {

    String getId();

    String getName();

    int getPriority();

    void join(URI connectionPath);

    void leave(URI connectionPath);
}
