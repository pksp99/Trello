package edu.syr.trello.service;

import edu.syr.trello.dao.User;
import edu.syr.trello.model.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest);

    List<User> getAllUsers();

    User getUserById(String userId);

    void deleteUser(String userId);
}
