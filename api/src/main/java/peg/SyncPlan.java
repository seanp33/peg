package peg;

/**
 * Defines a set of steps to be carried out during the course of a {@link Session}
 */
public interface SyncPlan {

    void addMember(PegAgent pegAgent);

    void removeMember(PegAgent pegAgent);

    void execute(boolean dryrun);
}
