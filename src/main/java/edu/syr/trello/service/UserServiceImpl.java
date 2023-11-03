package edu.syr.trello.service;

import edu.syr.trello.dao.User;
import edu.syr.trello.mapper.UserMapper;
import edu.syr.trello.model.UserRequest;
import edu.syr.trello.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
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
