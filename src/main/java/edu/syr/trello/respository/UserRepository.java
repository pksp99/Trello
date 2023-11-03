package edu.syr.trello.respository;

import edu.syr.trello.dao.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
