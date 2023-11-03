package edu.syr.trello.util;

import edu.syr.trello.dao.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserUtil {

    public void addTaskToUsers(List<User> userList, String taskId) {
        if (userList != null && taskId != null) {
            userList.forEach(user -> {
                if (user.getAssignedTaskIds() == null) {
                    user.setAssignedTaskIds(List.of(taskId));
                } else if (!user.getAssignedTaskIds().contains(taskId)) {
                    user.getAssignedTaskIds().add(taskId);
                }
            });
        }
    }

    public void removeTaskFromUsers(List<User> userList, String taskId) {
        if (userList != null && taskId != null) {
            userList.forEach(user -> {
                if (user.getAssignedTaskIds() != null) {
                    user.getAssignedTaskIds().removeIf(id -> id.equals(taskId));
                }
            });
        }
    }
    public void updateTaskId(List<User> existingAssignedUsers, List<User> updatedAssignedUsers, String taskId) {
        if (taskId != null) {
            removeTaskFromUsers(existingAssignedUsers, taskId);
            addTaskToUsers(updatedAssignedUsers, taskId);
        }
    }
}
