package edu.syr.trello.mapper;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {
    Logger logger = LoggerFactory.getLogger(TaskMapper.class);

    public Task mapToTask(TaskRequest body) {
        if (body == null) {
            logger.error("TaskRequest Body is null");
            return null;
        }
        return Task.builder()
                .title(body.getTitle())
                .description((body.getDescription()))
                .userId(body.getUserId())
                .state(body.getState())
                .assignedUsers(body.getAssignedUsers())
                .build();
    }

    public void updateTaskFromRequest(Task existingTask, TaskRequest body) {
        if (existingTask == null || body == null) {
            logger.error("ExistingTask or TaskRequest Body isn null");
            return;
        }
        if (body.getTitle() != null) {
            existingTask.setTitle(body.getTitle());
        }
        if (body.getDescription() != null) {
            existingTask.setDescription(body.getDescription());
        }
        if (body.getUserId() != null) {
            existingTask.setUserId(body.getUserId());
        }
        if (body.getState() != null) {
            existingTask.setState(body.getState());
        }
        if (body.getAssignedUsers() != null) {
            existingTask.setAssignedUsers(body.getAssignedUsers());
        }
    }
}
