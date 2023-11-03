package edu.syr.trello.controller;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskRequest;
import edu.syr.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest body) {
        Task createdTask = taskService.createTask(body);
        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/modify/{taskId}")
    public ResponseEntity<Task> modifyTask(
            @PathVariable String taskId,
            @Valid @RequestBody TaskRequest body) {
        Task modifiedTask = taskService.modifyTask(taskId, body);
        return ResponseEntity.ok(modifiedTask);
    }
}
