package peg;

/**
 * Defines the core server interface
 */
public interface PegServer {

    // TODO: specify specific exception type
    void initialize() throws Exception;

    // TODO: specify specific exception type
    void shutDown() throws Exception;
}
