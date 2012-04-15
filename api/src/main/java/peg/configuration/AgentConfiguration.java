package peg.configuration;

public class AgentConfiguration {

    private String name;

    private String replicationUri;

    private String[] buddies;

    private long initialReplicationWindow;

    public AgentConfiguration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReplicationUri() {
        return replicationUri;
    }

    public void setReplicationUri(String replicationUri) {
        this.replicationUri = replicationUri;
    }

    public String[] getBuddies() {
        return buddies;
    }

    public void setBuddies(String[] buddies) {
        this.buddies = buddies;
    }

    public long getInitialReplicationWindow() {
        return initialReplicationWindow;
    }

    public void setInitialReplicationWindow(long initialReplicationWindow) {
        this.initialReplicationWindow = initialReplicationWindow;
    }
}
