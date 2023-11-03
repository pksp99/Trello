package edu.syr.trello.service;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskRequest;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest body);

    void deleteTask(String taskId);

    List<Task> getAllTasks();

    Task modifyTask(String taskId, TaskRequest body);
}
