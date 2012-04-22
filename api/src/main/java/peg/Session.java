package peg;

import java.util.Collection;

/**
 * Defines a session between multiple {@link PegAgent} instances
 */
public interface Session {

    void expire();

    void addSyncPlan(SyncPlan plan);

    void removeSyncPlan(SyncPlan plan);

    Collection<SyncPlan> getSyncPlans();
}
