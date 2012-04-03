package sync;

import java.util.Collection;

public interface ConflictResolutionStrategy {

    boolean resolveConflicts(Collection<Conflict> conflicts);

}
