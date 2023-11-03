package edu.syr.trello.service;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskFilterRequest;
import edu.syr.trello.model.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest body);

    void deleteTask(String taskId);

    List<Task> getFilteredTasks(TaskFilterRequest filter);

    Task modifyTask(String taskId, TaskRequest body);

    Page<Task> getAllTasks(PageRequest pageRequest);
}
