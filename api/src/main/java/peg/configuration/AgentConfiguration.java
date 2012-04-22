package peg.configuration;

import com.thoughtworks.xstream.XStream;

import java.net.URL;
import java.util.List;

public class AgentConfiguration {

    private String name;

    private int port;

    private String agentAddress;

    private int corePoolSize;

    private long requestTimeout;

    private long initialReplicationWindow;

    private String replicationAddress;

    private List<String> buddies;

    private List<String> pegs;

    private static AgentConfiguration instance;

    public static AgentConfiguration getInstance() {
        if (instance == null) {
            XStream xstream = new XStream();
            xstream.alias("configuration", AgentConfiguration.class);
            xstream.alias("buddy", String.class);
            xstream.alias("peg", String.class);
            URL defaultsFile = AgentConfiguration.class.getClassLoader().getResource("agent.xml");
            instance = (AgentConfiguration) xstream.fromXML(defaultsFile);
        }

        return instance;
    }

    private AgentConfiguration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getReplicationAddress() {
        return replicationAddress;
    }

    public void setReplicationAddress(String replicationAddress) {
        this.replicationAddress = replicationAddress;
    }

    public long getInitialReplicationWindow() {
        return initialReplicationWindow;
    }

    public void setInitialReplicationWindow(long initialReplicationWindow) {
        this.initialReplicationWindow = initialReplicationWindow;
    }

    public List<String> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<String> buddies) {
        this.buddies = buddies;
    }

    public List<String> getPegs() {
        return pegs;
    }

    public void setPegs(List<String> pegs) {
        this.pegs = pegs;
    }
}
