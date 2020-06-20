package io.muzoo.ooc.webapp.basic.security;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    {
        users.put("taextream",new User("taextream", "345"));
        users.put("admin",new User("admin", "tot"));
    }

    public User findByUsername(String username){
        return users.get(username);
    }

    public boolean checkIfUserExist(String username){
        return users.containsKey(username);
    }
}
