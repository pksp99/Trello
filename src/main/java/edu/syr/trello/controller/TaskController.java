package edu.syr.trello.controller;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskFilterRequest;
import edu.syr.trello.model.TaskRequest;
import edu.syr.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Task> tasks = taskService.getAllTasks(pageRequest);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/applyFilter")
    public ResponseEntity<List<Task>> getFilteredTasks(@RequestBody(required = false) TaskFilterRequest filterRequest) {
        List<Task> tasks = taskService.getFilteredTasks(filterRequest);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest body) {
        Task createdTask = taskService.createTask(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
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

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getUserById(@PathVariable String taskId) {
        Task task = taskService.getUserById(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
