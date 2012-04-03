package sync;

/**
 * SyncAgent provides the mechanism to establish and maintain a {@link SyncSession} with other SyncAgents. A SyncAgent
 * can be uniquely identified by the their name and id properties
 */
public interface SyncAgent {

    String getId();

    String getName();

    int getPriority();

}
