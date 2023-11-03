package edu.syr.trello.service;

import edu.syr.trello.dao.User;
import edu.syr.trello.mapper.UserMapper;
import edu.syr.trello.model.UserRequest;
import edu.syr.trello.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
