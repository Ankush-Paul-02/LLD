package splitwise_design.services;

import splitwise_design.entities.Group;
import splitwise_design.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupService {

    private final Map<Integer, Group> groups;

    public GroupService() {
        this.groups = new ConcurrentHashMap<>();
    }

    public void createGroup(Integer id, String name, String desc) {
        Group group = new Group(id, name, desc);
        groups.put(id, group);
    }

    public Group getGroup(Integer id) {
        return groups.get(id);
    }

    public boolean isGroupExist(Integer id) {
        return groups.containsKey(id);
    }

    public void addUser(User user, Integer groupId) {
        Group group = groups.get(groupId);
        if (group != null && user != null && user.id() != null) {
            group.addUser(user);
        }
    }
}
