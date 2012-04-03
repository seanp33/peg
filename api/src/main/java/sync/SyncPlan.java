package sync;

/**
 * Defines a set of steps to be carried out during the course of a {@link SyncSession}
 */
public interface SyncPlan {

    void addMember(SyncAgent agent);

    void removeMember(SyncAgent agent);

    void execute(boolean dryrun);
}
