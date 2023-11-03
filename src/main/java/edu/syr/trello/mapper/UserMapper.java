package edu.syr.trello.mapper;

import edu.syr.trello.dao.User;
import edu.syr.trello.model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    Logger logger = LoggerFactory.getLogger(UserMapper.class);

    public User mapToUser(UserRequest userRequest) {
        if (userRequest == null) {
            logger.error("userRequest is null");
            return null;
        }
        return User.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .build();
    }
}
