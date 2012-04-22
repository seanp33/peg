package peg;

/**
 * Defines the core interface for a peg client implementation. A client represents an interface to a single
 * server
 */
public interface PegClient {

    String getName();

    void handshake();

    boolean isConnected();
}
