package peg.configuration;

public class AgentConfiguration {

    private String name;

    private String agentAddress;

    private String replicationAddress;

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

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getReplicationAddress() {
        return replicationAddress;
    }

    public void setReplicationAddress(String replicationAddress) {
        this.replicationAddress = replicationAddress;
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
