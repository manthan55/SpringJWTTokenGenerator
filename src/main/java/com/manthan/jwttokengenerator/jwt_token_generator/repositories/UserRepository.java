package com.manthan.jwttokengenerator.jwt_token_generator.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.manthan.jwttokengenerator.jwt_token_generator.models.User;

// this will be your JPA repository
// but in this sample using a hashmap as data store
@Repository
public class UserRepository {
    private Map<Long, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public User addUser(User user){
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public User getUser(Long userId) throws Exception{
        if(!users.containsKey(userId)) throw new Exception("User with id: "+userId+" not found");
        return users.get(userId);
    }

    public User updateUser(User user) throws Exception{
        if(!users.containsKey(user.getId())) throw new Exception("User with id: "+user.getId()+" not found");
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public void deleteUser(Long userId) throws Exception{
        if(users.containsKey(userId)) users.remove(userId);
    }
}
