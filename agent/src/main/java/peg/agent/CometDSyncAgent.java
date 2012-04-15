package peg.agent;

import sync.Agent;

import java.net.URI;

public class CometDSyncAgent implements Agent {

    private String id, name;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void join(URI connectionPath) {
    }

    @Override
    public void leave(URI connectionPath) {
    }
}
