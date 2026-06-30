package splitwise_design.services;

import splitwise_design.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<Integer, User> users;

    public UserService() {
        users = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        users.put(user.id(), user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public boolean isUserExist(int id) {
        return users.containsKey(id);
    }
}
