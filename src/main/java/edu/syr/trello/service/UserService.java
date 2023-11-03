package edu.syr.trello.service;

import edu.syr.trello.dao.User;
import edu.syr.trello.model.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    User createUser(UserRequest userRequest);

    Page<User> getAllUsers(PageRequest pageRequest);

    User getUserById(String userId);

    void deleteUser(String userId);
}
