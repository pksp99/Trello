package edu.syr.trello.service;

import edu.syr.trello.dao.Task;
import edu.syr.trello.mapper.TaskMapper;
import edu.syr.trello.model.TaskRequest;
import edu.syr.trello.respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task createTask(TaskRequest body) {
        Task task = taskMapper.mapToTask(body);
        task = taskRepository.save(task);
        return task;
    }

    @Override
    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task modifyTask(String taskId, TaskRequest body) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingTask != null) {
            taskMapper.updateTaskFromRequest(existingTask, body);
            existingTask = taskRepository.save(existingTask);
        }

        return existingTask;
    }
}
