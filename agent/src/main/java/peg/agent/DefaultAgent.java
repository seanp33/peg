package peg.agent;

import com.sun.enterprise.ee.cms.core.GMSFactory;
import com.sun.enterprise.ee.cms.core.GroupManagementService;
import sync.Agent;

import java.net.URI;

public class DefaultAgent implements Agent {

    private String id, name;

    private int priority;

    public DefaultAgent(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public void join(URI connectionPath) {
        String serverName = connectionPath.getScheme() + connectionPath.getAuthority();
        String groupName = connectionPath.getPath();
        GroupManagementService gms = (GroupManagementService) GMSFactory.startGMSModule(serverName, groupName,
                GroupManagementService.MemberType.CORE, null);
    }

    @Override
    public void leave(URI connectionPath) {
    }
}
