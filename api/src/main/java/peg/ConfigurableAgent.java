package peg;

import peg.configuration.AgentConfiguration;

public abstract class ConfigurableAgent implements Agent {

    protected AgentConfiguration configuration;

    protected AgentHandle agentHandle;

    protected ConfigurableAgent(AgentConfiguration configuration) {
        this.configuration = configuration;
        this.agentHandle = new AgentHandle(configuration.getName(), configuration.getAgentAddress());
    }

    @Override
    public String getName() {
        return agentHandle.getName();
    }

    public AgentHandle getAgentHandle() {
        return agentHandle;
    }
}
