package edu.syr.trello.service;

import edu.syr.trello.dao.Task;
import edu.syr.trello.dao.User;
import edu.syr.trello.mapper.TaskMapper;
import edu.syr.trello.model.TaskFilterRequest;
import edu.syr.trello.model.TaskRequest;
import edu.syr.trello.respository.TaskRepository;
import edu.syr.trello.respository.UserRepository;
import edu.syr.trello.util.UserUtil;
import edu.syr.trello.util.filters.TaskFilter;
import edu.syr.trello.util.filters.TaskFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserUtil userUtil;

    @Override
    public Task createTask(TaskRequest body) {
        List<User> assignedUsers = (!ObjectUtils.isEmpty(body.getAssignedUserIds()))
                ? userRepository.findAllById(body.getAssignedUserIds())
                : new ArrayList<>();
        Task task = taskMapper.mapToTask(body, assignedUsers);
        task = taskRepository.save(task);

        // Add Task to Associated Users
        userUtil.addTaskToUsers(assignedUsers, task.getId());
        userRepository.saveAll(assignedUsers);
        return task;
    }

    @Override
    public void deleteTask(String taskId) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask == null) return;
        taskRepository.delete(existingTask);

        // Remove Task from Associated Users
        List<User> assignedUsers = (!ObjectUtils.isEmpty(existingTask.getAssignedUserIds()))
                ? userRepository.findAllById(existingTask.getAssignedUserIds())
                : new ArrayList<>();
        userUtil.removeTaskFromUsers(assignedUsers, existingTask.getId());
        userRepository.saveAll(assignedUsers);
    }

    @Override
    public Page<Task> getAllTasks(PageRequest pageRequest) {
        return taskRepository.findAll(pageRequest);
    }

    @Override
    public Task getUserById(String taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    @Override
    public List<Task> getFilteredTasks(TaskFilterRequest filterRequest) {
        if (filterRequest == null) return taskRepository.findAll();
        TaskFilter taskFilter = TaskFilterFactory.getFilter(filterRequest);
        return taskFilter.filterTasks(taskRepository.findAll());
    }

    @Override
    public Task modifyTask(String taskId, TaskRequest body) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask == null) return null;

        // Modify Tasks for associated Users
        List<User> updatedAssignedUsers = (!ObjectUtils.isEmpty(body.getAssignedUserIds()))
                ? userRepository.findAllById(body.getAssignedUserIds())
                : new ArrayList<>();
        List<User> existingAssignedUsers = (!ObjectUtils.isEmpty(existingTask.getAssignedUserIds()))
                ? userRepository.findAllById(existingTask.getAssignedUserIds())
                : new ArrayList<>();
        userUtil.updateTaskId(existingAssignedUsers, updatedAssignedUsers, taskId);
        userRepository.saveAll(existingAssignedUsers);
        userRepository.saveAll(updatedAssignedUsers);

        // Modify existing Task
        taskMapper.updateTaskFromRequest(existingTask, body, updatedAssignedUsers);
        existingTask = taskRepository.save(existingTask);

        return existingTask;
    }


}
