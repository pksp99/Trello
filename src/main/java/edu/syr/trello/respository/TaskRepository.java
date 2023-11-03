package edu.syr.trello.respository;

import edu.syr.trello.dao.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
