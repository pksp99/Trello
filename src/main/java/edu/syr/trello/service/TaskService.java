package edu.syr.trello.service;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TaskService {
    Task createTask(TaskRequest body);

    void deleteTask(String taskId);

    Page<Task> getAllTasks(PageRequest pageRequest);

    Task modifyTask(String taskId, TaskRequest body);
}
