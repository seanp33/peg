package peg.agent;

import com.sun.enterprise.ee.cms.core.GMSFactory;
import com.sun.enterprise.ee.cms.core.GroupManagementService;
import sync.Agent;

import java.net.URI;

public class ShoalSyncAgent implements Agent {

    private String id, name;


    public ShoalSyncAgent(String id, String name) {
        this.id = id;
        this.name = name;
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
