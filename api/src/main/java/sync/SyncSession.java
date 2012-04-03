package sync;

import java.util.Collection;

/**
 * Defines a session between multiple {@link SyncAgent} instances
 */
public interface SyncSession {

    void expire();

    void addSyncPlan(SyncPlan plan);

    void removeSyncPlan(SyncPlan plan);

    Collection<SyncPlan> getSyncPlans();
}
