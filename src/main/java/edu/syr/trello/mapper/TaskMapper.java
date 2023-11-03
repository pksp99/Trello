package edu.syr.trello.mapper;

import edu.syr.trello.dao.Task;
import edu.syr.trello.dao.User;
import edu.syr.trello.model.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Component
public class TaskMapper {
    Logger logger = LoggerFactory.getLogger(TaskMapper.class);

    public Task mapToTask(TaskRequest body, List<User> assignedUsers) {
        if (body == null) {
            logger.error("TaskRequest Body is null");
            return null;
        }
        return Task.builder()
                .title(body.getTitle())
                .description((body.getDescription()))
                .userId(body.getUserId())
                .state(body.getState())
                .assignedUserIds(assignedUsers.stream().map(User::getId).toList())
                .build();
    }

    public void updateTaskFromRequest(Task existingTask, TaskRequest body, List<User> updatedAssignedUsers) {
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
        if (!ObjectUtils.isEmpty(updatedAssignedUsers)) {
            existingTask.setAssignedUserIds(updatedAssignedUsers.stream().map(User::getId).toList());
        }
    }
}
