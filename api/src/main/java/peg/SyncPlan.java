package peg;

/**
 * Defines a set of steps to be carried out during the course of a {@link Session}
 */
public interface SyncPlan {

    void addMember(Agent agent);

    void removeMember(Agent agent);

    void execute(boolean dryrun);
}
