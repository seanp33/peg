package peg;

/**
 * A pair of properties describing an Agent's name and address
 */
public class AgentHandle {

    private String name;

    private String address;

    public AgentHandle(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
